package com.arcesi.orderservice.exceptions.handlers;

import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.arcesi.orderservice.enums.ErrorsCodeEnumeration;
import com.arcesi.orderservice.exceptions.EntityNotFoundException;
import com.arcesi.orderservice.exceptions.InvalidEntityException;
import com.arcesi.orderservice.utils.IUtils;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
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
				.message(exception.getMessage()).lstErrors(exception.getLstErrors()).lstErrors(exception.getLstErrors())
				.timeStamp(IUtils.afficheDateFormatter()).build();

		return new ResponseEntity<ErrorsDTO>(dto, badhHttpStatus);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorsDTO> exceptionHandler(ConstraintViolationException exception, WebRequest request) {
		final HttpStatus badhHttpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		ErrorsDTO dto = ErrorsDTO.builder()
				.httpCode(badhHttpStatus.value())
				.codeEnum(ErrorsCodeEnumeration.PARAMETRE_NOT_VALID)
				.message(exception.getMessage())
				.timeStamp(IUtils.afficheDateFormatter()).build();

		return new ResponseEntity<ErrorsDTO>(dto, badhHttpStatus);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorsDTO> exceptionHandler(MethodArgumentTypeMismatchException exception, WebRequest request) {
		final HttpStatus badhHttpStatus = HttpStatus.BAD_REQUEST;
		ErrorsDTO dto = ErrorsDTO.builder()
				.httpCode(badhHttpStatus.value())
				.codeEnum(ErrorsCodeEnumeration.PARAMETRE_NOT_VALID)
				.message("Erros in the request try again ")
				.timeStamp(IUtils.afficheDateFormatter()).build();

		return new ResponseEntity<ErrorsDTO>(dto, badhHttpStatus);
	}
	
}
