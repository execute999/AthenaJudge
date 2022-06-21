package com.athena.judge.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.athena.judge.entity.ClassProblem;
import com.athena.judge.repository.ProblemRepository;

@Service
public class ProblemServiceImpl implements IProblemService {
	@Autowired
	private ProblemRepository problem_repository;
	
	@Override
	public List<ClassProblem> Get_List() {
		return (ArrayList<ClassProblem>) problem_repository.findAll();
	}

	@Override
	public void Save(ClassProblem problem) {
		problem_repository.save(problem);
		
	}

	@Override
	public ClassProblem Find_by_Id(int id) {
		return problem_repository.findById(id).orElse(null);
	}

	@Override
	public void Delete(int id) {
		 problem_repository.deleteById(id);
	}

	@Override
	public Integer CountStatus(int id_problem, String status) {
		return problem_repository.CountStatus(id_problem, status);
	}

	@Override
	public List<ClassProblem> findVisible() {
		return problem_repository.findVisible();
	}

	@Override
	public List<ClassProblem> findUnvisible() {
		return problem_repository.findUnvisible();
	}

}
