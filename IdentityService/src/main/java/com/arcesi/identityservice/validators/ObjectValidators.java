package com.arcesi.identityservice.validators;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

@Component
public class ObjectValidators<T> {

	@Autowired
	private Validator validator;

	public Map<String, String> validate(T objectToValidate) {
		Set<ConstraintViolation<T>> violations = validator.validate(objectToValidate);

		if (!violations.isEmpty()) {
			final Map<String, String> mapsErrors = new HashMap<>();
			for (ConstraintViolation<T> con : violations) {
				String message = con.getMessageTemplate();
				String attribut = con.getPropertyPath().toString();
				mapsErrors.put(attribut, message);

			}
			// return
			// violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toSet());
			return mapsErrors;
		}
		return Collections.emptyMap();
	}
}
