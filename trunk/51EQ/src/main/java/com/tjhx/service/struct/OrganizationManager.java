package com.tjhx.service.struct;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.cache.memcached.SpyMemcachedClient;

import com.tjhx.dao.struct.OrganizationJpaDao;
import com.tjhx.entity.struct.Organization;
import com.tjhx.globals.Constants;
import com.tjhx.globals.MemcachedObjectType;
import com.tjhx.service.ServiceException;

@Service
@Transactional(readOnly = true)
public class OrganizationManager {
	private static Logger logger = LoggerFactory.getLogger(OrganizationManager.class);

	@Resource
	private OrganizationJpaDao orgJpaDao;
	@Resource
	private SpyMemcachedClient spyMemcachedClient;

	/**
	 * 取得所有机构信息
	 * 
	 * @return 机构信息列表
	 */
	@SuppressWarnings("unchecked")
	public List<Organization> getAllOrganization() {

		List<Organization> _orgList = spyMemcachedClient.get(MemcachedObjectType.ORG_LIST.getObjKey());

		if (null == _orgList) {
			// 从数据库中取出全量机构信息(List格式)
			_orgList = (List<Organization>) orgJpaDao.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "uuid")));
			// 将机构信息Map保存到memcached
			spyMemcachedClient.set(MemcachedObjectType.ORG_LIST.getObjKey(),
					MemcachedObjectType.ORG_LIST.getExpiredTime(), _orgList);

			logger.debug("机构信息不在 memcached中,从数据库中取出并放入memcached");
		} else {
			logger.debug("从memcached中取出机构信息");
		}
		return _orgList;
	}

	/**
	 * 根据编号取得机构信息
	 * 
	 * @param uuid 机构编号
	 * @return 机构信息
	 */
	public Organization getOrganizationByUuid(Integer uuid) {
		return orgJpaDao.findOne(uuid);
	}

	/**
	 * 删除机构信息
	 * 
	 * @param uuid 机构编号
	 */
	@Transactional(readOnly = false)
	public void delOrganizationByUuid(Integer uuid) {
		spyMemcachedClient.delete(MemcachedObjectType.ORG_LIST.getObjKey());

		orgJpaDao.delete(uuid);
	}

	/**
	 * 添加新机构信息
	 * 
	 * @param org 机构信息
	 */
	@Transactional(readOnly = false)
	public void addNewOrganization(Organization org) {
		Organization _dbOrganization = orgJpaDao.findByBwId(org.getBwId());
		// 该机构已存在!
		if (null != _dbOrganization) {
			throw new ServiceException("ERR_MSG_ORG_001");
		}

		if (Constants.ROOT_ORG_BW_ID.equals(org.getBwId())) {
			org.setId(Constants.ROOT_ORG_ID);
		} else {
			Organization rootOrg = orgJpaDao.findOne(1);
			org.setParentOrg(rootOrg);
			rootOrg.addSubOrg(org);
			org.setId(rootOrg.getId() + org.getBwId());

		}

		orgJpaDao.save(org);

		spyMemcachedClient.delete(MemcachedObjectType.ORG_LIST.getObjKey());
	}

	/**
	 * 更新机构信息
	 * 
	 * @param org 机构信息
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Transactional(readOnly = false)
	public void updateOrganization(Organization org) throws IllegalAccessException, InvocationTargetException {
		Organization _dbOrganization = orgJpaDao.findOne(org.getUuid());
		if (null == _dbOrganization) {
			// 机构不存在!
			throw new ServiceException("ERR_MSG_ORG_002");
		}

		_dbOrganization.setName(org.getName());

		orgJpaDao.save(_dbOrganization);

		spyMemcachedClient.delete(MemcachedObjectType.ORG_LIST.getObjKey());
	}
}