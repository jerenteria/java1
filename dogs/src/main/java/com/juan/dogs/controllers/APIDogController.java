package com.juan.dogs.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juan.dogs.models.Dog;
import com.juan.dogs.services.DogService;

@RestController
@RequestMapping("/api") // Adds /api to the beginning of any endpoint
public class APIDogController {
	@Autowired
	private DogService dService;
	
	// Routes
	// api/
	@GetMapping("/")
	public List<Dog> allDogs() {
		return this.dService.getAllDogs();
	}
	
	//Return single Dog by ID
	// /api/{id}
	@GetMapping("(id)")
	public Dog getOneDog(@PathVariable("id")Long id) {
		return this.dService.getSingleDog(id);
	}
	
	// Create Dog by taking information from frontend
	@PostMapping("/")
	public Dog create(Dog newDog) {
		return this.dService.createDog(newDog);
	}
	
	// Update Dog
	@PutMapping("/update/{id}")
		public Dog edit(@PathVariable("id")Long id, Dog updatedDog) {
			return this.dService.updateDog(updatedDog);
		}
	// Delete Dog by ID
	@DeleteMapping("/delete/{id}")
		public void deleteDog(@PathVariable("id")Long id) {
			this.dService.deleteDog(id);
		}
}
