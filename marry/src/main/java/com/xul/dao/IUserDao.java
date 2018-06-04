package com.xul.dao;

import java.util.List;

import com.xul.entity.User;

public interface IUserDao {
	
	public List<User> getUsers();
	
	public boolean addUsers(User user);
	
	public User getUsersByname(String username);
	
	public boolean updatePwd(User user);
}
