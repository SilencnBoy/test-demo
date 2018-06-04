package com.xul.util;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * TODO redis缓存工具类
 * 
 * @author 徐良
 */
@SuppressWarnings("unused")
@Service
public class RedisCacheUtil {
	
	@Autowired
	private static RedisTemplate<String, Object> redisTemplate;

	/* ----------- common --------- */
	public static Collection<String> keys(String pattern) {
		return redisTemplate.keys(pattern);
	}

	public static void delete(String key) {
		redisTemplate.delete(key);
	}

	public static void delete(Collection<String> key) {
		redisTemplate.delete(key);
	}

	/* ----------- string key -------- */
	public static Object get(String key) {
		Object value = redisTemplate.opsForValue().get(key);
		return value;
	}

	public static void set(String key, Object obj) {
		if (obj == null) {
			return;
		}
		redisTemplate.opsForValue().set(key, obj);

	}

	public static void setTime(String key, Object obj, Long timeout, TimeUnit unit) {
		if (obj == null) {
			return;
		}

		if (timeout != null) {
			redisTemplate.opsForValue().set(key, obj, timeout, unit);
		} else {
			redisTemplate.opsForValue().set(key, obj);
		}
	}

	public RedisTemplate<String, Object> getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		RedisCacheUtil.redisTemplate = redisTemplate;
	}

}
