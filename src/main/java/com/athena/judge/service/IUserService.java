package com.athena.judge.service;

import java.util.ArrayList;
import java.util.List;

import com.athena.judge.entity.ClassUser;


public interface IUserService {
	public ClassUser Find_by_Id(int id);
	public void Save(ClassUser user);
	public List<ClassUser> Get_List_Top_N(int n);
	public List<ClassUser> FindAll();
	public List<ClassUser> Get_List();
	public List<ClassUser> FindByEmail(String email);
	public List<Integer> FindProblemsSolved(int id_user);
	public Integer FindByStatus(int id_user, String status);
	public List<ClassUser> AtLeastNProblemsSolved(Integer AtLeast);
	public Integer isSubscribed(int idUser, int idContest);
	public void subscribe(int idUser,int idContest);
}
