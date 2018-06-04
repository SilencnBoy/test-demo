package com.hoyatod.dao;

import java.util.List;

import com.hoyatod.entity.News;
import com.hoyatod.entity.User;

/**
 * 用户接口
 * @author Silencn
 */
public interface IUserDao {
	
	/**
	 *  用户注册
	 *  @param user实体对象
	 *  @return Integer -1-参数不合法  0-添加成功 1-添加失败 9-系统异常
	 */
	public Integer addUsers(User user);
	
	/**
	 * 	通过用户名查找用户
	 *  @param username
	 *  @return User
	 */
	public User findByUsermame(String username);
	
	/**
	 * 	获取所有用户列表
	 *  @return  list
	 */
	public List<User> getUsersList();
	
	/**
	 * 	获取所有新闻列表	
	 * 
	 *  @return List<News>
	 */
	public List<News> getNewsList();
	
}
