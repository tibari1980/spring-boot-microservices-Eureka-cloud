package com.arcesi.configservice.validators;

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

    public Map<String, String> validate(T objectToValidate){
    	Set<ConstraintViolation<T>> violations = validator.validate(objectToValidate);
    	if(!violations.isEmpty()) {
    		final Map<String, String> mapErrors=new HashMap<String,String>();
    		for(ConstraintViolation<T> con:violations) {
    			final String message=con.getMessageTemplate();
    			final String fieldName=con.getPropertyPath().toString();
    			mapErrors.put(message, fieldName);
    		}
    		return mapErrors;
    	}
    	return Collections.emptyMap();
    }
}
