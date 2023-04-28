package com.arcesi.orderservice.exceptions.handlers;

import java.util.Map;
import java.util.Set;

import com.arcesi.orderservice.enums.ErrorsCodeEnumeration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConstraintsErrorsDTO {

	private Integer httpCode;
	private ErrorsCodeEnumeration codeEnum;
	private String message;
	private Map<String,String> lstErrors;
	private String timeStamp;
}
