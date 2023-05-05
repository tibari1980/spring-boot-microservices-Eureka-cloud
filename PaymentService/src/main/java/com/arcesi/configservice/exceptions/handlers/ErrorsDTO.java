package com.arcesi.configservice.exceptions.handlers;

import java.time.Instant;
import java.util.Map;

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
	private String codeEnum;
	private String message;
	private Map<String, String> lstErrors;
	private Instant timeStamp;

}
