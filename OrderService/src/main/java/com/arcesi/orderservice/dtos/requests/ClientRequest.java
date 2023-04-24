package com.arcesi.orderservice.dtos.requests;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long codeClient;
	private String uidClient;
	private String nomClient;
	private String prenomClient;
	private String adressePostaleClient;
	private String email;
	@Column(name = "DATE_NAISSANCE_CLIENT", updatable = true, insertable = true, nullable = false)
	private Date dateNaissanceClient;
	private String sexClient;
	private String telephoneClient;
}
