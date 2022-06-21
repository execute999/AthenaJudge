package com.athena.judge.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.athena.judge.entity.ClassHint;
import com.athena.judge.entity.ClassUser;
import com.athena.judge.repository.AdviceRepository;
import com.athena.judge.service.IUserService;


@Controller
@RequestMapping("Hint")
public class ControllerHint {
	
	@Autowired
	private AdviceRepository hint_service ;

	@Autowired
	private IUserService user_service;
	
	@GetMapping("/{id_problem}")
	public String ListHint (@PathVariable("id_problem") Integer id_problem , Model model) {
		ClassHint ch = new ClassHint();
		ch.setId_problem(id_problem);
		model.addAttribute("new_advice",ch);
		model.addAttribute("list", hint_service.FindByIdProblem(id_problem));
		
		return "Views/ShowHintsById";
	}
	
	@PostMapping("/PostHint")
	public String  TestSubmission(@ModelAttribute ClassHint hint) {	
		org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		String email_user = authentication.getName(); // THE HAPINESS IN A LINE OF CODE
		List<ClassUser> List = user_service.FindByEmail(email_user);
		ClassUser User = List.get(0);
		
		hint.setNickname_user(User.getNickname());
		hint.setId_user(User.getId_user());
		hint.setDate(hint.getCurrentTime());
		
		hint_service.save(hint);
		
		return "redirect:/Hint/" + String.valueOf(hint.getId_problem());
	}
	
}
