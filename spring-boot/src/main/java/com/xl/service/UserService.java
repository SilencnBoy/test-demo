package com.xl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xl.dao.IUserDao;
import com.xl.entity.User;

@Service("UserService")
public class UserService {
	
	@Autowired
	private IUserDao dao;
	
	@Transactional(propagation=Propagation.REQUIRED)//开启事物
	public User findUserById(Integer id){
		
		User findUserById = dao.findUserById(id);
		return findUserById;
	}
	
}
