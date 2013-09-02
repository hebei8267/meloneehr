package com.tjhx.service.affair;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhx.dao.affair.WorkTypeJpaDao;
import com.tjhx.entity.affair.WorkType;

@Service
@Transactional(readOnly = true)
public class WorkTypeManager {

	@Resource
	private WorkTypeJpaDao workTypeJpaDao;

	/**
	 * 取得指定机构的上班类型信息列表
	 * 
	 * @param orgId
	 * @return
	 */
	public List<WorkType> getWorkTypeByOrgId(String orgId) {
		List<WorkType> workTypeList = workTypeJpaDao.getWorkTypeListByOrgId(orgId);

		return workTypeList;
	}

	/**
	 * 更新上班类型信息
	 * 
	 * @param orgId
	 * @param workTypeList
	 */
	@Transactional(readOnly = false)
	public void updateWorkTypeList(String orgId, List<WorkType> workTypeList) {
		List<WorkType> _dbWorkTypeList = getWorkTypeByOrgId(orgId);

		for (WorkType dbWorkType : _dbWorkTypeList) {
			WorkType workType = myEquals(dbWorkType, workTypeList);
			if (null != workType) {
				if ("1".equals(workType.getUseFlg())) {// 启用

					dbWorkType.setName(workType.getName());
					dbWorkType.setStartDate(workType.getStartDate());
					dbWorkType.setEndDate(workType.getEndDate());

				} else {

					dbWorkType.setStartDate(null);
					dbWorkType.setEndDate(null);

				}
				dbWorkType.setUseFlg(workType.getUseFlg());

				workTypeJpaDao.save(dbWorkType);
			}
		}
	}

	private WorkType myEquals(WorkType dbWorkType, List<WorkType> workTypeList) {
		for (WorkType workType : workTypeList) {
			if (workType.getUuid().equals(dbWorkType.getUuid())) {
				return workType;
			}
		}
		return null;
	}

}
