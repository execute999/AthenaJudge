package com.athena.judge.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.athena.judge.entity.ClassCounter;
import com.athena.judge.service.IProblemService;
import com.athena.judge.service.IUserService;

@Controller
@RequestMapping("/")

public class ControllerRankList {
	
	@Autowired
	private IUserService UserService;
	
	@GetMapping("/RankList")
	public String index(Model model) {
		ClassCounter counter = new ClassCounter();
		counter.setInit(0);
		model.addAttribute("list", UserService.Get_List_Top_N(10));
		model.addAttribute("counter",counter);
		return "/Views/RankList";
	}
}
