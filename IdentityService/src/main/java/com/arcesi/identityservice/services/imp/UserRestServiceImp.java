package com.arcesi.identityservice.services.imp;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arcesi.identityservice.dtos.RoleDTO;
import com.arcesi.identityservice.dtos.UserDTO;
import com.arcesi.identityservice.entities.RoleBean;
import com.arcesi.identityservice.entities.UserBean;
import com.arcesi.identityservice.enums.ErrorsCodeEnumeration;
import com.arcesi.identityservice.exceptions.ArgumentNotValideEntityException;
import com.arcesi.identityservice.exceptions.EntityNotFoundException;
import com.arcesi.identityservice.repositories.RoleBeanRepository;
import com.arcesi.identityservice.repositories.UserBeanRepository;
import com.arcesi.identityservice.services.IUserRestService;
import com.arcesi.identityservice.validators.ObjectValidators;

import lombok.extern.slf4j.Slf4j;

 
@Slf4j
@Service
@Transactional
public class UserRestServiceImp implements IUserRestService {

	@Autowired
	private UserBeanRepository userRepository;
	@Autowired
	private RoleBeanRepository roleRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ObjectValidators<UserDTO> validator;

	@Autowired
	private ObjectValidators<RoleDTO> validatorRole;

	@Override
	public List<UserDTO> findAllUsers(int page, int limit) {
		log.info("Inside methode findAllUsers of UserRestServiceImp   page : {} , limit : {}", page, limit);
		if (page > 0) {
			page = page + 1;
		}
		Pageable pageable = PageRequest.of(page, limit, Sort.by("id").ascending());
		Page<UserBean> pageUsers = userRepository.findAll(pageable);

		List<UserBean> lstUsers = pageUsers.getContent();
		List<UserDTO> dtosUsers = lstUsers.stream().map(user -> modelMapper.map(user, UserDTO.class))
				.collect(Collectors.toList());
		return dtosUsers;
	}

	@Override
	public List<RoleDTO> findAllRoles(int page, int limit) {
		log.info("Inside methode findAllRoles of UserRestServiceImp   page : {} , limit : {}", page, limit);
		if (page > 0) {
			page = page + 1;
		}
		Pageable pageable = PageRequest.of(page, limit, Sort.by("codeRole").ascending());
		Page<RoleBean> pageRoles = roleRepository.findAll(pageable);

		List<RoleBean> lstRoles = pageRoles.getContent();
		List<RoleDTO> dtosRoles = lstRoles.stream().map(role -> modelMapper.map(role, RoleDTO.class))
				.collect(Collectors.toList());
		return dtosRoles;
	}

	@Override
	public RoleDTO getRoleByName(String roleName) {
		log.info("Inside getRoleByName of UserRestServiceImp  Role Name  : {}", roleName);
		Optional<RoleBean> findRole = roleRepository.findRoleBeanByRoleName(roleName);

		if (findRole.isEmpty()) {
			log.error("Role not exist with : ` {} ` in our data base try again!!", roleName);
			throw new EntityNotFoundException("Role not exist with name  : `" + roleName + "` in our data base try again!!",
					ErrorsCodeEnumeration.ROLE_NOT_FOUND);
		}

		log.info("Role finding with role name : {} , role  : {} ", roleName, findRole.get().toString());
		return modelMapper.map(findRole.get(), RoleDTO.class);
	}

	@Override
	public UserDTO getUserByEmail(String email) {
		log.info("Inside getUserByEmail of UserRestServiceImp email  : {}", email);
		Optional<UserBean> findUser = userRepository.findUserBeanByEmail(email);

		if (findUser.isEmpty()) {
			log.error("User not exist with email  : ` {} ` in our data base try again!!", email);
			throw new EntityNotFoundException("User not exist with : `" + email + "` in our data base try again!!",
					ErrorsCodeEnumeration.USER_NOT_FOUND);
		}

		log.info("User finding with email  : {} , user : {} ", email, findUser.get().toString());
		return modelMapper.map(findUser.get(), UserDTO.class);
	}

