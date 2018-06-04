package com.xul.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xul.entity.User;
import com.xul.service.IUserService;
/**
 * NAME    : WebChat/com.xul.controller 
 * @author : 徐良 
 * TODO    : 路由控制器
 */
@Controller
@RequestMapping("/login")
public class ViewsController {
	
	@Autowired
	private IUserService dao;
	
	@RequestMapping(value = "")
	public String index() {
		return "login";
	}

	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}
	
	/**
	 * TODO 登陆请求页面
	 */
	@RequestMapping(value="/sumit",method=RequestMethod.POST)
	public String submit(String username,String password,HttpSession session){
		try {
			List<User> users = dao.getUsers();
			for (User user : users) {
				if(username.equals(user.getUsername())&& password.equals(user.getPassword())){
					session.setAttribute("login_status", true);
					session.setAttribute("name", username);
					return "redirect:/index";
				}else {
					return "redirect:/login" ;
				}
			}
			
		} catch (Exception e) {
			return "system error";
		}
		return "login";
	}
	
	/**
	 * 退出系统关闭当前session
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();
		session.invalidate();
		
		return "redirect:/login";
	}
}
