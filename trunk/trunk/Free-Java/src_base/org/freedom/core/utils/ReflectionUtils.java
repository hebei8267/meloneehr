/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.core.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.springframework.util.Assert;

/**
 * 反射的Utils函数集合.<br>
 * 提供访问私有变量,获取范型类型Class,提取集合中元素的属性等Utils函数.
 * 
 * @author 何贝
 * @since JDK1.5
 */
@SuppressWarnings("unchecked")
public class ReflectionUtils {
    private ReflectionUtils() {
    }

    /**
     * 直接读取对象属性值,无视private/protected修饰符,不经过getter函数.
     */
    public static Object getFieldValue(final Object object, final String fieldName) {
        Field field = getDeclaredField(object, fieldName);

        if (field == null)
            throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");

        makeAccessible(field);

        Object result = null;
        try {
            result = field.get(object);
        } catch (IllegalAccessException e) {
            // 不可能抛出的异常
            // throw e;
        }
        return result;
    }

    /**
     * 直接设置对象属性值,无视private/protected修饰符,不经过setter函数.
     */
    public static void setFieldValue(final Object object, final String fieldName, final Object value) {
        Field field = getDeclaredField(object, fieldName);

        if (field == null)
            throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");

        makeAccessible(field);

        try {
            field.set(object, value);
        } catch (IllegalAccessException e) {
            // 不可能抛出的异常
            // throw e;
        }
    }

    /**
     * 循环向上转型,获取对象的DeclaredField.
     */
    protected static Field getDeclaredField(final Object object, final String fieldName) {
        Assert.notNull(object);
        return getDeclaredField(object.getClass(), fieldName);
    }

    /**
     * 强制转换fileld可访问.
     */
    protected static void makeAccessible(Field field) {
        if (!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers())) {
            field.setAccessible(true);
        }
    }

    /**
     * 通过反射,获得定义Class时声明的父类的范型参数的类型. 如public UserDao extends HibernateDao<User>
     * 
     * @param clazz The class to introspect
     * @return the first generic declaration, or Object.class if cannot be determined
     */
    public static Class getSuperClassGenricType(Class clazz) {
        return getSuperClassGenricType(clazz, 0);
    }

    /**
     * 通过反射,获得定义Class时声明的父类的范型参数的类型.
     * 
     * 如public UserDao extends HibernateDao<User,Long>
     * 
     * @param clazz clazz The class to introspect
     * @param index the Index of the generic ddeclaration,start from 0.
     * @return the index generic declaration, or Object.class if cannot be determined
     */
    public static Class getSuperClassGenricType(Class clazz, int index) {

        Type genType = clazz.getGenericSuperclass();

        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }

        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if (index >= params.length || index < 0) {
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            return Object.class;
        }
        return (Class) params[index];
    }
}
