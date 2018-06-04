package com.xul.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageHelper;
import com.xul.entity.User;
import com.xul.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)      
@ContextConfiguration(locations={"classpath:applicationContext.xml","classpath:applicationEmail.xml","classpath:applicationFreemarker.xml","classpath:mybatis-config.xml"}) 
public class MyBatisTest {
	
	//mybatis 分页插件
	@Resource
	private IUserService dao;
    @Test  
    public void testPage(){  
    	
        PageHelper.startPage(2, 2); // 当前页码 和容量  
        List<User> users = dao.getUsers();
        for (User user : users) {
			System.out.println(user);
		}
    }  
}
