package com.xl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xl.dao.IUserDao;
import com.xl.entity.User;
import com.xl.service.UserService;

@RunWith(SpringRunner.class)  
@SpringBootTest(classes = AppConfig.class) 
public class ApplicationTests {
	
	@Autowired
	private UserService userDao;
	
	@Autowired
	private IUserDao dao;
	
	@Test
	public void testAdd() {
		User save = dao.save(new User("张三","123456"));
		System.out.println(save);
	}
	
	@Test
	public void testQuery() {
		User findOne = userDao.findUserById(1);
		System.out.println(findOne);
	
	}
	
	@Test
	public void testAll() {
		Iterable<User> findAll = dao.findAll();
		System.out.println(findAll);
	}
}
