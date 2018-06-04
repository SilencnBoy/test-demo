package com.xul.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xul.entity.User;
import com.xul.service.IUserService;

@Controller
@RequestMapping(value="/user",method={RequestMethod.GET,RequestMethod.POST})
public class UserController {
	
	
	@Autowired
	private IUserService dao;
	
	/**
	 *获取所有用户 
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public List<User> getUserList(HttpServletRequest request){
		List<User> users = dao.getUsers();
		return users;
	}
	
}
