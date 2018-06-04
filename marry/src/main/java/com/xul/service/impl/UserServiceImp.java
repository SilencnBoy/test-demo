package com.xul.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xul.dao.IUserDao;
import com.xul.entity.User;
import com.xul.service.IUserService;

@Service("UserServiceImp")
@Transactional(propagation=Propagation.REQUIRED)
public class UserServiceImp implements IUserService {
	
	@Autowired
	private IUserDao dao;
	
	/*@Cacheable(value="getCache")*/  //Õ®π˝¥øjava∞Êehcache≈‰÷√ª∫¥Ê
	public List<User> getUsers() {
		List<User> users = dao.getUsers();
		return users;
	}
	
	/*@CachePut(value="getCache",key="#user.getUsername()")*/
	public boolean addUsers(User user) {
		boolean flag = dao.addUsers(user);
				return flag;
	}
	
	/*@Cacheable(value="getCache",key="#username")*/
	public User getUsersByname(String username) {
		User usersByname = dao.getUsersByname(username);
		return usersByname;
	}

	/*@CachePut(value="getCache",key="#user.getUsername()")*/
	public boolean updatePwd(User user) {
		boolean flag = dao.updatePwd(user);
		return flag;
	}

}
