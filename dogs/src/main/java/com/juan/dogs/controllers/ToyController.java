package com.juan.dogs.controllers;

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
import org.springframework.web.bind.annotation.RequestMapping;

import com.juan.dogs.models.Rating;
import com.juan.dogs.models.Toy;
import com.juan.dogs.models.User;
import com.juan.dogs.services.DogService;
import com.juan.dogs.services.RatingService;
import com.juan.dogs.services.ToyService;
import com.juan.dogs.services.UserService;

@Controller
@RequestMapping("toys")
public class ToyController {
	@Autowired
	private ToyService tService;
	@Autowired
	private DogService dService;
	@Autowired
	private UserService uService;
	@Autowired
	private RatingService rService;
	
	
	@GetMapping("new")
	public String createToy(@ModelAttribute("toy")Toy toy, Model viewModel) {
		viewModel.addAttribute("dogs", this.dService.getAllDogs());
		return "toys/new.jsp";
	}
	
	@PostMapping("")
	public String create(@Valid @ModelAttribute("toy")Toy toy, BindingResult result, Model viewModel) {
		if(result.hasErrors()) {
			viewModel.addAttribute("dogs", this.dService.getAllDogs());
			return "toys/new,jsp";
		}
		this.tService.create(toy);
		return "redirect:/";
	}
	
	@PostMapping("/toy/rate/{id}")
	public String addRating(@Valid @ModelAttribute("rating") Rating rating, BindingResult result, @PathVariable("id") Long id, Model viewModel, HttpSession session) {
		Toy toy = this.tService.getSingleToy(id);
		if(result.hasErrors()) {
			viewModel.addAttribute("toy", toy);
			return "show.jsp";
		}
		Long userId = (Long)session.getAttribute("user_id");
		User user = this.uService.findOneUser(userId);
		rating.setUser(user);
		rating.setToy(toy);
		this.rService.createRating(rating);
		return "redirect:/dashboard";
	}
}
