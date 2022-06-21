package com.athena.judge.service;

import java.util.List;

import com.athena.judge.entity.ClassProblemsForContest;

public interface IProblemsForContest {
	public List<ClassProblemsForContest> findAll();
	public ClassProblemsForContest findById(int id);
	public void Save(ClassProblemsForContest problem);
	public List<ClassProblemsForContest> findByIdContest(int id);
}
