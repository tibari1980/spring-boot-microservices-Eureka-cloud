package com.arcesi.orderservice.external.client.decoder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;

import com.arcesi.orderservice.external.client.exception.RessourceNotFoundException;
import com.arcesi.orderservice.external.client.model.ErrorsDTO;
import com.arcesi.orderservice.external.client.model.ExceptionMessage;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.Response;
import feign.codec.ErrorDecoder;

public class RetrieveMessageErrorDecoder implements ErrorDecoder {

	private final ErrorDecoder errorDecoder = new Default();

	@Override
	public Exception decode(String methodKey, Response response) {
		ObjectMapper objectMapper = new ObjectMapper();
		ErrorsDTO dto = new ErrorsDTO();
		try (InputStream body = response.body().asInputStream()) {
			ExceptionMessage message = ExceptionMessage.builder()
					.timeStramp((String) response.headers().get("date").toArray()[0]).status(response.status())
					.error(HttpStatus.resolve(response.status()).getReasonPhrase())
					.message(IOUtils.toString(body, StandardCharsets.UTF_8)).path(response.request().url()).build();
			String json = message.getMessage();
			dto = objectMapper.readValue(json, ErrorsDTO.class);
			System.out.println("bonjour :" + dto);

		} catch (IOException exception) {
			return new Exception(exception.getMessage());
		}
		switch (response.status()) {
		case 400:
			throw new RessourceNotFoundException(dto.getMessage(), dto.getCodeEnum());
		case 404:
			throw new RessourceNotFoundException(dto.getMessage(), dto.getCodeEnum());
		default:
			return errorDecoder.decode(methodKey, response);
		}

	}

}
