<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
package ${basepackage}.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ${basepackage}.dao.${className}JpaDao;
import ${basepackage}.entity.${className};
import ${basepackage}.service.ServiceException;

@Service
@Transactional(readOnly = true)
public class ${className}Manager {
	private ${className}JpaDao ${classNameLower}JpaDao;
	
	/**
	 * 取得所有${table.tableAlias}信息
	 * 
	 * @return ${table.tableAlias}信息列表
	 */
	@SuppressWarnings("unchecked")
	public List<${className}> getAll${className}() {
		return (List<${className}>) ${classNameLower}JpaDao.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "uuid")));
	}
	
	/**
	 * 根据编号取得${table.tableAlias}信息
	 * 
	 * @param uuid ${table.tableAlias}编号
	 * @return ${table.tableAlias}信息
	 */
	public ${className} ge${className}ByUuid(Integer uuid) {
		return ${classNameLower}JpaDao.findOne(uuid);
	}
	
	/**
	 * 删除${table.tableAlias}信息
	 * 
	 * @param uuid ${table.tableAlias}编号
	 */
	@Transactional(readOnly = false)
	public void del${className}ByUuid(Integer uuid) {
		${classNameLower}JpaDao.delete(uuid);
	}
	
	/**
	 * 添加新${table.tableAlias}信息
	 * 
	 * @param ${classNameLower} ${table.tableAlias}信息
	 */
	@Transactional(readOnly = false)
	public void addNew${className}(${className} ${classNameLower}) {
		//----------------------------------------------------------------------------
		// TODO 修改开始
		${className} _db${className} = findByName(storeType.getName());
		// 该${table.tableAlias}已存在!
		if (null != _db${className}) {
			throw new ServiceException("?????????????????");
		}
		//----------------------------------------------------------------------------
		${classNameLower}JpaDao.save(${classNameLower});
	}
	
	/**
	 * 更新${table.tableAlias}信息
	 * 
	 * @param ${classNameLower} ${table.tableAlias}信息
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Transactional(readOnly = false)
	public void update${className}(${className} ${classNameLower}) throws IllegalAccessException, InvocationTargetException {
		//----------------------------------------------------------------------------
		// TODO 修改开始
		${className} _db${className} = storeTypeJpaDao.findOne(storeType.getUuid());
		if (null == _db${className}) {
			// 仓库类型不存在!
			throw new ServiceException("?????????????????");
		}

		_db${className}.setName(${classNameLower}.getName());
		_db${className}.setDescTxt(${classNameLower}.getDescTxt());
		
		//----------------------------------------------------------------------------
		${classNameLower}JpaDao.save(_db${className});
	}
	
	@Autowired
	public void set${className}JpaDao(${className}JpaDao ${classNameLower}JpaDao) {
		this.${classNameLower}JpaDao = ${classNameLower}JpaDao;
	}

}