package com.hoyatod.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hoyatod.entity.User;
import com.hoyatod.service.IUserService;

@Controller
@RequestMapping(value = "/login")
public class LoginController {
	
	@Autowired
	private IUserService userDao;
	
	@RequestMapping(value = "/ckeckLogin" , method = RequestMethod.POST)
	public String ckeckLogin(String username,String password,Model model,HttpSession session){
		if(username != null && !username.trim().equals("")){
			User users = userDao.findByUsermame(username.trim());
			if(users != null){
				if(username.equals(users.getUsername()) && password.equals(users.getPassword())){
					model.addAttribute("admin", username);
					session.setAttribute("username", username);
					return "index";
				}
				return "login";
			}
		}
		return "login";
	}
}
