package com.athena.judge.service;

import java.util.List;

import com.athena.judge.entity.ClassHint;

public interface IAdviceService {
	public List<ClassHint> FindAll();
	public void Save(ClassHint Advice);
	public List<ClassHint> FindByIdProblem(int id_problem);
}
