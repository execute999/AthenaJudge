package com.athena.judge.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.athena.judge.entity.ClassContest;

@Service
public interface IContest {
	public void save(ClassContest contest);
	public void delete(int idContest);
	public List<ClassContest> findAll();
	public ClassContest findById(int id);
	public int isSubscribed(int idUser, int idContes);
}
