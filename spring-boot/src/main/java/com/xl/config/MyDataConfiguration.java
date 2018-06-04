package com.xl.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;


/**
  * TODO 获取配置文件信息
  * 
  * @author xl
  * @date 2017年3月3日 下午8:35:21
  */
@Configuration
@EnableConfigurationProperties(MySqlConfig.class)
public class MyDataConfiguration {

	@Autowired
	private MySqlConfig config;

	public MySqlConfig getConfig() {
		return config;
	}

	public void setConfig(MySqlConfig config) {
		this.config = config;
	}
	
	
	
}
