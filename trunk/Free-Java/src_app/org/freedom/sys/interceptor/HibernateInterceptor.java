/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.sys.interceptor;

import java.io.Serializable;
import java.sql.Timestamp;

import org.freedom.sys.SysConstant;
import org.hibernate.EmptyInterceptor;

/**
 * HibernateInterceptor
 * 
 * @author 何贝
 * @since JDK1.5
 */
@SuppressWarnings("unused")
public class HibernateInterceptor extends EmptyInterceptor {

    private static final long serialVersionUID = 8377000932952044081L;

    private final String CREATE_DATE = "createDate";

    private final String CREATE_USER_ID = "createUserId";

    private final String UPDATE_DATE = "updateDate";

    private final String UPDATE_USER_ID = "updateUserId";

    private String userId = null;

    public HibernateInterceptor() {

    }

    @Override
    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames,
            org.hibernate.type.Type[] types) {
        boolean setUpdateDate = false;
        boolean setUpdateUser = false;

        for (int i = 0; i < propertyNames.length; i++) {
            if (!setUpdateUser && UPDATE_USER_ID.equals(propertyNames[i])) {
                if (userId == null) {
                    currentState[i] = SysConstant.SYS_USER_ID;
                } else {
                    currentState[i] = userId;
                }
                setUpdateUser = true;
            }

            if (!setUpdateDate && UPDATE_DATE.equals(propertyNames[i])) {
                currentState[i] = new Timestamp(System.currentTimeMillis());
                setUpdateDate = true;
            }

            if (setUpdateUser && setUpdateDate) {
                return true;
            }
        }

        throw new IllegalStateException("[" + entity.toString() + "] updateDate Not Set!");

    }

    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, org.hibernate.type.Type[] types) {
        boolean setUpdateDate = false;
        boolean setUpdateUserID = false;
        boolean setCreateDate = false;
        boolean setCreateUserID = false;
        Timestamp nowTime = null;

        for (int i = 0; i < propertyNames.length; i++) {

            if (!setCreateDate && CREATE_DATE.equals(propertyNames[i])) {
                if (nowTime == null) {
                    nowTime = new Timestamp(System.currentTimeMillis());
                }
                state[i] = nowTime;
                setCreateDate = true;
            }
            if (!setUpdateDate && UPDATE_DATE.equals(propertyNames[i])) {
                if (nowTime == null) {
                    nowTime = new Timestamp(System.currentTimeMillis());
                }
                state[i] = nowTime;
                setUpdateDate = true;
            }
            if (!setCreateUserID && CREATE_USER_ID.equals(propertyNames[i])) {

                if (userId == null) {
                    state[i] = SysConstant.SYS_USER_ID;
                } else {
                    state[i] = userId;
                }
                setCreateUserID = true;
            }
            if (!setUpdateUserID && UPDATE_USER_ID.equals(propertyNames[i])) {

                if (userId == null) {
                    state[i] = SysConstant.SYS_USER_ID;
                } else {
                    state[i] = userId;
                }

                setUpdateUserID = true;
            }
            if (setCreateDate && setUpdateDate && setUpdateUserID && setCreateUserID) {
                return true;
            }
        }
        throw new IllegalStateException("[" + entity.toString() + "] createDate/updateDate Not Set!");
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
}
