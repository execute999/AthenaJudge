package com.athena.judge.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.athena.judge.entity.ClassSubmission;
import com.athena.judge.repository.SubmissionRepository;

@Service
public class SubmissionServiceImpl implements ISubmissionService {

	@Autowired
	private SubmissionRepository SubmissionRepository;
	
	@Override
	public ArrayList<ClassSubmission> Get_List() {
		return (ArrayList<ClassSubmission>) SubmissionRepository.findAll() ;
	}

	@Override
	public void Save(ClassSubmission submission) {
		SubmissionRepository.save(submission);
	}

	@Override
	public ClassSubmission Find_by_Id(int id) {
		return SubmissionRepository.findById(id).orElse(null);
	}

	@Override
	public ArrayList<ClassSubmission> Get_List_AC(Integer id_problem, Integer id_user) {
		return SubmissionRepository.Get_List_AC(id_problem, id_user);
	}

	@Override
	public ArrayList<ClassSubmission> FindByStatus(String status) {
		return SubmissionRepository.FindByStatus(status);
	}

	

	
	
}
