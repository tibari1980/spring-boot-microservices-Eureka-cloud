package com.arcesi.identityservice.security.auth;

import java.io.IOException;
import java.time.Instant;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.arcesi.identityservice.dtos.RoleDTO;
import com.arcesi.identityservice.dtos.UserDTO;
import com.arcesi.identityservice.entities.RoleBean;
import com.arcesi.identityservice.entities.TokenBean;
import com.arcesi.identityservice.entities.UserBean;
import com.arcesi.identityservice.enums.AppUserRole;
import com.arcesi.identityservice.enums.ErrorsCodeEnumeration;
import com.arcesi.identityservice.enums.TokenType;
import com.arcesi.identityservice.exceptions.ArgumentNotValideEntityException;
import com.arcesi.identityservice.exceptions.EntityNotFoundException;
import com.arcesi.identityservice.repositories.RoleBeanRepository;
import com.arcesi.identityservice.repositories.TokenBeanRepository;
import com.arcesi.identityservice.repositories.UserBeanRepository;
import com.arcesi.identityservice.security.config.JwtService;
import com.arcesi.identityservice.validators.ObjectValidators;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
  private final UserBeanRepository userBeanRepository;
  private final TokenBeanRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final ModelMapper modelMapper;
  @Autowired
  private ObjectValidators<UserDTO> validator;
  @Autowired
  private RoleBeanRepository roleRepository;


  public AuthenticationResponse register(RegisterRequest request) {
    
	  log.info("Inside methode register of AuthentificationService request : {} ",request.toString());
	  

        UserDTO userDto=modelMapper.map(request, UserDTO.class);
		userDto.setCreatedAt(Instant.now());
		Map<String, String> violations = validator.validate(userDto);
		if (!violations.isEmpty()) {

			log.error("There are errors while saving user try again ! ", violations);
			throw new ArgumentNotValideEntityException("There are errors while saving user try again !!!",
					ErrorsCodeEnumeration.USER_NOT_VALIDE, violations);
		}
		RoleBean findRole = roleRepository.findByRoleNameIgnoreCase(AppUserRole.CUSTOMER.name())
				.orElseThrow(() -> new EntityNotFoundException(
						"Role with name : ` " + AppUserRole.CUSTOMER.name() + "` not found in our data base try again !!",
						ErrorsCodeEnumeration.ROLE_NOT_FOUND));
		// check if user Exist by email
		Optional<UserBean> ifExistUser = userBeanRepository.findUserBeanByEmail(userDto.getEmail());
		if (ifExistUser.isPresent()) {
			log.error("User exist with  email : ` {} ` in our data base try again!!", userDto.getEmail());
			throw new EntityNotFoundException(
					"User exist with email  : `" + userDto.getEmail() + "` in our data base try again!!",
					ErrorsCodeEnumeration.USER_NOT_FOUND);
		}

		userDto.setRoleDTOs(new HashSet<>());
		userDto.getRoleDTOs().add(modelMapper.map(findRole, RoleDTO.class));
		userDto.setUidUser(UUID.randomUUID().toString());
		userDto.setEnabled(Boolean.FALSE);
		userDto.setLocked(Boolean.FALSE);
		userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

		UserBean entity = modelMapper.map(userDto, UserBean.class);
		UserBean bean = userBeanRepository.save(entity);
		
		log.info("User created successfully !!", bean.toString());
		var jwtToken=jwtService.generateToken(bean);
		var refreshToken = jwtService.generateRefreshToken(bean);
	    saveUserToken(bean, jwtToken);
		return AuthenticationResponse
				.builder()
				.accessToken(jwtToken)
				.refreshToken(refreshToken)
				.build();

		
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    
    var user=userBeanRepository.findUserBeanByEmail(request.getEmail()).orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefreshToken(user);
    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);
    return AuthenticationResponse.builder()
        .accessToken(jwtToken)
            .refreshToken(refreshToken)
        .build();
  }

  private void saveUserToken(UserBean user, String jwtToken) {
    var token = TokenBean.builder()
        .userBean(user)
        .token(jwtToken)
        .tokenType(TokenType.BEARER)
        .expired(false)
        .revoked(false)
        .build();
    tokenRepository.save(token);
  }

  private void revokeAllUserTokens(UserBean user) {
    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }

  public void refreshToken(
          HttpServletRequest request,
          HttpServletResponse response
  ) throws IOException {
    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    final String refreshToken;
    final String userEmail;
    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
      return;
    }
    refreshToken = authHeader.substring(7);
    userEmail = jwtService.extractUsername(refreshToken);
    if (userEmail != null) {
      var user = this.userBeanRepository.findUserBeanByEmail(userEmail)
              .orElseThrow();
      if (jwtService.isTokenValid(refreshToken, user)) {
        var accessToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, accessToken);
        var authResponse = AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
        new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
      }
    }
  }
}
