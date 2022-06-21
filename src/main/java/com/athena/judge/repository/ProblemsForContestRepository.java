package com.athena.judge.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.athena.judge.entity.ClassProblem;
import com.athena.judge.entity.ClassProblemsForContest;

@Repository
public interface ProblemsForContestRepository extends CrudRepository< ClassProblemsForContest , Integer> {
	@Query(
			value = "select * from athena.table_submission where (status = 'AC' and id_problem = ?1 and id_user = ?2)",    
			nativeQuery = true
	)    
	ArrayList<ClassProblemsForContest> findByIdContest( @Param("id") Integer id);
	

}	
