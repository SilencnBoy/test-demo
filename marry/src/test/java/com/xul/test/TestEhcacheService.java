package com.xul.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xul.service.IEhcacheService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml","classpath:applicationEmail.xml","classpath:applicationFreemarker.xml"})
public class TestEhcacheService {
	
	@Autowired
	private IEhcacheService dao;
	
	@Test
	public void testGetEhcache() throws InterruptedException{
		
		System.out.println("第一次"+dao.getEhcache("param"));
		Thread.sleep(2000);
     	System.out.println("2秒后" + dao.getEhcache("param"));
        Thread.sleep(6000);
        System.out.println("再过6秒" + dao.getEhcache("param"));
		
	}
}
