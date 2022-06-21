package com.athena.judge.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.athena.judge.entity.ClassSubmission;
import com.athena.judge.entity.ClassUser;
import com.athena.judge.service.ISubmissionService;
import com.athena.judge.service.IUserService;


@Controller
@RequestMapping("Info")

public class ControllerInfo {
	
	@Autowired
	private IUserService UserService;
	@Autowired
	private ISubmissionService SubmissionService;
	
	@GetMapping("/")
	public String Profile(Model model) {
		
		// Querys about Users
		List<ClassUser> ListAll = UserService.FindAll();
		List<ClassUser> ListUser = UserService.Get_List_Top_N(1);
		List<ClassUser> ListUserAtLeastSolved1Problem = UserService.AtLeastNProblemsSolved(1);
		Integer TotUsers = ListAll.size();
		Integer TotUserSolvedAtLeast1Problem = ListUserAtLeastSolved1Problem.size();
		ClassUser BestUser = ListUser.get(0);
		ClassUser FirstUser = UserService.Find_by_Id(1); 
		
		//Querys about Submissions
		ArrayList<ClassSubmission> ListSubmissions = SubmissionService.Get_List();
		ArrayList<ClassSubmission> TotSubmissionsAC = SubmissionService.FindByStatus("AC");
		Integer TotSubmissions = ListSubmissions.size();
		Integer CountTotSubmissionsAC = TotSubmissionsAC.size();
		
		// Info will be send to view
		String info1 = "- Our first user was : " + FirstUser.getNickname();
		String info2 = "- Curremtly there are : "+ TotUsers.toString() + " registered users.";
		String info3 = "- There is a total of " +  TotSubmissions.toString() + " submissions.";
		String info4 = "- There Is a total of " + CountTotSubmissionsAC.toString() + " corect solutions.";
		String info5 = "- There are " + TotUserSolvedAtLeast1Problem.toString() + " users who solved at Least 1 problem";
		String info6 = "- Currently, the best User is  " + BestUser.getNickname() + " with " + BestUser.getSolved() + " problems solved." ;
		
		// model attribute
		model.addAttribute("info1",info1);
		model.addAttribute("info2",info2);
		model.addAttribute("info3",info3);
		model.addAttribute("info4",info4);
		model.addAttribute("info5",info5);
		model.addAttribute("info6",info6);
		return "/Views/Info";
	}	
	
}
