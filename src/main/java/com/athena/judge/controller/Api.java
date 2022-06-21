package com.athena.judge.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.athena.judge.entity.ClassHint;
import com.athena.judge.entity.ClassAlgorithm;
import com.athena.judge.entity.ClassContest;
import com.athena.judge.entity.ClassProblem;
import com.athena.judge.entity.ClassSubmission;
import com.athena.judge.entity.ClassUser;
import com.athena.judge.service.IAdviceService;
import com.athena.judge.service.IAlgorithmService;
import com.athena.judge.service.IContest;
import com.athena.judge.service.IProblemService;
import com.athena.judge.service.ISubmissionService;
import com.athena.judge.service.IUserService;

@RestController
@CrossOrigin (origins = {"*"})
@RequestMapping("/api")
public class Api {
	
	
	IProblemService problem_service;
	IAlgorithmService algo_service;
	IContest contestService;
	
	
	public Api( IProblemService problem_service,
			    IAlgorithmService algo_service  ) {
				
		this.problem_service = problem_service;
		this.algo_service = algo_service;
	}
	// ====================== PROBLEM SERVICE ======================
	@GetMapping("/API_Problems")
	public List<ClassProblem> Get_List_Problems() {
		return problem_service.Get_List();
	}
	
	
	// ====================== ALGORITHM SERVICE ======================

	@GetMapping("/API_Algorithms")
	public List<ClassAlgorithm> Get_List_Algorithms() {
		return algo_service.Get_List();
	}
	
	
	
	// ====================== USER SERVICE ======================
	@Autowired
	private IUserService UserService;
	@GetMapping("/API_Best_Users/{n}")
	public List<ClassUser> Get_List_Best_Users (@PathVariable("n") Integer n) {
		return UserService.Get_List_Top_N(n);
	}
	
	@PostMapping
	public void SaveUser(@RequestBody ClassUser user) {
		System.out.println("---------------------------------");
		System.out.println(user);
		System.out.println("---------------------------------");
		//return UserService.FindAll();
	}
	
	@GetMapping("/API_Users") 
	public List<ClassUser> Get_List_Users (@RequestHeader("Authorization") String Token) { // TESTING AUTHORIZATION
		System.out.println("My token is : " + Token);
		if(Token.equals("SecretKey")) {
			return UserService.FindAll();
		}
		else {
			return null;
		}
		
	}
	@GetMapping("/AllUsers") 
	public List<ClassUser> List_Users () { // TESTING AUTHORIZATION
		return UserService.FindAll();
		
	}
	@GetMapping("/API_UsersSolvedAtLeastNProblems/{n}")
	public List<ClassUser> SolvedAtLeastNProblems (@PathVariable("n") Integer n) {
		return UserService.AtLeastNProblemsSolved(n);
	}
	
	// ====================== ADVICE SERVICE ======================
	@Autowired
	private IAdviceService AdviceService;
	
	@GetMapping("/API_Advice_Problem/{id_problem}")
	public ClassHint Get_Advice (@PathVariable("id_problem") Integer id_problem) {
		List<ClassHint> LA = AdviceService.FindByIdProblem(id_problem);
		return LA.get(0);
	}
	@GetMapping("/API_Advice_All")
	public List<ClassHint> Get_Advice_All () {
		return AdviceService.FindAll();
	}	
	
	// ====================== Submission SERVICE ======================
	@Autowired
	private ISubmissionService SubmissionService;
	@GetMapping("/API_SubmissionByVeredict/{veredict}")
	public ArrayList<ClassSubmission> GetByStatus (@PathVariable("veredict") String veredict) {
		return SubmissionService.FindByStatus(veredict);
	}
	@GetMapping("/API_Submissions")
	public ArrayList<ClassSubmission> GetAll () {
		return SubmissionService.Get_List();
	}
	// ======================  ======================
	@GetMapping("/API/Contest/{id}")
	public ClassContest GeContest (@PathVariable("id") Integer id) {
		return contestService.findById(id);
	}
	
}
