package com.athena.judge.repository;

import java.util.ArrayList;
import java.util.List;

//import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.athena.judge.entity.ClassUser;


@Repository
public interface UserRepository extends CrudRepository<ClassUser, Integer> {

	@Query(value = "select * from athena.table_user order by  solved desc limit ?1", nativeQuery = true)
	List<ClassUser> Get_List_Top_N(@Param("n") Integer n);

	@Query(value = "select * from athena.table_user where email_address = ?1", nativeQuery = true)
	List<ClassUser> FindByEmail(@Param("email") String email);

	@Query(value = "SELECT athena.table_submission.id_problem from athena.table_submission inner join athena.table_user on athena.table_user.id_user = athena.table_submission.id_user and athena.table_submission.id_user = ?1  and athena.table_submission.status = 'AC' order by id_problem asc", nativeQuery = true)
	List<Integer> FindProblemsSolved(@Param("id_user") Integer id_user);

	@Query(value = "select count(*) from athena.table_submission where id_user = ?1 and status = ?2 ", nativeQuery = true)
	Integer FindByStatus(@Param("id_user") Integer id_user, @Param("status") String status);

	@Query(value = " select * from athena.table_user where solved >= ?1 ", nativeQuery = true)
	List<ClassUser> AtLeastNProblemsSolved(@Param("AtLeast") Integer AtLeast);

	@Query(value = "select count(*) from athena.table_subscription where id_contest = ?2 and id_user = ?1 ", nativeQuery = true)
	int isSubscribed(@Param("idUser") Integer idUser, @Param("idContest") Integer idContest);

	
	  @Modifying
	  @Transactional
	  @Query( 
			  value = "insert into athena.table_subscription (id_contest , id_user) values ( :idContest , :idUser )"
			  , 
			  nativeQuery = true ) 
	  void subscribe( @Param("idUser") Integer idUser, @Param("idContest") Integer idContest);
	

	
}
