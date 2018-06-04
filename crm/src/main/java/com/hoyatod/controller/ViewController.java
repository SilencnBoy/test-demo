package com.hoyatod.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hoyatod.entity.News;
import com.hoyatod.service.IUserService;

@Controller
public class ViewController {
	
	@Autowired
	private IUserService userDao;
	
	@RequestMapping(value = "/newsList",method = {RequestMethod.GET,RequestMethod.POST},produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getListNews(){
		List<News> newsList = userDao.getNewsList();
		return newsList.toString();
	}
}
