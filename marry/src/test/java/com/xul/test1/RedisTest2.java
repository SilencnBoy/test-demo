package com.xul.test1;

import org.junit.Test;

import com.xul.util.RedisCacheUtil;

public class RedisTest2 {
	
	@Test
	public void setKey(){
		RedisCacheUtil.set("Hello", "Hello world!");
		
		System.out.println(RedisCacheUtil.get("Hello"));
	}
}
