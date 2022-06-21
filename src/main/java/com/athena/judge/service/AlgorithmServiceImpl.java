package com.athena.judge.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.athena.judge.entity.ClassAlgorithm;
import com.athena.judge.repository.AlgorithmRepository;

@Service
public class AlgorithmServiceImpl implements IAlgorithmService {
	
	@Autowired
	private AlgorithmRepository algorithm_repository;

	@Override
	public ArrayList<ClassAlgorithm> Get_List() {
		return (ArrayList<ClassAlgorithm>) algorithm_repository.findAll();
	}

	@Override
	public void Save(ClassAlgorithm algorithm) {
		algorithm_repository.save(algorithm);
	}

	@Override
	public ClassAlgorithm Find_by_Id(int id) {
		return algorithm_repository.findById(id).orElse(null);
	}

	@Override
	public void Delete(int id) {
		algorithm_repository.deleteById(id);
	}
}
