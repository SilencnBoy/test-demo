package com.xul.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xul.entity.User;
import com.xul.service.ICommomService;

@Controller
@RequestMapping(value="/commom",method={RequestMethod.GET,RequestMethod.POST})
public class CommomController {
	
	@Autowired
	private ICommomService< User,Integer> dao;
	
	@RequestMapping("/add")
    public void addUser(User user){
        dao.add(user);
    }
    
    @RequestMapping("/delete")
    public void deleteUser(User user){
    	dao.delete(user.getUid());
    }
    
    @RequestMapping("/update")
    public void updateUser(User user){
    	dao.update(user.getUid());
    }
    
    @ResponseBody
    @RequestMapping("/get")
    public Map<String,Object> getUserById(User user){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("mess",dao.get(2));
        return map;
    }
    
    @ResponseBody
    @RequestMapping("/getAll")
    public Map<String,Object> getUser(){
        Map<String,Object> map = new HashMap<String,Object>();
        List<User> list = dao.getAll();
        map.put("mess",list.toString());
        return map;
    }
    
    
}
