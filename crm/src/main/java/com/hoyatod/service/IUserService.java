package com.hoyatod.service;

import java.util.List;

import com.hoyatod.entity.News;
import com.hoyatod.entity.User;

public interface IUserService {
	
	/**
	 *  用户注册
	 *  @param user实体对象
	 *  @return Map<String,Object> 
	 */
	public Integer addUsers(User user);
	
	/**
	 * 	通过用户名查找用户
	 *  @param username
	 *  @return Map<String,Object>
	 */
	public User findByUsermame(String username);
	
	/**
	 * 	获取所有用户列表
	 *  @return  Map<String,Object>
	 */
	public List<User> getUsersList();
	
	/**
	 * 	获取所有新闻列表	
	 * 
	 *  @return List<News>
	 */
	public List<News> getNewsList();
}
