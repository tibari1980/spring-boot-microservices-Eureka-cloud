package com.arcesi.orderservice.external.client.model;

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
	private String timeStamp;

}
