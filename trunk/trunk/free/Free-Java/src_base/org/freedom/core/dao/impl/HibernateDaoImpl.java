/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.core.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.freedom.core.dao.IGenericDao;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.Assert;

/**
 * Hibernate Dao的泛型基类
 * 
 * <pre>
 * 继承于Spring的HibernateDaoSupport
 * 提供分页函数和若干便捷查询方法，并对返回值作了泛型类型转换
 * </pre>
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class HibernateDaoImpl<T> extends HibernateDaoSupport implements IGenericDao<T> {

    protected Class<T> entityClass;// DAO所管理的Entity类型.

    /**
     * 取得entityClass.JDK1.4不支持泛型的子类可以抛开Class<T> entityClass,重载此函数达到相同效果。
     */
    protected Class<T> getEntityClass() {
        return entityClass;
    }

    /**
     * 在构造函数中将泛型T.class赋给entityClass.
     */
    @SuppressWarnings("unchecked")
    public HibernateDaoImpl() {
        entityClass = getSuperClassGenricType(getClass());
    }

    @SuppressWarnings("unchecked")
    public T get(Serializable id) {

        return (T) getHibernateTemplate().get(entityClass, id);
    }

    public void initialize(Object entityObj) {
        getHibernateTemplate().initialize(entityObj);
    }

    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        return getHibernateTemplate().loadAll(entityClass);
    }

    @SuppressWarnings("unchecked")
    public String getIdName(Class clazz) {
        Assert.notNull(clazz);
        ClassMetadata meta = getSessionFactory().getClassMetadata(clazz);
        Assert.notNull(meta, "Class " + clazz + " not define in hibernate session factory.");
        String idName = meta.getIdentifierPropertyName();
        Assert.hasText(idName, clazz.getSimpleName() + " has no identifier property define.");
        return idName;
    }

    public void delete(Object o) {
        getHibernateTemplate().delete(o);
    }

    public void deleteById(Serializable id) {
        delete(get(id));
    }

    public void save(T instance) {
        getHibernateTemplate().saveOrUpdate(instance);
    }

    @SuppressWarnings("unchecked")
    protected Class getSuperClassGenricType(Class clazz) {
        return (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        // return getSuperClassGenricType(clazz, 0);
    }

    @SuppressWarnings("unchecked")
    /**
     * 通过反射,获得定义Class时声明的父类的范型参数的类型. 如public BookManager extends GenricManager<Book>
     * 
     * @param clazz clazz The class to introspect
     * @param index the Index of the generic declaration,start from 0.
     * @return the index generic declaration, or <code>Object.class</code> if
     *         cannot be determined
     */
    protected Class getSuperClassGenricType(Class clazz, int index) {
        Type genType = clazz.getGenericSuperclass();

        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if (index >= params.length || index < 0) {
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            return Object.class;
        }
        return (Class) params[index];
    }

    protected String getCurrentDate() {
        Date date = new Date();
        return dateFormat(date);
    }

    protected String dateFormat(Date date) {
        SimpleDateFormat sm = new SimpleDateFormat("yyyyMMdd");
        return sm.format(date);
    }

    protected String formatMaxID(String id) {
        int iid = Integer.valueOf(id);
        if (iid > -1) {
            return String.format("%1$08d", iid + 1);
        }

        return null;
    }
}
