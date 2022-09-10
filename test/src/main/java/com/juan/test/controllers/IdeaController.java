package com.juan.test.controllers;

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


import com.juan.test.models.Idea;
import com.juan.test.models.User;
import com.juan.test.services.IdeaService;
import com.juan.test.services.UserService;

@Controller
@RequestMapping("/ideas")
public class IdeaController {
	@Autowired
	private UserService uService;
	@Autowired
	private IdeaService iService;
	
	@GetMapping("")
	public String landing(HttpSession session, Model viewModel) {
		Long userId = (Long)session.getAttribute("user_id");
		viewModel.addAttribute("ideas", this.iService.getIdeas());
		viewModel.addAttribute("user", this.uService.findOneUser(userId));
		return "dashboard.jsp";
	}
	@GetMapping("/new")		// Need Model Attribute for empty wedding object, need session to know which user is creating, viewmodel to grab session from post
	public String newIdea(@ModelAttribute("idea") Idea idea, HttpSession session, Model viewModel) {
		Long userId = (Long)session.getAttribute("user_id");
		viewModel.addAttribute("user_id", userId);
		return "add.jsp";
	}
	
	@PostMapping("/new")
	public String create(@Valid @ModelAttribute("idea") Idea idea, BindingResult result, HttpSession session, Model viewModel) {
		if(result.hasErrors()) {
			Long userId = (Long)session.getAttribute("user_id");
			viewModel.addAttribute("user_id", userId);
			return "add.jsp";
		}
		this.iService.create(idea);
		return "redirect:/ideas";
	}
	
	@GetMapping("/{id}")
	public String profile(@PathVariable("id") Long id, Model viewModel,@ModelAttribute("User") User user, @ModelAttribute("idea") Idea idea, HttpSession session) {
		Long userId = (Long)session.getAttribute("user_id");
		viewModel.addAttribute("idea", this.iService.getSingleIdea(id));
		viewModel.addAttribute("user", this.uService.findOneUser(userId));
		return "profile.jsp";
	}
	
	// Update Idea
	@PostMapping("/edit/{id}")
	public String updateIdea(@Valid @ModelAttribute("idea") Idea newIdea, BindingResult result, @PathVariable("id") Long id, Model viewModel) {
		if(result.hasErrors()) {
			viewModel.addAttribute("idea", this.iService.getSingleIdea(id));
			return "show.jsp";
		}
		this.iService.updateIdea(newIdea);
		return "redirect:/" + id;
	}
	
	@PostMapping("/addNewIdea")
	public String addNewIdea(@Valid @ModelAttribute("idea") Idea idea, BindingResult result, HttpSession session) {
		if(result.hasErrors()) {
			return "add.jsp";
		}
		Long userId = (Long)session.getAttribute("user_id");
		User userObject = this.uService.findOneUser(userId);
		idea.setCreator(userObject);
		this.iService.create(idea);
		return "redirect:/";
	}
	
	// Add likes
	@GetMapping("/like/{id}")
	public String like(@PathVariable("id") Long id, HttpSession session) {
		Long UserId = (Long)session.getAttribute("user_id");
		Long ideaId = id;
		User liker = this.uService.findOneUser(UserId);
		Idea likedIdea = this.iService.getSingleIdea(ideaId);
		this.iService.addLiker(liker, likedIdea);
		return "redirect:/dashboard";
	}
	// REMOVE LIKES
	@GetMapping("/unlike/{id}")
	public String unlike(@PathVariable("id") Long id, HttpSession session) {
		Long UserId = (Long)session.getAttribute("user_id");
		Long ideaId = id;
		User liker = this.uService.findOneUser(UserId);
		Idea likedDog = this.iService.getSingleIdea(ideaId);
		this.iService.removeLiker(liker, likedDog);
		return "redirect:/dashboard";
	}
}
