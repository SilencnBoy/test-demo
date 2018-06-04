package com.xl.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.xl.service.RedisServiceChild;

/**
 * TODO redis 服务
 * 
 * 
 * @author xl
 */
@Service("RedisServiceParent")
public class RedisServiceParent extends RedisServiceChild{
	
	private static RedisTemplate<String, Object> redisTemplate;

	@SuppressWarnings({ "unchecked", "static-access","rawtypes" })
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
}
