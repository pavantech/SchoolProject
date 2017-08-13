package com.schooleducation.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.schooleducation.model.Register;
public class RegisterValidator implements Validator {

	   @Override
	   public boolean supports(Class<?> clazz) {
	      return Register.class.isAssignableFrom(clazz);
	   }

	   @Override
	   public void validate(Object target, Errors errors) {		
	      ValidationUtils.rejectIfEmptyOrWhitespace(errors, 
	         "username", "required.username","Field username name is required.");
	      ValidationUtils.rejectIfEmptyOrWhitespace(errors, 
	 	         "firstname", "required.firstname","Field first name is required.");
	      ValidationUtils.rejectIfEmptyOrWhitespace(errors, 
		 	         "password", "required.password","Field password is required.");
	      ValidationUtils.rejectIfEmptyOrWhitespace(errors, 
		 	         "lastname", "required.lastname","Field lastname is required.");
	      ValidationUtils.rejectIfEmptyOrWhitespace(errors, 
		 	         "email", "required.email","Field email is required.");
	      ValidationUtils.rejectIfEmptyOrWhitespace(errors, 
		 	         "phone", "required.phone","Field phone is required.");
	   }
	}
