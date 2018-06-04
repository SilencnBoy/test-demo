package com.hoyatod.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoyatod.dao.IUserDao;
import com.hoyatod.entity.News;
import com.hoyatod.entity.User;
import com.hoyatod.service.IUserService;

@Service("UserServiceImpl")
@Transactional
public class UserServiceImpl implements IUserService{
	
	private static Logger log = Logger.getLogger(IUserService.class);
	
	@Autowired  
	private IUserDao userDao;

	@Override
	public Integer addUsers(User user) {
		try {
			if(user != null){
				String username = user.getUsername();
				String password = user.getPassword();
				if(username ==null || password == null){
					return -1;
				}else if (username != null && username.trim().length() < 6 || password != null && password.trim().length() < 6) {
					return -1;
				}else{
					 Integer addUsers = userDao.addUsers(user);
					 if(addUsers != null && addUsers > 0){
						 return 0;
					 }else{
						 return 1;
					 }
				}
			}else{
				return 1;
			}
		} catch (Exception e) {
			log.info("系统异常", e);
			return 9;
		}
	}

	@Override
	public User findByUsermame(String username) {
		try {
			User findByUsermame = null;
			if(username != null && !username.equals("") && !username.equals("null")){
				findByUsermame = userDao.findByUsermame(username);
				return findByUsermame;
			}else{
				return findByUsermame;
			}
		} catch (Exception e) {
			log.info("系统异常", e);
			return null;
		}
	}

	@Override
	public List<User> getUsersList() {
		try {
			List<User> usersList = userDao.getUsersList();
			return usersList;
		} catch (Exception e) {
			log.info("系统异常", e);
			return null;
		}
	}

	@Override
	public List<News> getNewsList() {
		try {
			List<News> newsList = userDao.getNewsList();
			return newsList;
		} catch (Exception e) {
			log.info("系统异常", e);
			return null;
		}
	}

}
