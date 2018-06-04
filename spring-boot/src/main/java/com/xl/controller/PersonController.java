package com.xl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.xl.dao.IPersonDao;
import com.xl.entity.Person;
import com.xl.service.RedisServiceParent;
import com.xl.util.DataUtil;

/**
  * TODO PersonController
  * @author xl
  * @date 2017年3月3日 下午8:37:00
  */
@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	private IPersonDao dao;
	
	@Autowired
	private RedisServiceParent redisDao;
	
	@RequestMapping(value="/",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView  index(){
		
		return new ModelAndView("index");
	}
	
	@RequestMapping(value="/index",method={RequestMethod.GET,RequestMethod.POST})
	public String init(){
		try {
			System.out.println("执行缓存");
			redisDao.setValue("key_index", "Hello World!");
			System.out.println(redisDao.getValue("key_index"));
		} catch (Exception e) {
			return new DataUtil("500","no","fail").toString();
		}
		return "Hello World!<img src=\"../resources/images/app.jpg\">";
	}
	
	@RequestMapping(value="/getUser",method={RequestMethod.GET,RequestMethod.POST})
	public Person getUser(){
		Person person = dao.findUserByName("admin");
		return person;
	}
	
	@RequestMapping(value="/add",method={RequestMethod.GET,RequestMethod.POST})
	public String add(){
		boolean flag = dao.addPerson(new Person("凤姐",28,"女"));
		if(flag){
			
			return new DataUtil("200","ok","success").toString();
		}
		return new DataUtil("500","no","fail").toString();
		
	}
}
