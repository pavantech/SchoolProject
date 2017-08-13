package com.studentproject.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.studentproject.model.Login;
public class LoginValidator implements Validator {

	   @Override
	   public boolean supports(Class<?> clazz) {
	      return Login.class.isAssignableFrom(clazz);
	   }

	   @Override
	   public void validate(Object target, Errors errors) {		
	      ValidationUtils.rejectIfEmptyOrWhitespace(errors, 
	         "username", "required.username","Field username name is required.");
	      ValidationUtils.rejectIfEmptyOrWhitespace(errors, 
		 	         "password", "required.password","Field password is required.");
	      
	   }
	}
