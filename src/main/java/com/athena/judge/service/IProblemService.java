package com.athena.judge.service;

import java.util.ArrayList;
import java.util.List;

import com.athena.judge.entity.ClassProblem;


public interface IProblemService {
	public List<ClassProblem> Get_List();
	public void Save(ClassProblem problem);
	public ClassProblem Find_by_Id(int id);
	public void Delete (int id);
	public Integer CountStatus(int id_problem, String status);
	public List<ClassProblem> findVisible();
	public List<ClassProblem> findUnvisible();
}
