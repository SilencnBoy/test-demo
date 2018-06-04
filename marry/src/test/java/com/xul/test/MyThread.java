package com.xul.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xul.entity.User;
import com.xul.service.ICommomService;

@SuppressWarnings("unused")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml","classpath:applicationEmail.xml","classpath:applicationFreemarker.xml","classpath:applicationQuartz.xml"})
public class MyThread extends Thread{
	
	
	@Autowired
	private ICommomService<User, Integer> dao;
	
	@Test
	public void run() {
		System.out.println("running");
		User user;
		try {
			user = dao.get(2);
			System.out.println(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		
		for (int i =1; i <=2; i++) {//启动的线程个数
			MyThread myThread = new MyThread();
			myThread.start();
			 
		}
	}
}
