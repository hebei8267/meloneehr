package com.tjhx.entity.struct;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.NaturalId;

import com.tjhx.entity.IdEntity;

/**
 * 部门
 */
@Entity
@Table(name = "T_DEPARTMENT")
public class Department extends IdEntity {

	private static final long serialVersionUID = 6561338847680281703L;

	/** 部门编号 */
	private String id;

	/** 机构编号-百威 */
	private String bwId;

	/** 部门名称 */
	private String name;

	/** Index */
	private Integer index;

	/** 子部门List */
	private List<Department> subDepList = new ArrayList<Department>();

	/** 父部门 */
	private Department parentDep;

	/**
	 * 取得部门编号
	 * 
	 * @return id 部门编号
	 */
	@NaturalId
	@Column(name = "DEP_ID", nullable = false, length = 32)
	public String getId() {
		return id;
	}

	/**
	 * 设置部门编号
	 * 
	 * @param id 部门编号
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 取得机构编号-百威
	 * 
	 * @return bwId 机构编号-百威
	 */
	@Column(name = "ORG_BW_ID", nullable = false, length = 32)
	public String getBwId() {
		return bwId;
	}

	/**
	 * 设置机构编号-百威
	 * 
	 * @param bwId 机构编号-百威
	 */
	public void setBwId(String bwId) {
		this.bwId = bwId;
	}

	/**
	 * 取得部门名称
	 * 
	 * @return name 部门名称
	 */
	@Basic
	@Column(name = "DEP_NAME", nullable = false, length = 32)
	public String getName() {
		return name;
	}

	/**
	 * 设置部门名称
	 * 
	 * @param name 部门名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 取得Index
	 * 
	 * @return index Index
	 */
	@Basic
	@Column(name = "_INDEX")
	public Integer getIndex() {
		return index;
	}

	/**
	 * 设置Index
	 * 
	 * @param index Index
	 */
	public void setIndex(Integer index) {
		this.index = index;
	}

	/**
	 * 取得子部门List
	 * 
	 * @return subDepList 子部门List
	 */
	@OneToMany(mappedBy = "parentDep", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@IndexColumn(name = "_INDEX", base = 1)
	public List<Department> getSubDepList() {
		return subDepList;
	}

	/**
	 * 设置子部门List
	 * 
	 * @param subDepList 子部门List
	 */
	public void setSubDepList(List<Department> subDepList) {
		this.subDepList = subDepList;
	}

	/**
	 * 添加子部门
	 * 
	 * @param dep 子部门
	 */
	public void addSubDep(Department dep) {
		int index = 1;
		for (Department _department : this.subDepList) {
			if (_department.getIndex() != null) {
				_department.setIndex(index);
				index++;
			}
		}

		dep.setIndex(index);
		this.subDepList.add(dep);
	}

	/**
	 * 取得父部门
	 * 
	 * @return parentDep 父部门
	 */
	@ManyToOne
	@JoinColumn(name = "PARENT_DEP_H_ID")
	public Department getParentDep() {
		return parentDep;
	}

	/**
	 * 设置父部门
	 * 
	 * @param parentDep 父部门
	 */
	public void setParentDep(Department parentDep) {
		this.parentDep = parentDep;
	}

}
