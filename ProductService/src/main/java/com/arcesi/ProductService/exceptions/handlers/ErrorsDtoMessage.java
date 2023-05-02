package com.arcesi.ProductService.exceptions.handlers;

import com.arcesi.ProductService.enums.ErrorsCodeEnumeration;

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
	private ErrorsCodeEnumeration codeEnum;
	private String message;
	private String timeStamp;

}
