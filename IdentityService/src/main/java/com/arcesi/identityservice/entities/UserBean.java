package com.arcesi.identityservice.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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
@Entity
@Table(name = "USERS", uniqueConstraints = { @UniqueConstraint(columnNames = "email", name = "user_email_unique"),
		@UniqueConstraint(columnNames = "CLE_UNIQUE_USER", name = "user_uidUser_unique") })
@Builder
public class UserBean implements Serializable {
	
	private static final long serialVersionUID = -6515559816600750171L;
	@SequenceGenerator(name = "appuser_sequence", allocationSize = 1, sequenceName = "appuse_sequence")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appuser_sequence")
	@Column(name = "CODE_USER", unique = true)
	private Long id;
	@Column(name = "CLE_UNIQUE_USER", updatable = false, unique = true, insertable = true)
	private String uidUser;
	@Column(name = "FIRST_NAME", updatable = true, columnDefinition = "TEXT")
	private String firstName;
	@Column(name = "LAST_NAME", updatable = true, columnDefinition = "TEXT")
	private String lastName;
	@Column(name = "EMAIL", unique = true)
	private String email;
	@Column(name = "PASSWORD", length = 255)
	private String password;
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(
			 name="users_roles",
			 joinColumns = @JoinColumn(name="user_id"),
			 inverseJoinColumns = @JoinColumn(name="role_id")
			)
	private Set<RoleBean> roleBeans;
	@Column(name = "LOCKED")
	private Boolean locked;
	@Column(name = "ENABLED")
	private Boolean enabled ;
	
	@Column(name="IP_ADRESSE_USER",insertable = true)
	private String ipAdresse;
	
	@CreatedDate
	@Column(name="CREATED_AT",nullable = false,insertable = true,updatable = false)
	private Instant createdAt;
	
	@LastModifiedDate
	@Column(name="UPDATED_AT",updatable = true,nullable = true)
	private Instant updatedAt;

	
	 

	 

	 

}
