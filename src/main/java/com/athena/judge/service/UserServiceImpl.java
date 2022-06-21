package com.athena.judge.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.athena.judge.entity.ClassUser;
import com.athena.judge.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService {

	
	@Autowired
	private UserRepository UserRepository;
	
	@Override
	public ClassUser Find_by_Id(int id) {
		return UserRepository.findById(id).orElse(null) ;
	}
	
	@Override
	public void Save(ClassUser user) {
		UserRepository.save(user);
	}
	
	@Override
	public List<ClassUser> Get_List_Top_N(int n) {
		return (UserRepository.Get_List_Top_N(n));
	}

	@Override
	public List<ClassUser> Get_List() {
		return (ArrayList<ClassUser>) UserRepository.findAll();
	}

	@Override
	public List<ClassUser> FindByEmail(String email) {
		return UserRepository.FindByEmail(email);
	}

	@Override
	public List<Integer> FindProblemsSolved(int id_user) {
		return UserRepository.FindProblemsSolved(id_user);
	}

	@Override
	public Integer FindByStatus(int id_user, String status) {
		return UserRepository.FindByStatus(id_user, status);
	}

	@Override
	public List<ClassUser> FindAll() {
		return (ArrayList<ClassUser>) UserRepository.findAll();
	}

	@Override
	public List<ClassUser> AtLeastNProblemsSolved(Integer AtLeast) {
		return UserRepository.AtLeastNProblemsSolved(AtLeast);
	}

	@Override
	public Integer isSubscribed(int idUser, int idContest) {
		return UserRepository.isSubscribed(idUser, idContest);
	}

	@Override
	public void subscribe(int idUser, int idContest) {
		UserRepository.subscribe(idUser, idContest);
	}

}