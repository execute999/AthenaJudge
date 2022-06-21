package com.athena.judge.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.athena.judge.entity.ClassHint;
import com.athena.judge.entity.ClassAlgorithm;
import com.athena.judge.entity.ClassProblem;
import com.athena.judge.entity.ClassProblemUser;
import com.athena.judge.entity.ClassRole;
import com.athena.judge.entity.ClassUser;
import com.athena.judge.repository.AdviceRepository;
import com.athena.judge.service.IAlgorithmService;
import com.athena.judge.service.IProblemService;
import com.athena.judge.service.IRoleService;
import com.athena.judge.service.IUserService;


@Controller
@RequestMapping("/")
public class ControllerHome {
	
	//////////////-------------SERVICES----------------////////////////////
	
	@Autowired
	private IProblemService ProblemService;
	
	@Autowired
	private IUserService UserService;
	
	@Autowired
	private IRoleService RoleService;
	
	@Autowired
	private BCryptPasswordEncoder passEncoder;

	@Autowired
	private IAlgorithmService algorithm_service;
	
	
	////////////////////////////////////////////////////////////////////////
	IAlgorithmService algo_service;
	
	public ControllerHome ( IAlgorithmService algo_service) {
		this.algo_service = algo_service;
	}
	
	////////////////////////////////////////////////////////////////////////
	
	@GetMapping( { "/" , "/home" })
	public String index(Model model) {
		List<ClassUser> List = UserService.Get_List_Top_N(10);
		model.addAttribute("list", List);
		return "home";
	}
	
	@GetMapping("/ShowProblems")
	public String List_Problems (Model model) {
		
		org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email_user = authentication.getName();
		List<ClassUser> list_user = UserService.FindByEmail(email_user);
		ClassUser user = list_user.get(0); // this is my current user
		
		List<Integer> L = UserService.FindProblemsSolved( user.getId_user() ); // problems solved by the current user
		
		HashMap<Integer, Boolean> Solved = new HashMap<Integer, Boolean>();
	
		for(Integer c : L) {
			Solved.put(c, true); // i'm filling the hashmaap with my solved problens
		}
		
		ArrayList<ClassProblemUser> SendList = new ArrayList<ClassProblemUser>(); // i ll send this list, Problem + bool
		List<ClassProblem> List_Problems = ProblemService.findVisible(); /// HHEEERRREEE
		
		for(ClassProblem cp : List_Problems) {
			
			ClassProblemUser cu = new ClassProblemUser();
			// filling the same values
			cu.setId_problem(cp.getId_problem()); cu.setDocument(cp.getDocument()); cu.setSolution(cp.getSolution()); cu.setSolved_by(cp.getSolved_by()); cu.setTitle(cp.getTitle());
			
			int id_problem = cp.getId_problem(); 
			cu.setSolved(false);
			if( Solved.get(id_problem) != null ) {
				cu.setSolved(true);
			}
			
			SendList.add(cu);
		}
		
		model.addAttribute("list",SendList);
		return "/Views/list_problems_user";
	}

	
	
	
	@GetMapping("/ShowAlgorithms") 
	public String List_Algorithms (Model model) {
		List <ClassAlgorithm> List_Algorithms = algo_service.Get_List();
		model.addAttribute("list",List_Algorithms);
		return "/Views/list_algorithms_user"; 
	}

	
	
	
	
	
	@GetMapping( { "/New_Register_User" })
	public String Register_User(Model model) {
		
		List<ClassUser> List_users = UserService.Get_List();
		ClassUser cu = new ClassUser();
		model.addAttribute("user", cu);
		model.addAttribute("list", List_users);
		return "/Views/new_register_user";
	}
	
	@PostMapping("/Save_New_User")
	public String Save_User(@ModelAttribute ClassUser user , @RequestParam("file") MultipartFile doc_algorithm ) {
		if(!doc_algorithm.isEmpty()) {
			String abs = "C://Spring//resources";
			try {
				byte[] byteimg = doc_algorithm.getBytes();
				Path rutacompleta = Paths.get(abs + "//" + doc_algorithm.getOriginalFilename());
				Files.write(rutacompleta, byteimg);
				user.setImg(doc_algorithm.getOriginalFilename());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		user.setPassword( passEncoder.encode(user.getPassword()) );
		ClassRole cr = new ClassRole();
		
		// be CAREFUL, without a role the user can't login
		cr.setRole("ROLE_USER"); // creating a ROLE_USER for the new user
		UserService.Save(user); // 
		
		cr.setId_user(user.getId_user());
		RoleService.Save(cr);
		
		return "redirect:/";
	}
	
	@GetMapping("/AC")
	public String AC() {
		return "/Views/AC"; 
	}
	
	@GetMapping("/WA")
	public String WA() {
		return "/Views/WA"; 
	}	
}
