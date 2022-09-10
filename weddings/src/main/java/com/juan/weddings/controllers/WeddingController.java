package com.juan.weddings.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.juan.weddings.models.Wedding;
import com.juan.weddings.services.UserService;
import com.juan.weddings.services.WeddingService;

@Controller
@RequestMapping("/weddings")
public class WeddingController {
	@Autowired
	private UserService uService;
	@Autowired
	private WeddingService wService;
	
	@GetMapping("")
	public String landing(HttpSession session, Model viewModel) {
		Long userId = (Long)session.getAttribute("user_id");
		viewModel.addAttribute("weddings", this.wService.getWeddings());
		viewModel.addAttribute("user", this.uService.findOneUser(userId));
		return "dashboard.jsp";
	}
	@GetMapping("/new")		// Need Model Attribute for empty wedding object, need session to know which user is creating, viewmodel to grab session from post
	public String newWedding(@ModelAttribute("wedding") Wedding wedding, HttpSession session, Model viewModel) {
		Long userId = (Long)session.getAttribute("user_id");
		viewModel.addAttribute("user_id", userId);
		return "add.jsp";
	}
	
	@PostMapping("/new")
	public String create(@Valid @ModelAttribute("wedding") Wedding wedding, BindingResult result, HttpSession session, Model viewModel) {
		if(result.hasErrors()) {
			Long userId = (Long)session.getAttribute("user_id");
			viewModel.addAttribute("user_id", userId);
			return "add.jsp";
		}
		this.wService.create(wedding);
		return "redirect:/weddings";
	}
}
