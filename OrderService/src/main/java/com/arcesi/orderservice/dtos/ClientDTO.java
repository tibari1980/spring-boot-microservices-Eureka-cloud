package com.arcesi.orderservice.dtos;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ClientDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long codeClient;
	private String uidClient;
	private String nomClient;
	private String prenomClient;
	private String adressePostaleClient;
	private String email;
	private Date dateNaissanceClient;
	private String sexClient;
	private String telephoneClient;

}
