package com.arcesi.identityservice.controllers.exceptions.handlers;

import java.util.Set;

import com.arcesi.identityservice.enums.ErrorsCodeEnumeration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorsDTO {

	private Integer httpCode;
	private ErrorsCodeEnumeration codeEnum;
	private String message;
	private Set<String> lstErrors;
	private String timeStamp;

}
