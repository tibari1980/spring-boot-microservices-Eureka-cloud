package com.arcesi.orderservice.entities;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "CLIENTS", uniqueConstraints = {
		@UniqueConstraint(columnNames = "CODE_CLIENT", name = "SEQUENCE_CODE_CLIENT"),
		@UniqueConstraint(columnNames = "CODE_UID_CLIENT", name = "SEQUENCE_CODE_UID_CLIENT"),
		@UniqueConstraint(columnNames = "EMAIL_CLIENT", name = "SEQUENCE_EMAIL_CLIENT") })

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ClientEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@SequenceGenerator(allocationSize = 1, name = "client_sequence", sequenceName = "client_sequence")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_sequence")
	@Column(name = "CODE_CLIENT", insertable = true, nullable = false, updatable = false, unique = true)
	private Long codeClient;
	@Column(name = "CODE_UID_CLIENT", length = 36, insertable = true, nullable = false, updatable = false, unique = true)
	private String uidClient;
	@Column(name = "NOM_CLIENT", length = 50, insertable = true, nullable = false, updatable = true)
	private String nomClient;
	@Column(name = "PRENOM_CLIENT", insertable = true, length = 50, nullable = false, updatable = true)
	private String prenomClient;
	@Column(name = "ADRESSE_CLIENT", insertable = true, updatable = true, nullable = false)
	private String adressePostaleClient;
	@Column(name = "EMAIL_CLIENT", unique = true, insertable = true, length = 100, nullable = false, updatable = true)
	private String email;
	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_NAISSANCE_CLIENT", updatable = true, insertable = true, nullable = false)
	private Date dateNaissanceClient;
	@Column(name = "SEX_CLIENT", insertable = true, updatable = true, nullable = false)
	private String sexClient;
	@Column(name = "TELEPHONE_CLIENT", insertable = true, updatable = true, nullable = false)
	private String telephoneClient;

}
