package com.arcesi.identityservice.controllers.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arcesi.identityservice.controllers.ApiGestionUser;
import com.arcesi.identityservice.dtos.RoleDTO;
import com.arcesi.identityservice.dtos.UserDTO;
import com.arcesi.identityservice.dtos.requests.AddRoleToUserRequest;
import com.arcesi.identityservice.dtos.requests.RoleRequest;
import com.arcesi.identityservice.dtos.requests.UserRequest;
import com.arcesi.identityservice.dtos.responses.RoleResponse;
import com.arcesi.identityservice.dtos.responses.UserResponse;
import com.arcesi.identityservice.enums.ErrorsCodeEnumeration;
import com.arcesi.identityservice.exceptions.ArgumentNotValidException;
import com.arcesi.identityservice.services.IUserRestService;
import com.arcesi.identityservice.utils.Constants;

import lombok.extern.slf4j.Slf4j;

@RestController
 
@Slf4j
@RequestMapping(value = Constants.APP_ROOT + "usersRoles")
@Validated
public class GestionUserControllerImp implements ApiGestionUser {

	@Autowired
	private IUserRestService iUserRestService;

	@Autowired
	private ModelMapper mapper;

	@Override
	public ResponseEntity<List<UserResponse>> getAllUsersActive(int page, int limit) {
		log.info("Inside methode getAllUsersActive of GestionUserControllerImp  , page : {} ,limi :{}", page, limit);

		List<UserDTO> lstUsersDtos = iUserRestService.findAllUsers(page, limit);
		if (lstUsersDtos.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		List<UserResponse> lstUsersResponse = lstUsersDtos.stream().map(user -> mapper.map(user, UserResponse.class))
				.collect(Collectors.toList());
		return new ResponseEntity<List<UserResponse>>(lstUsersResponse, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<List<RoleResponse>> getAllRoles(int page, int limit) {
		log.info("Inside methode getAllRoles of GestionUserControllerImp  , page : {} ,limi :{}", page, limit);

		List<RoleDTO> lstRolesDtos = iUserRestService.findAllRoles(page, limit);
		if (lstRolesDtos.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		List<RoleResponse> lstRolesResponse = lstRolesDtos.stream().map(role -> mapper.map(role, RoleResponse.class))
				.collect(Collectors.toList());
		return new ResponseEntity<List<RoleResponse>>(lstRolesResponse, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<RoleResponse> findRoleByName(String roleName) {
		log.info("Inside methode findRoleByName of GestionUserControllerImp   Role Name : {} ", roleName);

		if (StringUtils.isBlank(roleName)) {
			log.error("Role name is not valide try again : {}", roleName);
			throw new ArgumentNotValidException("Role name : `" + roleName + "` is not valid try again",
					ErrorsCodeEnumeration.PARAMETRE_NOT_VALID);
		}
		RoleDTO roleDTO = iUserRestService.getRoleByName(roleName);

		return new ResponseEntity<RoleResponse>(mapper.map(roleDTO, RoleResponse.class), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UserResponse> findUserByEmail(String email) {
		log.info("Inside methode findUserByEmail of GestionUserControllerImp   User Email : {} ", email);

		if (StringUtils.isBlank(email)) {
			log.error("Email user is not valide try again : {}", email);
			throw new ArgumentNotValidException("User email : `" + email + "` is not valid try again",
					ErrorsCodeEnumeration.PARAMETRE_NOT_VALID);
		}
		UserDTO userDTO = iUserRestService.getUserByEmail(email);

		return new ResponseEntity<UserResponse>(mapper.map(userDTO, UserResponse.class), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UserResponse> findUserByUid(String uid) {
		log.info("Inside methode findUserByUid of GestionUserControllerImp   User uid : {} ", uid);

		if (StringUtils.isBlank(uid)) {
			log.error("Code unique  user is not valide try again : {}", uid);
			throw new ArgumentNotValidException("User uid : `" + uid + "` is not valid try again",
					ErrorsCodeEnumeration.PARAMETRE_NOT_VALID);
		}
		UserDTO userDTO = iUserRestService.getUserByUid(uid);

		return new ResponseEntity<UserResponse>(mapper.map(userDTO, UserResponse.class), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> addRoleToUser(final AddRoleToUserRequest roleToUser) {
		log.info("Inside methode addRoleToUser of GestionUserControllerImp   User email  : {} , Role name : {} ",
				roleToUser.getUserEmail(), roleToUser.getRoleName());
		if (StringUtils.isBlank(roleToUser.getUserEmail()) || StringUtils.isBlank(roleToUser.getRoleName())) {
			log.error("Email or Role name are not valide try again email : {}, roleName : {}", roleToUser.getUserEmail(),roleToUser.getRoleName());
			throw new ArgumentNotValidException(
					"User email or Role name : `" + roleToUser.getUserEmail() + " -- " + roleToUser.getRoleName() + "` are not valid try again",
					ErrorsCodeEnumeration.PARAMETRE_NOT_VALID);
		}
		iUserRestService.addRoleToUser(roleToUser.getUserEmail(),roleToUser.getRoleName());

		return new ResponseEntity<String>("Role with  : `"+ roleToUser.getRoleName() + " `  Added to User with Email  : ` "+ roleToUser.getUserEmail()+" ` successfully !!",HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<String> detelteUser(Long idUser) {
		log.info("Inside methode deleteUser of GestionUserControllerImp  code User : {}", idUser);
		if (idUser == null) {
			log.info("Code user `{}` not valid try again", idUser);
			throw new ArgumentNotValidException("Code user : `" + idUser + "` not valid try again",
					ErrorsCodeEnumeration.PARAMETRE_NOT_VALID);
		}
		iUserRestService.deleteUserById(idUser);
		return new ResponseEntity<String>("User with id :`" + idUser + "`deleted successfully  ",
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> detelteRole(Long idRole) {
		log.info("Inside methode deleteRole of GestionUserControllerImp  code Role : {}", idRole);
		if (idRole == null) {
			log.info("Role Id :  `{}` not valid try again", idRole);
			throw new ArgumentNotValidException("Role id  : `" + idRole + "` not valid try again",
					ErrorsCodeEnumeration.PARAMETRE_NOT_VALID);
		}
		iUserRestService.deleteRoleById(idRole);
		return new ResponseEntity<String>("Role with id :`" + idRole + "`deleted successfully  ",
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UserResponse> updateUser(UserRequest userRequest, Long code) {
		log.info("Inside methode updateUser in Controller GestionUserControllerImp  userRequest : {}  , code  : {} ",
				userRequest, code);
		UserDTO userDTO = iUserRestService.updateUser(mapper.map(userRequest, UserDTO.class), code);

		return new ResponseEntity<UserResponse>(mapper.map(userDTO, UserResponse.class), HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<RoleResponse> updateRole(RoleRequest roleRequest, Long code) {
		log.info("Inside methode updateRole in Controller GestionUserControllerImp  roleRequest : {}  , code  : {} ",
				roleRequest, code);
		RoleDTO roleDTO = iUserRestService.updateRole(mapper.map(roleRequest, RoleDTO.class), code);

		return new ResponseEntity<RoleResponse>(mapper.map(roleDTO, RoleResponse.class), HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<UserResponse> createUser(UserRequest userRequest, Long roleId) {
		log.info("Inside méthode createUser in Controller GestionUserControllerImp  user: {} ,identifiant role : {}",
				userRequest.toString(), roleId);
		UserDTO userDTO = iUserRestService.createUser(mapper.map(userRequest, UserDTO.class), roleId);
		return new ResponseEntity<UserResponse>(mapper.map(userDTO, UserResponse.class), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<RoleResponse> createRole(RoleRequest roleRequest) {
		log.info("Inside méthode createRole in Controller GestionUserControllerImp roleRequest : {}",
				roleRequest.toString());
		RoleDTO roleDTO = iUserRestService.createRole(mapper.map(roleRequest, RoleDTO.class));
		return new ResponseEntity<RoleResponse>(mapper.map(roleDTO, RoleResponse.class), HttpStatus.CREATED);
	}

}
