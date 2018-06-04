package com.xl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.xl.entity.Person;

/**
  * TODO mybatis 整合
  * 
  * @author xl
  * @date 2017年3月3日 下午8:37:51
  */
@Mapper
public interface IPersonDao {
	
	@Select("select * from person where name=#{name}")
	public Person findUserByName(@Param("name") String name);
	
	@Select("select * from person")
	public List<Person> findAll();
	
	@Insert("insert into person(name,age,sex) value(#{person.name},#{person.age},#{person.sex})")
	public boolean addPerson(@Param("person") Person person);
	
	@Delete("delete from person where age=#{age}")
	public boolean delPerson(@Param("age") Integer age);
	
}
