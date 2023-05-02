package com.arcesi.orderservice.exceptions.handlers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorsDtoMessage {

	private Integer httpCode;
	private com.arcesi.orderservice.enums.ErrorsCodeEnumeration codeEnum;
	private String message;
	private String timeStamp;

}
