package com.xul.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * json����ת��������
 */
public class JsonUtil {
	private static ObjectMapper mapper = new ObjectMapper();

	/**
	 * ����toJSON
	 * 
	 * @param object
	 * @return
	 */
	public static String getString(Object object) {
		try {
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * jsonת��Bean
	 * 
	 * @param json
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public static <T> T getBean(String json, Class<T> clazz) {
		try {
			return mapper.readValue(json, clazz);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
