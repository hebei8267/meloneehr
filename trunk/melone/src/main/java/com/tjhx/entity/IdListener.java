package com.tjhx.entity;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.hibernate.event.spi.SaveOrUpdateEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 在自动为entity添加审计信息的Hibernate EventListener.
 * 
 * 在hibernate执行saveOrUpdate()时,自动为IdEntity的子类添加审计信息.
 * 
 */
public class IdListener {

	private static Logger logger = LoggerFactory.getLogger(IdListener.class);

	@PrePersist
	@PreUpdate
	public void onSaveOrUpdate(IdEntity object) throws HibernateException {

		// 如果对象是IdEntity子类,添加审计信息.

		IdEntity entity = (IdEntity) object;
		// String loginName = SpringSecurityUtils.getCurrentUserName();
		String loginName = null;
		if (null == loginName || StringUtils.isBlank(loginName)) {
			loginName = "1";
		}
		if (entity.getUuid() == null) {
			// 创建新对象
			entity.setCreateDate(new Date());
			entity.setCreateUserId(loginName);
			
			entity.setUpdateDate(new Date());
			entity.setUpdateUserId(loginName);
		} else {
			// 修改旧对象
			entity.setUpdateDate(new Date());
			entity.setUpdateUserId(loginName);

			logger.info("对象(ID:{}) 被 {} 在 {} 修改", new Object[] { entity.getUuid(), loginName, new Date() });
		}

	}
}
