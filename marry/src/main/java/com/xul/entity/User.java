package com.xul.entity;

import java.io.Serializable;

/**
 * 用户类
 * 
 * @author xl
 * @see User
 * */
@SuppressWarnings("serial")
public class User implements Serializable{
	
	private Integer uid;//id
	private String username;//用户名
	private String password;//密码
	private String tel;//电话
	
	public User() {
		super();
	}

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public User(String username, String password, String tel) {
		super();
		this.username = username;
		this.password = password;
		this.tel = tel;
	}
	
	public User(Integer uid, String username, String password, String tel) {
		super();
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.tel = tel;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
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

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "User [ID编号=" + uid + ", NAME用户名=" + username + ", PASS密码=" + password + ", TEL电话=" + tel + "]";
	}
}
