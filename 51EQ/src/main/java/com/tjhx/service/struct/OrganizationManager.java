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
	private OrganizationJpaDao orgJpaDao;

	/**
	 * 取得所有机构信息
	 * 
	 * @return 机构信息列表
	 */
	@SuppressWarnings("unchecked")
	public List<Organization> getAllOrganization() {
		return (List<Organization>) orgJpaDao.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "uuid")));
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

		Organization rootOrg = orgJpaDao.findOne(1);

		org.setParentOrg(rootOrg);
		rootOrg.addSubOrg(org);
		org.setId(rootOrg.getId() + org.getBwId());

		orgJpaDao.save(org);
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
	}
}