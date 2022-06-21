package com.athena.judge.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.athena.judge.entity.ClassAlgorithm;
import com.athena.judge.entity.ClassContest;
import com.athena.judge.entity.ClassProblem;
import com.athena.judge.entity.ClassProblemSelected;
import com.athena.judge.entity.ClassUser;
import com.athena.judge.service.IAlgorithmService;
import com.athena.judge.service.IContest;
import com.athena.judge.service.IProblemService;


@Controller
@RequestMapping("Admin")
public class ControllerAdmin {
	
	@Autowired
	private IProblemService ProblemService;
	
	@Autowired
	private IAlgorithmService AlgorithmService;
	
	@Autowired
	private IContest ContestService;
	
	@GetMapping( { "/" })
	public String index() {
		// opcion de post_Problem o Post_algorithm
		return "/Views/welcome_admin";
	}
	
	// http://localhost:1135/Admin/ShowProblems
	// PROBLEMS
	
	@GetMapping("/ShowProblems") // show problems from admin's view
	public String List_Problems (Model model) {
		
		List<ClassProblem> List_Problems = ProblemService.Get_List();	
		model.addAttribute("list",List_Problems);
		return "/Views/list_problems_admin"; // i'll make a view for problems (admin)
	}
	
	
	@GetMapping( { "/PostProblem" })
	public String Post_Problem(Model model) {
		
		List<ClassProblem> List_problems = ProblemService.Get_List();
		ClassProblem  nc = new ClassProblem();
		nc.setSolved_by(0);
		model.addAttribute("new_problem", nc);
		model.addAttribute("list", List_problems);
		
		return "/Views/post_problem"; // return the form for post a problem, 
	}
	
	@PostMapping("/Save")
	public String Save(@ModelAttribute ClassProblem problem , @RequestParam("file") MultipartFile imagen ) {
		if(!imagen.isEmpty()) {
			String abs = "C://Spring//resources";
			try {
				byte[] byteimg = imagen.getBytes();
				Path rutacompleta = Paths.get(abs + "//" + imagen.getOriginalFilename());
				Files.write(rutacompleta, byteimg);
				problem.setDocument(imagen.getOriginalFilename());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		problem.setSolved_by(0);
		ProblemService.Save(problem);
		return "redirect:/ShowProblems";
	}
	
	@GetMapping("/Edit/{id}")
	public String Edit(@PathVariable("id") Integer id_problem, Model model) {
		List<ClassProblem> List_problems = ProblemService.Get_List();
		ClassProblem nc =  ProblemService.Find_by_Id(id_problem);
		model.addAttribute("new_problem", nc);
		model.addAttribute("list", List_problems);
		return "/Views/post_problem"; // return the form for post a problem 
	}
	
	@GetMapping("/Delete/{id}")
	public String Delete(@PathVariable("id") Integer id_problem) {
		ProblemService.Delete(id_problem);
		return "redirect:/Admin/ShowProblems";
	}
	
	// ALGORITHMS
	@GetMapping("/ShowAlgorithms") // show problems from admin's view
	public String List_Algorithms (Model model) {
		
		List<ClassAlgorithm > List_Algorithms = AlgorithmService.Get_List();
		model.addAttribute("list",List_Algorithms);
		return "Views/list_algorithms_admin"; 
	}
	
	@GetMapping( { "/PostAlgorithm" })
	public String Post_Algorithm(Model model) {
		
		List<ClassAlgorithm> List_algorithms = AlgorithmService.Get_List();
		ClassAlgorithm  nc = new ClassAlgorithm();
		model.addAttribute("new_algorithm", nc);
		model.addAttribute("list", List_algorithms);
		
		return "/Views/post_algorithm";
	}
	
	@PostMapping("/SaveAlgorithm")
	public String Save(@ModelAttribute ClassAlgorithm algorithm , @RequestParam("file") MultipartFile doc_algorithm ) {
		if(!doc_algorithm.isEmpty()) {
			String abs = "C://Spring//resources";
			try {
				byte[] byteimg = doc_algorithm.getBytes();
				Path rutacompleta = Paths.get(abs + "//" + doc_algorithm.getOriginalFilename());
				Files.write(rutacompleta, byteimg);
				algorithm.setDoc(doc_algorithm.getOriginalFilename());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		AlgorithmService.Save(algorithm);
		return "redirect:/Admin/ShowAlgorithms";
	}
	
	@GetMapping("/Edit_Algorithm/{id}")
	public String Edit_Algorithm(@PathVariable("id") Integer id_algorithm, Model model) {
		List<ClassAlgorithm> List_algorithms = AlgorithmService.Get_List();
		ClassAlgorithm nc =  AlgorithmService.Find_by_Id(id_algorithm);
		model.addAttribute("new_algorithm", nc);
		model.addAttribute("list", List_algorithms);
		return "/Views/post_algorithm"; // return the form for post a problem 
	}
	
	@GetMapping("/Delete_Algorithm/{id}")
	public String Delete_Algorithm(@PathVariable("id") Integer id_algorithm) {
		AlgorithmService.Delete(id_algorithm);
		return "redirect:/Admin/ShowAlgorithms";
	}	
	
	@GetMapping( { "/CreateContest" })
	public String CreateContest(Model model) {
		ClassContest contest = new ClassContest();
		model.addAttribute("new_contest", contest);		
		return "/Views/new_contest"; // return the form for post a problem,
	}
	
	@PostMapping("/SaveContest")
	public String SaveContest(@ModelAttribute ClassContest contest) {
		ContestService.save(contest);
		return "redirect:/Admin/ShowContests";
	}
	
	@GetMapping("/ShowContests")
	public String ShowContests(Model model) {
		List<ClassContest> list = ContestService.findAll();
		model.addAttribute("list" , list);
		return "Views/show_contests_admin";
	}
	
	@GetMapping("/Contest/Edit/{id}")
	public String EditContest(@PathVariable("id") Integer idContest, Model model) {
		ClassContest contest = ContestService.findById(idContest);
		
		List<ClassProblemSelected> selected = new ArrayList<ClassProblemSelected> ();
		List<ClassProblem> notVisible= ProblemService.findUnvisible();
		
		for(ClassProblem problem : notVisible) {
			ClassProblemSelected cs = new ClassProblemSelected ();
			cs.setIdProblem(problem.getId_problem());
			cs.setTitle(problem.getTitle());
			cs.setDocument(problem.getDocument());
			cs.setSelected(false);
			selected.add(cs);
		}
		
		model.addAttribute("contest", contest);
		model.addAttribute("list", selected);
		return "/Views/edit_contest"; // return the form for post a problem 
	}
	
	@PostMapping("/SaveProblemsContest")
	public void Save(@ModelAttribute ArrayList<ClassProblemSelected> list) {
		System.out.println("*****************----------------*****************");
		System.out.println("len list " + list.size());
		for(ClassProblemSelected x : list) {
			System.out.println(x);
		}
		System.out.println("*****************----------------*****************");
	}
}