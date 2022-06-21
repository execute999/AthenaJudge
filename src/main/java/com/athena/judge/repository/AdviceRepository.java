package com.athena.judge.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.athena.judge.entity.ClassHint;


@Repository
public interface AdviceRepository extends CrudRepository< ClassHint , Integer>{
	@Query(
			value = "select * from athena.table_advice where id_problem = ?1",    
			nativeQuery = true
	)    
	List<ClassHint> FindByIdProblem( @Param("id_problem") Integer id_problem );
	
}
