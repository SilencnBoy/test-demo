package com.xul.test1;


import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisTest1 {
	
	 public static void main(String[] args) {
		 
		   	JedisPool pool;
	        JedisPoolConfig config = new JedisPoolConfig();//创建redis连接池
	        config.setMaxTotal(300);// 设置最大连接数，-1无限制
	        config.setMaxIdle(100);// 设置最大空闲连接
	        config.setMaxWaitMillis(100000);// 设置最大阻塞时间，记住是毫秒数milliseconds
	        pool = new JedisPool(config, "127.0.0.1", 6379,200000); // 创建连接池
	        
	      for (int i =1; i <=10000; i++) {//这里自己设置用多少线程并发访问
				
				new ThreadToMysql(i,pool).start();
				 
		  }
	 }
}
