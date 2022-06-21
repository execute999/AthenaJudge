package com.athena.judge.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.athena.judge.entity.ClassHint;
import com.athena.judge.repository.AdviceRepository;

@Service
public class AdviceServiceImpl implements IAdviceService {

	@Autowired
	private AdviceRepository advice_repository;
	
	@Override 
	public List<ClassHint> FindAll() {
		return (ArrayList<ClassHint>) advice_repository.findAll();
	}
	
	@Override
	public void Save(ClassHint Advice) {
		advice_repository.save(Advice);
	}

	@Override
	public List<ClassHint> FindByIdProblem(int id_problem) {
		return advice_repository.FindByIdProblem(id_problem);
	}
	
}
