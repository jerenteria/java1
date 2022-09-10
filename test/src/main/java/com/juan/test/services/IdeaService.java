package com.juan.test.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juan.test.models.Idea;
import com.juan.test.models.User;
import com.juan.test.repositories.IdeaRepository;

@Service
public class IdeaService {
	@Autowired
	private IdeaRepository iRepo;
	
	public List<Idea> getIdeas() {
		return this.iRepo.findAll();
	}
	public Idea findById(Long id) {
		return this.iRepo.findById(id).orElse(null);
	}
	public Idea create(Idea idea) {
		return this.iRepo.save(idea);
	}
	// Read
	public Idea getSingleIdea(Long id) {
		return this.iRepo.findById(id).orElse(null);
	}
	
	
	// Update
	public Idea updateIdea(Idea updatedIdea) {
		return this.iRepo.save(updatedIdea);
	}
	// Add Liker to database
	public void addLiker(User user, Idea idea) {
	// Get The list from the dog objet
		List<User> likers = idea.getLikers();
	// Add the liker
		likers.add(user);
	// Updat the database
		this.iRepo.save(idea);
	}
	
	
	// REMOVE LIKER FROM DATABASE!
	// Add Liker to database
	public void removeLiker(User user, Idea idea) {
	// Get The list from the dog objet
		List<User> likers = idea.getLikers();
	// Remove the liker
		likers.remove(user);
	// Updat the database
		this.iRepo.save(idea);
	}
}
