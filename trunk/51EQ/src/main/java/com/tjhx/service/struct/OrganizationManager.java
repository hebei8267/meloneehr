package com.tjhx.service.struct;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhx.dao.struct.OrganizationJpaDao;
import com.tjhx.entity.struct.Organization;
import com.tjhx.service.ServiceException;

@Service
@Transactional(readOnly = true)
public class OrganizationManager {
	@Resource
	private OrganizationJpaDao organizationJpaDao;

	/**
	 * 取得所有机构信息
	 * 
	 * @return 机构信息列表
	 */
	@SuppressWarnings("unchecked")
	public List<Organization> getAllOrganization() {
		return (List<Organization>) organizationJpaDao.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "uuid")));
	}

	/**
	 * 根据编号取得机构信息
	 * 
	 * @param uuid 机构编号
	 * @return 机构信息
	 */
	public Organization getOrganizationByUuid(Integer uuid) {
		return organizationJpaDao.findOne(uuid);
	}

	/**
	 * 删除机构信息
	 * 
	 * @param uuid 机构编号
	 */
	@Transactional(readOnly = false)
	public void delOrganizationByUuid(Integer uuid) {
		organizationJpaDao.delete(uuid);
	}

	/**
	 * 添加新机构信息
	 * 
	 * @param organization 机构信息
	 */
	@Transactional(readOnly = false)
	public void addNewOrganization(Organization organization) {
		Organization _dbOrganization = findByName(organization.getName());
		// 该机构已存在!
		if (null != _dbOrganization) {
			throw new ServiceException("ERR_MSG_ORG_001");
		}
		organizationJpaDao.save(organization);
	}

	/**
	 * 取得机构信息
	 * 
	 * @param name 名称
	 * @return 机构信息
	 */
	public Organization findByName(String name) {
		return organizationJpaDao.findByName(name);
	}

	/**
	 * 更新机构信息
	 * 
	 * @param organization 机构信息
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Transactional(readOnly = false)
	public void updateOrganization(Organization organization) throws IllegalAccessException, InvocationTargetException {
		// ----------------------------------------------------------------------------
		// TODO 修改开始
		Organization _dbOrganization = organizationJpaDao.findOne(organization.getUuid());
		if (null == _dbOrganization) {
			// 机构不存在!
			throw new ServiceException("?????????????????");
		}

		_dbOrganization.setName(organization.getName());

		// ----------------------------------------------------------------------------
		organizationJpaDao.save(_dbOrganization);
	}
}