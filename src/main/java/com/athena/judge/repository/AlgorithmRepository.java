package com.athena.judge.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.athena.judge.entity.ClassAlgorithm;

@Repository
public interface AlgorithmRepository extends CrudRepository< ClassAlgorithm , Integer>{
	
}
