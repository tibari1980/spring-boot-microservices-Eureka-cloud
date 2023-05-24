package com.arcesi.configservice.exceptions.handlers;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.arcesi.configservice.exceptions.EntityNotFoundException;
import com.arcesi.configservice.exceptions.InvalidEntityException;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	DateTimeFormatter format = DateTimeFormatter.ofPattern("HHmm, dd MMM uuuu");

	
	@ExceptionHandler(InvalidEntityException.class)
	public ResponseEntity<ErrorsDTO> exceptionHandler(InvalidEntityException exception, WebRequest request) {
		final HttpStatus badhHttpStatus = HttpStatus.BAD_REQUEST;
		ErrorsDTO dto = ErrorsDTO.builder()
				.codeEnum(exception.getErrorEnum()).httpCode(badhHttpStatus.value())
				.message(exception.getMessage()).lstErrors(exception.getLstErrors()).lstErrors(exception.getLstErrors())
				.timeStamp(Instant.now())
				.build();

		return new ResponseEntity<ErrorsDTO>(dto, badhHttpStatus);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErrorsDTO> exceptionHandler(EntityNotFoundException exception, WebRequest request){
		final HttpStatus errorInternal=HttpStatus.INTERNAL_SERVER_ERROR;
		ErrorsDTO dto=ErrorsDTO.builder()
				.codeEnum(exception.getCodeErrors().name())
				.httpCode(errorInternal.value())
				.message(exception.getMessage())
				.timeStamp(Instant.now())
				.build();
		return new ResponseEntity<ErrorsDTO>(dto,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
