package com.arcesi.orderservice.external.client.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.arcesi.orderservice.external.client.model.ErrorsDTO;
import com.arcesi.orderservice.utils.IUtils;

@RestControllerAdvice
public class ApiRessourceExterneExceptionHanlder extends ResponseEntityExceptionHandler {

	@ExceptionHandler(RessourceNotFoundException.class)
	public ResponseEntity<ErrorsDTO> handelExceptionProduct(RessourceNotFoundException exception, WebRequest request) {
		final HttpStatus badrequest = HttpStatus.BAD_REQUEST;
		ErrorsDTO dto = ErrorsDTO.builder().httpCode(badrequest.value()).codeEnum(exception.getCodeError())
				.message(exception.getMessage()).timeStamp(IUtils.afficheDateFormatter()).build();
		return new ResponseEntity<ErrorsDTO>(dto, badrequest);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorsDTO> handelExceptionProduct(Exception exception, WebRequest request) {
		final HttpStatus badrequest = HttpStatus.BAD_REQUEST;
		ErrorsDTO dto = ErrorsDTO.builder().httpCode(badrequest.value())
				.message(exception.getMessage()).timeStamp(IUtils.afficheDateFormatter()).build();
		return new ResponseEntity<ErrorsDTO>(dto, badrequest);
	}
}
