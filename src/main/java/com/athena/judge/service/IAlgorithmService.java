package com.athena.judge.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.athena.judge.entity.ClassAlgorithm;
@Service // maybe delete this
public interface IAlgorithmService {
	public List<ClassAlgorithm > Get_List();
	public void Save(ClassAlgorithm algorithm);
	public ClassAlgorithm Find_by_Id(int id);
	public void Delete (int id);
}
