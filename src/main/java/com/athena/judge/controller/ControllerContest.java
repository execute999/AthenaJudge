package com.athena.judge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.athena.judge.entity.ClassContest;
import com.athena.judge.entity.ClassUser;
import com.athena.judge.service.IContest;
import com.athena.judge.service.IProblemService;
import com.athena.judge.service.IUserService;

@Controller
@RequestMapping("/")

public class ControllerContest {
	
	@Autowired
	private IContest ContestService;
	
	@Autowired
	private IUserService UserService; 
	
	@GetMapping("/Contest")
	public String  ShowContests(Model model) {	
		List<ClassContest> list = ContestService.findAll();
		model.addAttribute("list",list);
		return "/Views/Contest";
	}
	
	
	@GetMapping("/Subscribe/{idContest}")
	public String  Subscribe(@PathVariable("idContest") Integer idContest, Model model) {	
		model.addAttribute("idContest", idContest);
		return "/Views/Subscribe";
	}
	
	@PostMapping("/Check/{idContest}")
	public String  Check(Model model, @RequestParam String sourceText, @PathVariable int idContest) {
		
		String myPass = sourceText;
		ClassContest currentContest = ContestService.findById(idContest);
		String pass = currentContest.getPassword();
		org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String emailUser = authentication.getName();
		List<ClassUser> listUser = UserService.FindByEmail(emailUser);
		ClassUser user = listUser.get(0);
		int idUser = user.getId_user();
		
		System.out.println("############################");
		System.out.println(sourceText);
		System.out.println(idContest);
		System.out.println(idUser);
		System.out.println("############################");
		
		if(pass.equals(myPass)) {
			UserService.subscribe(idUser, idContest);
			// GET THE LIST OF PROBLEMS AND SEND BY MODEL
			
			return "redirect:/Views/solvecontest";
		}
		else {
			return "/Views/Contest";
		}
	}

}
