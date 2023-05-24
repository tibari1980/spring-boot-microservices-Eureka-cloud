package com.arcesi.identityservice.dtos;

import java.io.Serializable;
import java.time.Instant;
import java.util.Collection;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class UserDTO implements Serializable {

	private static final long serialVersionUID = -6515559816600750171L;

	private Long id;
	private String uidUser;
	@NotBlank(message = "First name  must not be empty.")
	@Size(min = 4, max = 40, message = "First name must have a minimum of 5 characters and a maximum of 40 characters.")
	@Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "First name  require alphanumiric and space .")
	private String firstName;
	@NotBlank(message = "Last name  must not be empty.")
	@Size(min = 4, max = 40, message = "Last name must have a minimum of 5 characters and a maximum of 40 characters.")
	@Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "Last name require alphanumiric and space .")
	private String lastName;
	@Pattern(regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$", message = "Email is not valid!!")
	private String email;
	@NotBlank(message = "Password must not be empty.")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", message = "Password must have: uppercase letter, lowercase letter, special characters. between 8 and 20")
	private String password;
	private Collection<RoleDTO> roleDTOs;
	private Boolean locked;
	private Boolean enabled;
	private String ipAdresse;
	private Instant createdAt;
	private Instant updatedAt;

}
