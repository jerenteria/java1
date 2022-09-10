package com.juan.weddings.services;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juan.weddings.models.User;
import com.juan.weddings.repositories.UserRepository;


@Service
public class UserService {
	@Autowired
	private UserRepository uRepo;
	
	public User findOneUser(Long id) {
		User user = this.uRepo.findById(id).orElse(null);
		return user;
	}
	
	public List<User> allUsers() {
		return this.uRepo.findAll();
	}
	
	// Register User and Hash the password
	public User registerUser(User user) {
		// Generate the Hash
		String hash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		// Set the hashed passwor don the users password field
		user.setPassword(hash);
		// Save that new user password to the database
		return this.uRepo.save(user);
		
	}
	
	// Log in / Authenticate
	public boolean authenticateUser(String email, String password) {
		// Make sure the user logging in is who they say they are
		// step 1: try and query for the user by Email
		User user = this.uRepo.findByEmail(email);
		
		if(user == null) {
			return false;
		}
		
		// Step 2: check provided email against email in the database for user
		return BCrypt.checkpw(password, user.getPassword());
	}
	
	public User getByEmail(String email) {
		return this.uRepo.findByEmail(email);
	}
}
