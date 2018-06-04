package com.xul.dao;

import java.io.Serializable;
import java.util.List;

/**
 * TODO service 层接口
 * 
 * @author 徐良
 * */
public interface ICommomDao <T, PK extends Serializable>{
	
	/**
	 * TODO 添加对象
	 * 
	 * @return boolean
	 */
	public boolean add(T entity);

	/**
	 * TODO 删除指定id对象
	 * 
	 * @return boolean
	 */
	public boolean delete(PK id);

	/**
	 * TODO 修改指定id对象
	 * 
	 * @return boolean
	 */ 
	public boolean update(PK id);

	/**
	 * TODO 获取指定id对象
	 * 
	 * @return T
	 */
	public T get(PK id);

	/**
	 * TODO 获取所有对象
	 * 
	 * @return list
	 */
	public List<T> getAll();
	
	
}
