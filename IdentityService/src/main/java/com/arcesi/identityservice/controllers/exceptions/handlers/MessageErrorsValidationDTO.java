package com.arcesi.identityservice.controllers.exceptions.handlers;

import java.util.Map;

import com.arcesi.identityservice.enums.ErrorsCodeEnumeration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageErrorsValidationDTO {

	private Integer httpCode;
	private ErrorsCodeEnumeration codeEnum;
	private String message;
	private Map<String, String> mapsErrors;
	private String timeStamp;
}
