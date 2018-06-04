package com.xul.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xul.entity.User;
import com.xul.redis.RedisServiceParent;
import com.xul.service.ICommomService;

@SuppressWarnings("static-access")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext-redis.xml","classpath:applicationContext.xml","classpath:applicationEmail.xml","classpath:applicationFreemarker.xml","classpath:applicationQuartz.xml"})
public class TestRedis {
	
	@Autowired
	private ICommomService<User, Integer> dao;
	
	@Autowired
	private RedisServiceParent redis;
	@Test
	public void testGet(){
		
//		for (int i = 1; i < 100000; i++) {
			User user = dao.get(2);
		    System.out.println(user);
//		}
	}
	
	@Test
	public void testGetAll(){
		
		List<User> user = dao.getAll();
		for (User user2 : user) {
			System.out.println(user2);
			System.out.println();
		}
	}
	
	@Test
	public void testadd(){
		
		boolean flag = dao.add(new User("admin","admin","15897899920"));
		System.out.println(flag);
	}
	
	@Test
	public void testSet(){
		
		redis.setValue("key01", "今天天气不错!");
		System.out.println("缓存成功!");
		System.out.println(redis.getValue("key01")+redis.getOValue("01"));
//		System.out.println(redis.getValue("userList"));
		redis.removeValue("userList");
		System.out.println("移出缓存");
	}
}	
