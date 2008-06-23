package cn.hb.core.dao;

import java.io.Serializable;
import java.util.List;

/**
 * The basic GenericDao interface with CRUD methods
 */
public interface IGenericDao<T> {
	/**
	 * 根据ID获取对象.
	 * 
	 * @see HibernateGenericDao#getId(Class,Object)
	 */
	public T get(Serializable id);

	/**
	 * 获取全部对象.
	 */
	public List<T> getAll();

	/**
	 * 保存对象.
	 */
	public void save(T instance);

	/**
	 * 删除对象.
	 */
	public void remove(Object o);

	/**
	 * 根据ID删除对象.
	 */
	public void removeById(Serializable id);

	/**
	 * 取得对象的主键名,辅助函数.
	 */
	@SuppressWarnings("unchecked")
	public String getIdName(Class clazz);
}
