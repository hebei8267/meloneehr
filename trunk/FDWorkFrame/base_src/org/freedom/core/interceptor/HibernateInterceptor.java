/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.core.interceptor;

import java.io.Serializable;
import java.sql.Timestamp;

import org.hibernate.EmptyInterceptor;

/**
 * HibernateInterceptor
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class HibernateInterceptor extends EmptyInterceptor {

	private static final long serialVersionUID = 8377000932952044081L;

	private final String CREATE_DATE = "createDate";

	private final String CREATE_USER_ID = "createUserId";

	private final String UPDATE_DATE = "updateDate";

	private final String UPDATE_USER_ID = "updateUserId";

	@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,
			String[] propertyNames, org.hibernate.type.Type[] types) {
		boolean setUpdateDate = false;

		for (int i = 0; i < propertyNames.length; i++) {
			if (!setUpdateDate && UPDATE_DATE.equals(propertyNames[i])) {
				currentState[i] = new Timestamp(System.currentTimeMillis());
				setUpdateDate = true;
			}
		}
		if (setUpdateDate) {
			return true;
		} else {
			throw new IllegalStateException("[" + entity.toString() + "] updateDate Not Set!");
		}
	}

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, org.hibernate.type.Type[] types) {
		boolean setUpdateDate = false;
		boolean setUpdateUserID = false;
		boolean setCreateDate = false;
		boolean setCreateUserID = false;
		Timestamp nowTime = null;

		for (int i = 0; i < propertyNames.length; i++) {

			if (CREATE_DATE.equals(propertyNames[i])) {
				if (nowTime == null) {
					nowTime = new Timestamp(System.currentTimeMillis());
				}
				state[i] = nowTime;
				setCreateDate = true;
			}
			if (UPDATE_DATE.equals(propertyNames[i])) {
				if (nowTime == null)
					nowTime = new Timestamp(System.currentTimeMillis());
				state[i] = nowTime;
				setUpdateDate = true;
			}
			if (CREATE_USER_ID.equals(propertyNames[i])) {
				// TODO hebei
				if (state[i] == null) {
					System.out.println(CREATE_USER_ID + " Not Set!");
				}
				state[i] = "sys";
				setCreateUserID = true;
			}
			if (UPDATE_USER_ID.equals(propertyNames[i])) {
				// TODO hebei
				if (state[i] == null) {
					System.out.println(UPDATE_USER_ID + " Not Set!");
				}
				state[i] = "sys";
				setUpdateUserID = true;
			}
			if (setCreateDate && setUpdateDate && setUpdateUserID && setCreateUserID) {
				return true;
			}
		}
		throw new IllegalStateException("[" + entity.toString() + "] createDate/updateDate Not Set!");
	}
}
