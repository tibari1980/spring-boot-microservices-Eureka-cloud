package com.arcesi.orderservice.dtos;

import java.util.Date;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
public class ClientDTO extends AbstractDTO {

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
