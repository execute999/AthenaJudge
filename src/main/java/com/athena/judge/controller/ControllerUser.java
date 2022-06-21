package com.athena.judge.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.athena.judge.entity.ClassAlgorithm;
import com.athena.judge.entity.ClassProblem;
import com.athena.judge.entity.ClassRole;
import com.athena.judge.entity.ClassSubmission;
import com.athena.judge.entity.ClassUser;
import com.athena.judge.service.IProblemService;
import com.athena.judge.service.IRoleService;
import com.athena.judge.service.ISubmissionService;
import com.athena.judge.service.IUserService;

@Controller
@RequestMapping("/User")
public class ControllerUser {
	
	@Autowired
	private IProblemService ProblemService;
	
	@Autowired
	private IUserService UserService;
	@Autowired
	private ISubmissionService SubmissionService;
	
	@Autowired
	private DataSource datasource;
	
	@Autowired
	private BCryptPasswordEncoder passEncoder;

	
	@PostMapping("/TestSubmission")
	public String  TestSubmission(@ModelAttribute ClassSubmission submission) {	
		ClassProblem cp = ProblemService.Find_by_Id(submission.getId_problem());
		String redirect = "redirect:/WA";
	    if (cp.getSolution().equals(submission.getAnswer())) {
			cp.setSolved_by(cp.getSolved_by() + 1);
			ProblemService.Save(cp);
			submission.setStatus("AC");
			ClassUser cu = UserService.Find_by_Id(submission.getId_user());
			cu.setSolved(cu.getSolved() + 1);
			UserService.Save(cu); 
			redirect = "redirect:/AC";
		}
		else {
			submission.setStatus("WA");	
		}
		SubmissionService.Save(submission);
		return redirect;
	}
	
	@GetMapping("/Submit/{id_problem}")
	public String Edit(@PathVariable("id_problem") Integer id_problem , Model model) {
		
		org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email_user = authentication.getName(); // THE HAPINESS IN A LINE OF CODE
		
		List<ClassUser> List = UserService.FindByEmail(email_user);
		
		ClassUser UserFound = List.get(0);
		
		Integer id_user = UserFound.getId_user(); 
		ClassSubmission cs = new ClassSubmission();
		cs.setId_problem(Integer.valueOf(id_problem));
		cs.setId_user(Integer.valueOf(id_user));
		model.addAttribute("new_submission", cs);
		
		// i have to review if the user solved the problem
		ArrayList<ClassSubmission> List_Submission = SubmissionService.Get_List_AC(id_problem, id_user);
		
		if(List_Submission.size() > 0 ) {
			
			Map<String, Integer> graphData = new TreeMap<>();
			
	        graphData.put("Accepted", ProblemService.CountStatus(id_problem, "AC"));
	        graphData.put("Wrong Answer", ProblemService.CountStatus(id_problem, "WA"));
	        // this part i don't know how work, maybe i'll have a bug jeje
	        model.addAttribute("chartData", graphData);
	       	
			return "/Views/problem_solved";
		}
		else {
			return "/Views/submit_solution"; 
		}
	}	
	
	@GetMapping("/Profile")
	public String Profile(Model model) {
		org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email_user = authentication.getName();
		List<ClassUser> list_user = UserService.FindByEmail(email_user);
		ClassUser user = list_user.get(0); // this is my current user
		
		Map<String, Integer> graphData = new TreeMap<>();
		
        graphData.put("Accepted", UserService.FindByStatus(user.getId_user(), "AC") );
        graphData.put("Wrong Answer", UserService.FindByStatus(user.getId_user(), "WA"));
      
        model.addAttribute("chartData", graphData);
		model.addAttribute("user",user);
		
		return "/Views/Profile";
	}
	
	@GetMapping ("/EnterContest/{id}")
	public String EnterContest(@PathVariable("id") Integer id , Model model) {
		org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String emailUser = authentication.getName();
		List<ClassUser> listUser = UserService.FindByEmail(emailUser);
		ClassUser user = listUser.get(0);
		int idUser = user.getId_user();
		
		int qry = UserService.isSubscribed(idUser, id);
		
		String redirect = "redirect:/solvecontest";
		if(qry == 0) {
			//redirect = "redirect:/Subscribe/" + id.toString();
			return  "redirect:/Subscribe/" + id.toString();
		}
		return "/Views/solvecontest";
	}
	
}
