package com.athena.judge.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.athena.judge.entity.ClassContest;

@Repository
public interface ContestRepository extends CrudRepository< ClassContest , Integer>{
	
}
