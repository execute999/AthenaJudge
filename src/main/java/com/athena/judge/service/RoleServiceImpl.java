package com.athena.judge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.athena.judge.entity.ClassRole;
import com.athena.judge.repository.RoleRepository;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private RoleRepository RoleService;
	@Override
	public void Save(ClassRole role) {
		RoleService.save(role);
	}	
}
