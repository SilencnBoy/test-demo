package com.xl.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xl.dao.IUserDao;
import com.xl.entity.User;

@RestController
@EnableAutoConfiguration 
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private IUserDao userDao;
	
	@RequestMapping(value="/index",method={RequestMethod.GET,RequestMethod.POST})
	public String init(){
		
		return "Hello World!";
	}
	
	@RequestMapping(value="/getUser",method={RequestMethod.GET,RequestMethod.POST})
	public User getUser(){
		 User user= userDao.save(new User("admin03","admin03"));
		 
		return user;
	}
	
	@RequestMapping(value="/list",method={RequestMethod.GET,RequestMethod.POST})
	public List<User> list(){
		List<User> users = new ArrayList<User>();
		Iterable<User> lists = userDao.findAll();
		for (User user : lists) {
			users.add(user);
		}
		System.out.println(users);
		return users;
	}
}
