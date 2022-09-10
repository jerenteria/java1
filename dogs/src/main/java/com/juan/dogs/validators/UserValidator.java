package com.juan.dogs.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.juan.dogs.models.User;
import com.juan.dogs.repositions.UserRepository;

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
			// Nobody name Matt can join
			if(user.getFirstName().equals("Matt")) {
				errors.rejectValue("firstName", "noMattsAllowed", "Sorry, we're not accepting matts at this time!");
			}
	}
}
