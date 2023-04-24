package com.arcesi.orderservice.exceptions.handlers;

import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.arcesi.orderservice.exceptions.EntityNotFoundException;
import com.arcesi.orderservice.exceptions.InvalidEntityException;
import com.arcesi.orderservice.utils.IUtils;

public class RestExceptionHandler {
	DateTimeFormatter format = DateTimeFormatter.ofPattern("HHmm, dd MMM uuuu");

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErrorsNotFoundDTO> exceptionHandler(EntityNotFoundException exception, WebRequest request) {
		final HttpStatus httpStatusNotFound = HttpStatus.NOT_FOUND;
		ErrorsNotFoundDTO dto = ErrorsNotFoundDTO.builder().codeEnum(exception.getCodeErrors())
				.httpCode(httpStatusNotFound.value()).message(exception.getMessage())
				.timeStamp(IUtils.afficheDateFormatter()).build();

		return new ResponseEntity<ErrorsNotFoundDTO>(dto, httpStatusNotFound);
	}

	@ExceptionHandler(InvalidEntityException.class)
	public ResponseEntity<ErrorsDTO> exceptionHandler(InvalidEntityException exception, WebRequest request) {
		final HttpStatus badhHttpStatus = HttpStatus.BAD_REQUEST;
		ErrorsDTO dto = ErrorsDTO.builder().codeEnum(exception.getErrorEnum()).httpCode(badhHttpStatus.value())
				.message(exception.getMessage())
				// lstErrors(exception.getLstErrors())
				.lstErrors(exception.getLstErrors()).timeStamp(IUtils.afficheDateFormatter()).build();

		return new ResponseEntity<ErrorsDTO>(dto, badhHttpStatus);
	}

}
