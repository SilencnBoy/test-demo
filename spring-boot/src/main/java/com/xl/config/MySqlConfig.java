package com.xl.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
  * TODO MySqlConfig
  * 
  * @author xl
  * @date 2017年3月3日 下午8:36:05
  */
@ConfigurationProperties(prefix="spring.datasource.")
public class MySqlConfig {

	private String url;
	private String username;
	private String password;
	private String driverClassName;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

}
