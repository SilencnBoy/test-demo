package com.xul.redis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * TODO redis manager
 * 
 * @author xl
 */
@SuppressWarnings({ "unchecked", "static-access","rawtypes" })
public class RedisServiceParent extends RedisServiceChild {
	
	private static RedisTemplate<String, Object> redisTemplate;

	public void setRedisTemplate(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public static void setOValueInit(String key, Object value) {
		redisTemplate.opsForValue().set(key, value);
	}

	public static void setOValue(String key, Object value) {
		redisTemplate.opsForValue().set(key, value);
	}

	public static void removeOValue(String key) {
		redisTemplate.delete(key);
	}

	public static boolean objectHasKey(String key) {
		return redisTemplate.hasKey(key);
	}

	public static Object getOValue(String key) {
		Object o;
		o = redisTemplate.opsForValue().get(key);
		return o;
	}
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		ApplicationContext c = new ClassPathXmlApplicationContext("applicationContext-redis.xml","applicationContext.xml","applicationEmail.xml","applicationFreemarker.xml","applicationQuartz.xml");
		RedisServiceParent rd = c.getBean(RedisServiceParent.class);
		rd.setValue("qw", "你好, 世界!");
		rd.setValue("01", "Hello World!");
		System.out.println(rd.getValue("01")+rd.getValue("qw"));
		System.out.println();
		rd.removeValue("01");
		
	}

}
