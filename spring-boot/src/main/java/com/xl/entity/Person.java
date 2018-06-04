package com.xl.entity;

import java.io.Serializable;


/**
  * TODO Person实体类
  * 
  * @author xl
  * @date 2017年3月3日 下午8:38:54
  */
@SuppressWarnings("serial")
public class Person implements Serializable{
	
	private String name;
	private Integer age;
	private String sex;
	
	
	public Person() {
		super();
	}

	public Person(String name, Integer age, String sex) {
		super();
		this.name = name;
		this.age = age;
		this.sex = sex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", sex=" + sex + "]";
	}
	
}
