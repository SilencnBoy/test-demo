package com.xul.service;

import java.util.List;

import com.xul.entity.User;

public interface IUserService {

	public List<User> getUsers();

	public boolean addUsers(User user);

	public User getUsersByname(String username);

	public boolean updatePwd(User user);
}
