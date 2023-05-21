package com.arcesi.identityservice.controllers;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.arcesi.identityservice.dtos.requests.RoleRequest;
import com.arcesi.identityservice.dtos.requests.UserRequest;
import com.arcesi.identityservice.dtos.responses.RoleResponse;
import com.arcesi.identityservice.dtos.responses.UserResponse;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public interface ApiGestionUser {

	// http://localhost:8093/api/v1/usersRoles/5/users
	@PostMapping(value = "{roleId}/users", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest,
			@PathVariable(name = "roleId") @Positive(message = "Role ID must be greater than zero") Long roleId);

	// http://localhost:8093/api/v1/usersRoles/
	@PostMapping(value = "/roles", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RoleResponse> createRole(@RequestBody RoleRequest roleRequest);

	@GetMapping(value = "/allUsersActive", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserResponse>> getAllUsersActive(
			@RequestParam(name = "page", defaultValue = "0") final int page,
			@RequestParam(name = "limit", defaultValue = "10") final int limit);

	@GetMapping(value = "/allRoles", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RoleResponse>> getAllRoles(
			@RequestParam(name = "page", defaultValue = "0") final int page,
			@RequestParam(name = "limit", defaultValue = "10") final int limit);

	@GetMapping(value = "/findRoleByName/{roleName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RoleResponse> findRoleByName(@PathVariable(value = "roleName") @NotBlank String roleName);

	@GetMapping(value = "/findUserByEmail/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResponse> findUserByEmail(@PathVariable(value = "email") String email);

	@GetMapping(value = "/findUserByUid/{uid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResponse> findUserByUid(@PathVariable(value = "uid") String uid);

	@PostMapping(value = "/addRoleToUser", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addRoleToUser(@NotBlank final String userEmail, @NotBlank final String roleName);

	@DeleteMapping(value = "/{idUser}")
	public ResponseEntity<String> detelteUser(
			@PathVariable(value = "idUser") @Positive(message = "User ID must be greater than zero") Long idUser);

	@DeleteMapping(value = "/{idRole}")
	public ResponseEntity<String> detelteRole(
			@PathVariable(value = "idRole") @Positive(message = "Role ID must be greater than zero") Long idRole);

	@PutMapping(value = "/updateUser/{code}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest userRequest,
			@PathVariable(name = "code") @Positive(message = "User ID must be greater than zero") Long code);

	@PutMapping(value = "/updateRole/{code}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RoleResponse> updateRole(@RequestBody RoleRequest roleRequest,
			@PathVariable(name = "code") @Positive(message = "Role ID must be greater than zero") Long code);
}
