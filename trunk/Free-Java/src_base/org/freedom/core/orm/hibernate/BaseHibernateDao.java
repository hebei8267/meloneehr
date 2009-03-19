/*
 * Copy By SpringSide
 * 
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.core.orm.hibernate;

import java.io.Serializable;
import java.util.List;

import org.freedom.core.utils.ReflectionUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

/**
 * Hibernate Dao的泛型基类
 * 
 * 封装Hibernate原生API的CRUD范型基类,<br>
 * 取消了HibernateTemplate,直接使用Hibernate原生API
 * 
 * @param <T> DAO操作的对象类型
 * @author 何贝
 * @since JDK1.5
 */
@SuppressWarnings("unchecked")
public class BaseHibernateDao<T> {
    protected Class<T> entityClass;// DAO所管理的Entity类型.
    protected SessionFactory sessionFactory;// Hibernate原生API

    /**
     * 用于扩展的DAO子类使用的构造函数.
     * 
     * 通过子类的范型定义取得对象类型Class.
     * 
     * public class UserDao extends BaseHibernateDao<User>
     */
    public BaseHibernateDao() {
        this.entityClass = ReflectionUtils.getSuperClassGenricType(getClass());
    }

    /**
     * 用于Service层直接使用SimpleHibernateDAO的构造函数.
     * 
     * BaseHibernateDao<User> userDao = new BaseHibernateDao<User>(sessionFactory, User.class);
     */
    public BaseHibernateDao(final SessionFactory sessionFactory, final Class<T> entityClass) {
        this.sessionFactory = sessionFactory;
        this.entityClass = entityClass;
    }

    /**
     * 保存新增或修改的对象.
     */
    public void save(final T instance) {
        Assert.notNull(instance);
        getSession().saveOrUpdate(instance);
    }

    /**
     * 删除对象.
     * 
     * @param entityObj 对象必须是session中的对象或含id属性的transient对象.
     */
    public void delete(final T entityObj) {
        Assert.notNull(entityObj);
        getSession().delete(entityObj);
    }

    /**
     * 根据ID删除对象
     */
    public void deleteById(Serializable id) {
        Assert.notNull(id);
        delete(get(id));
    }

    /** 根据ID获取对象 */
    public T get(Serializable id) {
        Assert.notNull(id);
        return (T) getSession().load(entityClass, id);
    }

    public List<T> getAll() {
        return findByCriteria();
    }

    /**
     * 根据Criterion条件创建Criteria.
     * 
     * 返回对象类型不是Entity时可用此函数灵活查询.
     * 
     * @param criterions 数量可变的Criterion.
     */
    public Criteria createCriteria(final Criterion... criterions) {
        Criteria criteria = getSession().createCriteria(entityClass);
        for (Criterion c : criterions) {
            criteria.add(c);
        }
        return criteria;
    }

    /**
     * 根据命名查询与参数列表创建Query对象.
     * 
     * 返回对象类型不是Entity时可用此函数灵活查询.
     */
    public Query createNamedQuery(final String hqlName) {
        return _createQueryByNamed(hqlName);
    }

    /**
     * 根据命名查询与参数列表创建Query对象.
     * 
     * 返回对象类型不是Entity时可用此函数灵活查询.
     * 
     * @param values 数量可变的参数
     */
    public Query createNamedQuery(final String hqlName, final Object... values) {

        Query query = _createQueryByNamed(hqlName);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        return query;
    }

    private Query _createQueryByNamed(final String hqlName) {
        Assert.hasText(hqlName);
        return getSession().getNamedQuery(hqlName);

    }

    /**
     * 根据查询HQL与参数列表创建Query对象.
     * 
     * 返回对象类型不是Entity时可用此函数灵活查询.
     */
    public Query createQuery(final String hql) {
        return _createQuery(hql);
    }

    /**
     * 根据查询HQL与参数列表创建Query对象.
     * 
     * 返回对象类型不是Entity时可用此函数灵活查询.
     * 
     * @param values 数量可变的参数
     */
    public Query createQuery(final String hql, final Object... values) {

        Query query = _createQuery(hql);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        return query;
    }

    private Query _createQuery(final String hql) {
        Assert.hasText(hql);
        return getSession().createQuery(hql);
    }

    /**
     * 按HQL查询对象列表.
     * 
     * @param values 数量可变的参数
     */
    public List find(final String hql) {
        return createQuery(hql).list();
    }

