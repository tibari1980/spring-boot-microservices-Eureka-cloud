package com.arcesi.orderservice.exceptions.handlers;

import java.util.Set;

 

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
	private com.arcesi.orderservice.enums.ErrorsCodeEnumeration codeEnum;
	private String message;
	private Set<String> lstErrors;
	private String timeStamp;

}