	@Override
	public UserDTO getUserByUid(String uid) {
		log.info("Inside getUserByUid of UserRestServiceImp  uid  : {}", uid);
		Optional<UserBean> findUser = userRepository.findUserBeanByUidUser(uid);

		if (findUser.isEmpty()) {
			log.error("User not exist with uid  : ` {} ` in our data base try again!!", uid);
			throw new EntityNotFoundException("User not exist with uid : `" + uid + "` in our data base try again!!",
					ErrorsCodeEnumeration.USER_NOT_FOUND);
		}

		log.info("User finding with uid  : {} , user : {} ", uid, findUser.get().toString());
		return modelMapper.map(findUser.get(), UserDTO.class);
	}

	@Override
	public void addRoleToUser(String userEmail, String roleName) {
		log.info("Inside addRoleToUser of UserRestServiceImp  userEmail : {}  Role Name : {}", userEmail, roleName);
		UserDTO userDto = getUserByEmail(userEmail);
		RoleDTO roleDto = getRoleByName(roleName);
		UserBean userBean = modelMapper.map(userDto, UserBean.class);
		RoleBean roleBean = modelMapper.map(roleDto, RoleBean.class);
		userBean.getRoleBeans().add(roleBean);
        userRepository.saveAndFlush(userBean);
		log.info("Role with role Name : ` {} ` added dto user with email : `{}`", roleName, userEmail);

	}

	@Override
	public void deleteRoleById(Long idRole) {
		log.info("Inside methode deleteRoleById of UserRestServiceImp Code Role :{}", idRole);
		RoleBean findRole = roleRepository.findById(idRole)
				.orElseThrow(() -> new EntityNotFoundException(
						"Role with id : `" + idRole + "` not found in our data base try again",
						ErrorsCodeEnumeration.ROLE_NOT_FOUND));
		if (findRole != null) {
			log.info("Delete Role id : `{}`", idRole);
			roleRepository.deleteById(idRole);
		}
		log.info("Role : `{}` deleted successfully ", findRole.toString());
	}

	@Override
	public void deleteUserById(Long idUser) {
		log.info("Inside methode deleteUserById of UserRestServiceImp Code Role :{}", idUser);
		UserBean findUser = userRepository.findById(idUser)
				.orElseThrow(() -> new EntityNotFoundException(
						"User with id : `" + idUser + "` not found in our data base try again",
						ErrorsCodeEnumeration.USER_NOT_FOUND));
		if (findUser != null) {
			log.info("Delete User  id : `{}`", idUser);
			userRepository.deleteById(idUser);
		}
		log.info("User : `{}` deleted successfully ", findUser.toString());
	}

	@Override
	public UserDTO updateUser(UserDTO userDto, Long code) {
		log.info("Inside methode updateUser in Service UserRestServiceImp : userDTO {}, IdUser : {}", userDto, code);
		Map<String, String> violations = validator.validate(userDto);
		if (!violations.isEmpty()) {

			log.error("There are errors while updating user try again ! ", violations);
			throw new ArgumentNotValideEntityException("There are errors while saving user try again !!!",
					ErrorsCodeEnumeration.USER_NOT_VALIDE, violations);
		}

		UserBean findUserInOurDB = userRepository.findById(code).orElseThrow(() -> new EntityNotFoundException(
				"User  : " + code + " not found in our data base try again", ErrorsCodeEnumeration.USER_NOT_FOUND));
		// check if user exist with email
		if (!findUserInOurDB.getEmail().equalsIgnoreCase(userDto.getEmail())) {
			Optional<UserBean> ifExistUser = userRepository.findUserBeanByEmail(userDto.getEmail());
			if (ifExistUser.isPresent()) {
				log.error("User exist with : ` {} ` in our data base try again!!", userDto.getEmail());
				throw new EntityNotFoundException(
						"User exist with : `" + userDto.getEmail() + "` in our data base try again!!",
						ErrorsCodeEnumeration.USER_NOT_FOUND);
			}
		}

		
		findUserInOurDB.setEmail(userDto.getEmail());
		findUserInOurDB.setFirstName(userDto.getFirstName());
		findUserInOurDB.setLastName(userDto.getLastName());
		findUserInOurDB.setUpdatedAt(Instant.now());
		userRepository.saveAndFlush(findUserInOurDB);
		log.info("User updated successfully : {}", findUserInOurDB);
		return modelMapper.map(findUserInOurDB, UserDTO.class);
	}

