package com.athena.judge.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.athena.judge.entity.ClassSubmission;


@Repository
public interface SubmissionRepository extends CrudRepository<ClassSubmission, Integer> {
	
	@Query(
			value = "select * from athena.table_submission where (status = 'AC' and id_problem = ?1 and id_user = ?2)",    
			nativeQuery = true
	)    
	ArrayList<ClassSubmission> Get_List_AC( @Param("id_problem") Integer id_problem ,  @Param("id_user") Integer id_user);
	
	@Query(
			value = "select * from athena.table_submission where status = ?1",    
			nativeQuery = true
	)    
	ArrayList<ClassSubmission> FindByStatus( @Param("status") String status);
	
}
