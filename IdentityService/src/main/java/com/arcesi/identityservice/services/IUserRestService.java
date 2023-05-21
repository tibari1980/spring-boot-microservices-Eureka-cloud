package com.arcesi.identityservice.services;

import java.util.List;

import com.arcesi.identityservice.dtos.RoleDTO;
import com.arcesi.identityservice.dtos.UserDTO;

public interface IUserRestService {

	List<UserDTO> findAllUsers(final int page, final int limit);

	List<RoleDTO> findAllRoles(final  int page,final int limit);

	RoleDTO getRoleByName(final  String roleName);

	UserDTO getUserByEmail(final String email);

	UserDTO getUserByUid(final String uid);

	void addRoleToUser(final String userEmail,final  String roleName);

	void deleteRoleById(final Long idRole);

	void deleteUserById(Long idUser);

	UserDTO updateUser( final UserDTO userDto,final Long code);

	RoleDTO updateRole(final RoleDTO roleDto,final  Long code);

	UserDTO createUser(final UserDTO userDto,final Long roleId);

	RoleDTO createRole(RoleDTO roleDto);

}