	@Override
	public RoleDTO updateRole(RoleDTO roleDto, Long code) {
		log.info("Inside methode updateRole in Service UserRestServiceImp : roleDto {}, IdRole : {}", roleDto, code);
		Map<String, String> violations = validatorRole.validate(roleDto);
		if (!violations.isEmpty()) {

			log.error("There are errors while updating role try again ! ", violations);
			throw new ArgumentNotValideEntityException("There are errors while updating role try again !!!",
					ErrorsCodeEnumeration.ROLE_NOT_VALIDE, violations);
		}

		RoleBean findRoleInOurDB = roleRepository.findById(code)
				.orElseThrow(() -> new EntityNotFoundException(
						"Role with  : `" + code + "` not found in our data base try again",
						ErrorsCodeEnumeration.ROLE_NOT_FOUND));
		// check if role exist with role name
		if (!findRoleInOurDB.getRoleName().equalsIgnoreCase(roleDto.getRoleName())) {
			Optional<RoleBean> ifExistRole = roleRepository.findByRoleNameIgnoreCase(roleDto.getRoleName());
			if (ifExistRole.isPresent()) {
				log.error("Role exist with : ` {} ` in our data base try again!!", roleDto.getRoleName());
				throw new EntityNotFoundException(
						"Role exist with name  : `" + roleDto.getRoleName() + "` in our data base try again!!",
						ErrorsCodeEnumeration.ROLE_NOT_FOUND);
			}
		}

		findRoleInOurDB.setRoleName(roleDto.getRoleName());
		findRoleInOurDB.setUpdatedAt(Instant.now());
		roleRepository.saveAndFlush(findRoleInOurDB);
		log.info("Role updated successfully : {}", findRoleInOurDB.toString());
		return modelMapper.map(findRoleInOurDB, RoleDTO.class);
	}

	@Override
	public UserDTO createUser(UserDTO userDto, Long roleId) {

		log.info("Inside methode createUser of UserRestServiceImp  UserDTO : {}  : code Role : {}", userDto, roleId);
		userDto.setCreatedAt(Instant.now());
		Map<String, String> violations = validator.validate(userDto);
		if (!violations.isEmpty()) {

			log.error("There are errors while saving product try again ! ", violations);
			throw new ArgumentNotValideEntityException("There are errors while saving user try again !!!",
					ErrorsCodeEnumeration.USER_NOT_VALIDE, violations);
		}
		RoleBean findRole = roleRepository.findById(roleId)
				.orElseThrow(() -> new EntityNotFoundException(
						"Role with id : ` " + roleId + "` not found in our data base try again !!",
						ErrorsCodeEnumeration.ROLE_NOT_FOUND));
		// check if Product Exist by Designation
		Optional<UserBean> ifExistUser = userRepository.findUserBeanByEmail(userDto.getEmail());
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

		UserBean entity = modelMapper.map(userDto, UserBean.class);
		UserBean bean = userRepository.save(entity);
		log.info("User created successfully !!", bean.toString());
		return modelMapper.map(bean, UserDTO.class);

	}

	@Override
	public RoleDTO createRole(RoleDTO roleDto) {

		log.info("Inside methode createRole of UserRestServiceImp  RoleDTO : {} ", roleDto.toString());
		roleDto.setCreatedAt(Instant.now());
		Map<String, String> violations = validatorRole.validate(roleDto);
		if (!violations.isEmpty()) {

			log.error("There are errors while saving roledto try again ! ", violations);
			throw new ArgumentNotValideEntityException("There are errors while saving role try again !!!",
					ErrorsCodeEnumeration.ROLE_NOT_VALIDE, violations);
		}

		// check if Role Exist by libelle
		Optional<RoleBean> ifExistRole = roleRepository.findByRoleNameIgnoreCase(roleDto.getRoleName());
		if (ifExistRole.isPresent()) {
			log.error("Role exist with : ` {} ` in our data base try again!!", roleDto.getRoleName());
			throw new EntityNotFoundException(
					"Role  exist with name  : `" + roleDto.getRoleName() + "` in our data base try again!!",
					ErrorsCodeEnumeration.ROLE_NOT_FOUND);
		}

		roleDto.setCodeUniqueRole(UUID.randomUUID().toString());

		RoleBean role = roleRepository.saveAndFlush(modelMapper.map(roleDto, RoleBean.class));
		log.info("Role created successfully !!", role.toString());
		return modelMapper.map(role, RoleDTO.class);

	}

}
