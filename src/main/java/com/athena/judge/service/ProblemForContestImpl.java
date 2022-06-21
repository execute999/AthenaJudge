package com.athena.judge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.athena.judge.entity.ClassProblemsForContest;
import com.athena.judge.repository.ProblemRepository;
import com.athena.judge.repository.ProblemsForContestRepository;

@Service
public class ProblemForContestImpl implements IProblemsForContest {

	@Autowired
	private  ProblemsForContestRepository problemForContestService;
	
	@Override
	public List<ClassProblemsForContest> findAll() {
		return (List<ClassProblemsForContest>) problemForContestService.findAll();
	}

	@Override
	public ClassProblemsForContest findById(int id) {
		return problemForContestService.findById(id).orElse(null);
	}

	@Override
	public void Save(ClassProblemsForContest problem) {
		problemForContestService.save(problem);
	}

	@Override
	public List<ClassProblemsForContest> findByIdContest(int id) {
		return problemForContestService.findByIdContest(id);
	}
	
}
