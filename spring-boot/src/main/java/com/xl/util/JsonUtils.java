package com.xl.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * TODO json转换工具类
 * 
 * @author 徐良
 */
@SuppressWarnings("unchecked")
public class JsonUtils {

	private static Gson gson;
	private static ObjectMapper mapper = new ObjectMapper();

	private JsonUtils() {}

	static {
		GsonBuilder gb = new GsonBuilder();
		gb.setDateFormat("yyyy-MM-dd HH:mm:ss");
		gson = gb.create();
	}
	
	public static final String toJson(Object obj) {
		return gson.toJson(obj);
	}

	public static final <T> T fromJson(final String json, Class<T> clazz) {//将json转成指定类型
		return gson.fromJson(json, clazz);
	}

	public static final <T> T fromJson(final String json, Type t) {//json转任何类型
		return gson.fromJson(json, t);
	}

	public static final Map<String, Object> fromJson(final String json) {//json转换map集合
		return fromJson(json, Map.class);
	}
	
	public static String getString(Object object) {//object 转换 String
		try {
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	public static <T> T getBean(String json, Class<T> clazz) {//json转换Bean
		try {
			return mapper.readValue(json, clazz);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
