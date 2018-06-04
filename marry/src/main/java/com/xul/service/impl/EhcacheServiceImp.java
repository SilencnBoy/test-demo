package com.xul.service.impl;

import java.text.SimpleDateFormat;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xul.dao.IEhCacheDao;
import com.xul.service.IEhcacheService;
@SuppressWarnings("unused")
@Service("EhcacheServiceImp")
@Transactional
public class EhcacheServiceImp implements IEhcacheService{
	
	
	@Autowired
	private IEhCacheDao dao;
	
	@Cacheable(value="getCache",key="#param")
	public String getEhcache(String param) {
		return new SimpleDateFormat(new Date().toString()).toString();
	}

}
