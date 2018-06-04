package com.xul.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xul.entity.User;
import com.xul.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml","classpath:applicationEmail.xml","classpath:applicationFreemarker.xml"})
public class TestUserService {
	
	@Autowired
	private IUserService dao;
    
	@Test
	public void testGetUser() {
		try {
			List<User> users = dao.getUsers();
			//System.out.println(users);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testaddUser() {
		  try {
			  for (int i = 0; i < 1000000; i++) {
				  
				 dao.addUsers(new User("xuliang--"+i,"admin"+i,"110"+i));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testGetUserByname() {
		try {
			User usersByname = dao.getUsersByname("admin1");
			System.out.println(usersByname);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testUpdatePwd(){
		try {
			boolean flag = dao.updatePwd(new User("admin","110"));
			System.out.println(flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
