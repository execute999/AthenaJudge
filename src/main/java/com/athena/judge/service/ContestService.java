package com.athena.judge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.athena.judge.entity.ClassContest;
import com.athena.judge.repository.ContestRepository;

@Service
public class ContestService implements IContest {

	@Autowired ContestRepository contestService ;
	
	@Override
	public void save(ClassContest contest) {
		contestService.save(contest);
	}

	@Override
	public void delete(int idContest) {
		contestService.deleteById(idContest);
	}

	@Override
	public List<ClassContest> findAll() {
		return (List<ClassContest>) contestService.findAll();
	}

	@Override
	public ClassContest findById(int id) {
		return  contestService.findById(id).orElse(null);
	}

	@Override
	public int isSubscribed(int idUser, int idContes) {
		// TODO Auto-generated method stub
		return 0;
	}

	

	
}
