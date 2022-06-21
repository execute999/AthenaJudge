package com.athena.judge.service;

import java.util.ArrayList;

import com.athena.judge.entity.ClassSubmission;


public interface ISubmissionService {
	public ArrayList<ClassSubmission> Get_List();
	public void Save(ClassSubmission submission);
	public ClassSubmission Find_by_Id(int id);
	public ArrayList<ClassSubmission> Get_List_AC(Integer id_problem , Integer id_user);
	public ArrayList<ClassSubmission> FindByStatus(String status);
}
