package com.athena.judge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.athena.judge.entity.ClassProblem;

@Repository
public interface ProblemRepository extends CrudRepository<ClassProblem, Integer> {
	
	@Query(
			value = "select count(*) from athena.table_submission where id_problem = ?1 and status = ?2",    
			nativeQuery = true
	)    
	Integer CountStatus( @Param("id_problem") Integer id_problem , @Param("status") String status);

	// select * from athena.table_problem where hidden = 0;

	@Query(
			value = "select * from athena.table_problem where hidden = 0",    
			nativeQuery = true
	)    
	List<ClassProblem> findVisible();
	
	@Query(
			value = "select * from athena.table_problem where hidden = 1",    
			nativeQuery = true
	)    
	List<ClassProblem> findUnvisible();
	
}
