package com.xl.dao;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

import com.xl.entity.User;

/**
  * TODO JPA hibernate整合
  * 
  * @author xl
  * @date 2017年3月3日 下午8:38:26
  */
@Repository  
@Table(name="user")  
@Qualifier("userDao")
public interface IUserDao extends CrudRepository<User,Long>{
	
	public User findUserById(Integer id);
	
}
