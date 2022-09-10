package com.juan.dogs.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.juan.dogs.models.Dog;
import com.juan.dogs.models.Rating;
import com.juan.dogs.models.Tag;
import com.juan.dogs.models.User;
import com.juan.dogs.services.DogService;
import com.juan.dogs.services.TagService;
import com.juan.dogs.services.UserService;
import com.juan.dogs.validators.UserValidator;
@Controller
public class DogController {
	@Autowired
	private DogService dService;
	@Autowired
	private TagService tService;
	@Autowired
	private UserService uService;
	@Autowired
	private UserValidator validator;
	
	@GetMapping("/")
	public String login(@ModelAttribute("user")User user) {
		return "landing.jsp";
	}
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("user")User user, BindingResult result, HttpSession session) {
		validator.validate(user, result); // Validates
		if(result.hasErrors()) {
			return "landing.jsp";
		}
		User newUser = this.uService.registerUser(user);
		session.setAttribute("user_id", newUser.getId());
		return "redirect:/dashboard";
	}
	
	@PostMapping("/login") // Matches /log in landing.jsp
	public String login(@RequestParam("loginEmail")String email, @RequestParam("loginPassword")String password, RedirectAttributes redirectAttrs, HttpSession session) {
		if(!this.uService.authenticateUser(email, password)) {
			redirectAttrs.addFlashAttribute("loginError", "Invalid Credentials");
			return "redirect:/";
		}
		
		User user = this.uService.getByEmail(email);
		session.setAttribute("user_id", user.getId());
		return "redirect:/dashboard";
	}
	
	@GetMapping("/user/{id}")
	public String userProfile(@PathVariable("id") Long id, Model viewModel) {
		viewModel.addAttribute("user", this.uService.findOneUser(id));
		return "profile.jsp";
	}
	
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	
	@GetMapping("/dashboard")
	public String index(Model viewModel, HttpSession session) {
		Long userId = (Long)session.getAttribute("user_id");
		if(userId == null) {
			return "redirect:/";
		}
		User user = this.uService.findOneUser(userId);
		List<Dog> allDogs = this.dService.getAllDogs();
		viewModel.addAttribute("allDogs", allDogs);
		viewModel.addAttribute("user", user);
		return "index.jsp";
	}
	@GetMapping("/add")
	public String addDog(@ModelAttribute("dog")Dog dog) {
		return "add.jsp";
	}
	// New way of doing forms + validations
	@PostMapping("addNewDog")
	public String addNewDog(@Valid @ModelAttribute("dog") Dog dog, BindingResult result, HttpSession session) {
		if(result.hasErrors()) {
			return "add.jsp";
		}
		Long userId = (Long)session.getAttribute("user_id");
		User userOwnerObject = this.uService.findOneUser(userId);
		dog.setOwner(userOwnerObject);
		this.dService.createDog(dog);
		return "redirect:/";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable("id")Long id, Model viewModel, @ModelAttribute("tag") Tag tag, @ModelAttribute("dog")Dog dog, @ModelAttribute("rating")Rating rating, HttpSession session) {
		Long userId = (Long)session.getAttribute("user_id");
		viewModel.addAttribute("dog", this.dService.getSingleDog(id));
		viewModel.addAttribute("user", this.uService.findOneUser(userId));
		return "show.jsp";
	}
	

	@PostMapping("/edit/{id}")
	public String updateDog(@Valid @ModelAttribute("dog")Dog newDog, BindingResult result, @PathVariable("id")Long id, Model viewModel) {
		if(result.hasErrors()) {
			viewModel.addAttribute("dog", this.dService.getSingleDog(id));
			return "show.jsp";
		}
		this.dService.updateDog(newDog);
		return "redirect:/";
	}
	
	@PostMapping("/addTag")
	public String createTag(@Valid @ModelAttribute("tag")Tag tag, BindingResult result, Model viewModel) {
		Long dogId = tag.getDog().getId();
		if(result.hasErrors()) {
			viewModel.addAttribute("dog", this.dService.getSingleDog(dogId));
			return "show.jsp";
		}
		this.tService.create(tag);
		return "redirect:/" + dogId;
	}
	// Add likes
	@GetMapping("/like/{id}")
	public String like(@PathVariable("id") Long id, HttpSession session) {
		Long UserId = (Long)session.getAttribute("user_id");
		Long petId = id;
		User liker = this.uService.findOneUser(UserId);
		Dog likedDog = this.dService.getSingleDog(petId);
		this.dService.addLiker(liker, likedDog);
		return "redirect:/dashboard";
	}
	// REMOVE LIKES
	@GetMapping("/unlike/{id}")
	public String unlike(@PathVariable("id") Long id, HttpSession session) {
		Long UserId = (Long)session.getAttribute("user_id");
		Long petId = id;
		User liker = this.uService.findOneUser(UserId);
		Dog likedDog = this.dService.getSingleDog(petId);
		this.dService.removeLiker(liker, likedDog);
		return "redirect:/dashboard";
	}
	// Old way of doing Normal HTML forms + Validations from scratch
	@PostMapping("/addDog")
	public String newDog(@RequestParam("name")String name, @RequestParam("breed")String breed, @RequestParam("age")int age, RedirectAttributes redirectAttrs) {
		ArrayList<String> errors = new ArrayList<String>();
		if(name.equals("")) {
			errors.add("Hey there, you forgot to add a name");
		}
		if(errors.size() > 0) {
			for(String e: errors) {
				redirectAttrs.addFlashAttribute("errors", e);
				return "redirect:/";
				
			}
		}
		this.dService.createDog(name, breed, age);
		return "redirect:/";
	}
	
}

