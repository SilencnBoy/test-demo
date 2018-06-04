package com.hoyatod.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hoyatod.entity.News;
import com.hoyatod.entity.User;
import com.hoyatod.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class UserServiceTest {
	
	@Autowired
	private IUserService userServiceDao;
	
	@Test
	public void testAddUsers(){
		Integer addUsers = userServiceDao.addUsers(new User("hoyatod","hoyatod"));
		System.out.println(addUsers);
	}
	
	@Test
	public void testFindByUsermame(){
		User findByUsermame = userServiceDao.findByUsermame("null");
		System.out.println(findByUsermame);
	}
	
	@Test
	public void testGetUsersList(){
		List<User> usersList = userServiceDao.getUsersList();
		System.out.println(usersList);
	}
	@Test
	public void testGetNewsList(){
		List<News> usersList = userServiceDao.getNewsList();
		System.out.println(usersList);
	}
}