    /**
     * 按HQL查询对象列表.
     * 
     * @param values 数量可变的参数
     */
    public List find(final String hql, final Object... values) {
        return createQuery(hql, values).list();
    }

    /**
     * 按命名HQL查询对象列表.
     * 
     * @param values 数量可变的参数
     */
    public List findByNamedQuery(final String hqlName) {
        return createNamedQuery(hqlName).list();
    }

    /**
     * 按命名HQL查询对象列表.
     * 
     * @param values 数量可变的参数
     */
    public List findByNamedQuery(final String hqlName, final Object... values) {
        return createNamedQuery(hqlName, values).list();
    }

    /**
     * 按Criteria查询对象列表.
     * 
     * @param criterions 数量可变的Criterion.
     */
    public List findByCriteria(final Criterion... criterions) {
        return createCriteria(criterions).list();
    }

    /**
     * 按DetachedCriteria查询对象列表.
     * 
     * @param criterions 数量可变的Criterion.
     * 
     * Copy SpringFrame HibernateTemplate.java
     */
    public List findByCriteria(DetachedCriteria criteria) {
        return findByCriteria(criteria, -1, -1);
    }

    public List findByCriteria(final DetachedCriteria criteria, final int firstResult, final int maxResults) {

        Assert.notNull(criteria, "DetachedCriteria must not be null");

        Criteria executableCriteria = criteria.getExecutableCriteria(getSession());
        // prepareCriteria(executableCriteria);
        if (firstResult >= 0) {
            executableCriteria.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            executableCriteria.setMaxResults(maxResults);
        }
        return executableCriteria.list();

    }

    /**
     * 按属性查找对象列表,匹配方式为相等.
     */
    public List<T> findByProperty(final String propertyName, final Object value) {
        Assert.hasText(propertyName);
        Criterion criterion = Restrictions.eq(propertyName, value);
        return findByCriteria(criterion);
    }

    /**
     * 按HQL查询Long类型结果.
     */
    public Long findLong(final String hql, final Object... values) {
        return (Long) findUnique(hql, values);
    }

    /**
     * 按命名HQL查询Long类型结果.
     */
    public Long findLongByNamedQuery(final String hqlName, final Object... values) {
        return (Long) findUniqueByNamedQuery(hqlName, values);
    }

    /**
     * 按HQL查询唯一对象.
     */
    public Object findUnique(final String hql, final Object... values) {
        return createQuery(hql, values).uniqueResult();
    }

    /**
     * 按命名HQL查询唯一对象.
     */
    public Object findUniqueByNamedQuery(final String hqlName, final Object... values) {
        return createNamedQuery(hqlName, values).uniqueResult();
    }

    /**
     * 按属性查找唯一对象,匹配方式为相等.
     */
    public T findUniqueByProperty(final String propertyName, final Object value) {
        Assert.hasText(propertyName);
        Criterion criterion = Restrictions.eq(propertyName, value);
        return (T) createCriteria(criterion).uniqueResult();
    }

    /**
     * 取得最大的业务ID
     * 
     * @param id
     * @return
     */
    protected String formatMaxID(String id) {
        if (id == null) {
            id = "0";
        }
        int iid = Integer.valueOf(id);
        if (iid > -1) {
            return String.format("%1$08d", iid + 1);
        }

        return null;
    }

    /**
     * 取得对象的主键名.
     */
    public String getIdName() {
        ClassMetadata meta = getSessionFactory().getClassMetadata(entityClass);
        Assert.notNull(meta, "Class " + entityClass.getSimpleName() + " not define in HibernateSessionFactory.");
        return meta.getIdentifierPropertyName();
    }

    /**
     * Hibernate原生API
     * 
     * Session org.hibernate.SessionFactory.getCurrentSession()
     */
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * 判断对象的属性值在数据库内是否唯一.
     * 
     * 在修改对象的情景下,如果属性新修改的值(value)等于属性原来的值(orgValue)则不作比较.
     */
    public boolean isPropertyUnique(final String propertyName, final Object newValue, final Object orgValue) {
        if (newValue == null || newValue.equals(orgValue))
            return true;
        Object object = findUniqueByProperty(propertyName, newValue);
        return (object == null);
    }

    /**
     * 采用@Autowired按类型注入SessionFactory,当有多个SesionFactory的时候Override本函数.
     * 
     * @param sessionFactory
     */
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
