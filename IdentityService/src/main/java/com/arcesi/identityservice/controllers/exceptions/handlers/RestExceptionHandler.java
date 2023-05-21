package com.arcesi.identityservice.controllers.exceptions.handlers;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.arcesi.identityservice.exceptions.ArgumentNotValidException;
import com.arcesi.identityservice.exceptions.ArgumentNotValideEntityException;
import com.arcesi.identityservice.exceptions.EntityNotFoundException;
import com.arcesi.identityservice.exceptions.InvalidEntityException;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	DateTimeFormatter format = DateTimeFormatter.ofPattern("HHmm, dd MMM uuuu");

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErrorsNotFoundDTO> exceptionHandler(EntityNotFoundException exception, WebRequest request) {
		final HttpStatus httpStatusNotFound = HttpStatus.NOT_FOUND;
		ErrorsNotFoundDTO dto = ErrorsNotFoundDTO.builder().codeEnum(exception.getCodeErrors())
				.httpCode(httpStatusNotFound.value()).message(exception.getMessage())
				.timeStamp(Instant.now().toString()).build();

		return new ResponseEntity<ErrorsNotFoundDTO>(dto, httpStatusNotFound);
	}

	@ExceptionHandler(InvalidEntityException.class)
	public ResponseEntity<ErrorsDTO> exceptionHandler(InvalidEntityException exception, WebRequest request) {
		final HttpStatus badhHttpStatus = HttpStatus.BAD_REQUEST;
		ErrorsDTO dto = ErrorsDTO.builder().codeEnum(exception.getErrorEnum()).httpCode(badhHttpStatus.value())
				.message(exception.getMessage()).lstErrors(exception.getLstErrors())
				.timeStamp(Instant.now().toString()).build();

		return new ResponseEntity<ErrorsDTO>(dto, badhHttpStatus);
	}


	@ExceptionHandler(ArgumentNotValidException.class)
	public ResponseEntity<MessageErrorDTO> exceptionHandler(ArgumentNotValidException exception, WebRequest request) {
		final HttpStatus badhHttpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		MessageErrorDTO dto = MessageErrorDTO.builder().codeEnum(exception.getErrorEnum())
				.httpCode(badhHttpStatus.value()).message(exception.getMessage())
				.timeStamp(Instant.now().toString()).build();
		return new ResponseEntity<MessageErrorDTO>(dto, badhHttpStatus);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<MessageErrorDTO> exceptionHandler(MissingPathVariableException exception,
			WebRequest request) {
		final HttpStatus badhHttpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		MessageErrorDTO dto = MessageErrorDTO.builder().httpCode(badhHttpStatus.value()).message(exception.getMessage())
				.timeStamp(Instant.now().toString()).build();
		return new ResponseEntity<MessageErrorDTO>(dto, badhHttpStatus);
	}

	@ExceptionHandler(ArgumentNotValideEntityException.class)
	public ResponseEntity<MessageErrorsValidationDTO> exceptionHandler(ArgumentNotValideEntityException exception,
			WebRequest request) {
		final HttpStatus badhHttpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		MessageErrorsValidationDTO dto = MessageErrorsValidationDTO.builder().httpCode(badhHttpStatus.value())
				.message(exception.getMessage()).mapsErrors(exception.getLstErrors())
				.timeStamp(Instant.now().toString()).build();
		return new ResponseEntity<MessageErrorsValidationDTO>(dto, badhHttpStatus);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<MessageErrorDTO> constraintViolationException(ConstraintViolationException exception,
			WebRequest request) {
		HttpStatus badRequest = HttpStatus.BAD_REQUEST;
		final MessageErrorDTO dto = MessageErrorDTO.builder().message(exception.getMessage())
				.httpCode(badRequest.value()).timeStamp(Instant.now().toString()).build();
		return new ResponseEntity<MessageErrorDTO>(dto, badRequest);
	}

}
