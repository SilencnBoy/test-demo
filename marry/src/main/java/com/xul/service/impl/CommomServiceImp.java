package com.xul.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xul.dao.ICommomDao;
import com.xul.entity.User;
import com.xul.service.ICommomService;

@Service("CommomServiceImp")
@Transactional
public class CommomServiceImp implements ICommomService<User, Integer> {

	@Autowired
	private ICommomDao <User, Integer> dao;

	@Override
	@CacheEvict(value = "commom", key = "add", allEntries = true)
	public boolean add(User entity) {
		boolean flag = dao.add(entity);
		return flag;
	}

	@Override
	@CacheEvict(value = "commom", key = "delete", allEntries = true)
	public boolean delete(Integer id) {
		boolean flag = dao.delete(id);
		return flag;
	}

	@Override
	@CacheEvict(value = "commom", key = "update", allEntries = true)
	public boolean update(Integer id) {
		boolean flag = dao.update(id);
		return flag;
	}

	@Override
	@Cacheable(value = "commom",key="#id")
	public User get(Integer id) {
		User user = dao.get(id);
		return user;
	}

	@Override
	@Cacheable(value = "commom",key="#key")
	public List<User> getAll() {
		List<User> list = dao.getAll();
		return list;
	}

}
