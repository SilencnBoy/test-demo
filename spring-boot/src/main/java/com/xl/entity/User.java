package com.xl.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
  * TODO User实体类
  * 
  * @author xl
  * @date 2017年3月3日 下午8:39:10
  */
@Entity  
@Table(name="user")
@SuppressWarnings("serial")
public class User implements Serializable{
	
	private Integer id;
	private String username;
	private String password;
	
	public User() {
		super();
	}

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public User(Integer id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}
	
	@Id  
    @GeneratedValue(strategy=GenerationType.AUTO)  
    @Column(name="id")  
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="username")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name="password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String toString() {
		return "User类--> [ID编号:" + id + ", 用户名:" + username + ", 密码:" + password + "]";
	}

}
