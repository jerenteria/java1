package com.juan.test.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.juan.test.models.User;
import com.juan.test.repositories.UserRepository;

@Component
public class UserValidator {
	@Autowired
	private UserRepository uRepo;
	
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}
	
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		
		if(!user.getPassword().equals(user.getConfirmPassword())) {
			errors.rejectValue("password", "Match", "Hey you, passwords Do Not Match");
		}
			// Make sure email is unique in database
			if(this.uRepo.existsByEmail(user.getEmail())) {
				errors.rejectValue("email", "Unique", "Email has already been registered there, person!");
			}

	}
}
