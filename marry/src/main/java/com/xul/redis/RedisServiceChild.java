package com.xul.redis;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.StringRedisTemplate;

public class RedisServiceChild {
	
	private StringRedisTemplate stringRedisTemplate;
	
	/**
	 * 设置redis 过期时间
	 * 
	 * @param key
	 * @param time
	 */
	public void expire(String key, long time) {
		stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);// 单位为秒
	}
	
	public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
		this.stringRedisTemplate = stringRedisTemplate;
	}

	public RedisConnection getConnection() {
		return stringRedisTemplate.getConnectionFactory().getConnection();
	}

	public void setValue(String key, String value) {
		stringRedisTemplate.opsForValue().set(key, value);
	}

	public String getValue(String key) {
		return stringRedisTemplate.opsForValue().get(key);
	}

	public void removeValue(String key) {
		stringRedisTemplate.delete(key);
	}

	public boolean stringHasKey(String key) {
		return stringRedisTemplate.hasKey(key);
	}

	public void bgSave() {
		RedisConnection conn = getConnection();
		conn.bgSave();
		conn.close();
	}
}
