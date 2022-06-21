package com.athena.judge.controller;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.athena.judge.entity.ClassHint;
import com.athena.judge.entity.ClassRole;
import com.athena.judge.entity.ClassSubmission;
import com.athena.judge.entity.ClassUser;
import com.athena.judge.repository.AdviceRepository;
import com.athena.judge.repository.UserRepository;

@Controller
@RequestMapping("Problems")
public class ControllerProblem {
	
	@Autowired
	private AdviceRepository AdviceService ;
	
	@Autowired
	private UserRepository UserService ;
	
	@Autowired
	private AdviceRepository HintService ;
	
	@GetMapping("/")
	public String List_Problems (Model model) {
		return "Views/problems";
	}
	@GetMapping("Hint/{id_problem}")
	public String ListHint (@PathVariable("id_problem") Integer id_problem , Model model) {
		ClassHint ch = new ClassHint();
		ch.setId_problem(id_problem);
		model.addAttribute("new_advice",ch);
		model.addAttribute("list", HintService.FindByIdProblem(id_problem));
		return "Views/ShowHintsById";
	}	
}
