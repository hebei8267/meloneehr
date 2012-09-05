package com.tjhx.entity.shop;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.NaturalId;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.tjhx.entity.IdEntity;

/**
 * 门店
 */
@Entity
@Table(name = "T_SHOP")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Shop extends IdEntity {

	private static final long serialVersionUID = 5365460546248353444L;

	/** 门店编号 */
	private String id;
	/** 门店名称-汉字 */
	private String name;
	/** 门店电话号码 */
	private String telNum;
	/** 门店地址 */
	private String addr;
	/** 门店传真号码 */
	private String faxNum;
	/** 门店详细描述 */
	private String descTxt;
	/** 仓库信息集合 */
	private Set<Store> storeSet = Sets.newHashSet();

	// ##########################################################
	/** 关联仓库信息集合 */
	private String[] storeIds;
	/** 非关联仓库信息集合 */
	private String[] noStoreIds;
	/** 全量仓库信息集合 */
	private List<Store> allStoreList;

	/**
	 * 取得门店编号
	 * 
	 * @return 门店编号
	 */
	@NaturalId
	@Column(nullable = false, length = 16)
	public String getId() {
		return id;
	}

	/**
	 * 设置门店编号
	 * 
	 * @param id 门店编号
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 取得门店名称-汉字
	 * 
	 * @return 门店名称-汉字
	 */
	@Column(nullable = false, length = 32)
	public String getName() {
		return name;
	}

	/**
	 * 设置门店名称-汉字
	 * 
	 * @param name 门店名称-汉字
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 取得门店电话号码
	 * 
	 * @return 门店电话号码
	 */
	@Column(length = 32)
	public String getTelNum() {
		return telNum;
	}

	/**
	 * 设置门店电话号码
	 * 
	 * @param telNum 门店电话号码
	 */
	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}

	/**
	 * 取得门店地址
	 * 
	 * @return 门店地址
	 */
	public String getAddr() {
		return addr;
	}

	/**
	 * 设置门店地址
	 * 
	 * @param addr 门店地址
	 */
	public void setAddr(String addr) {
		this.addr = addr;
	}

	/**
	 * 取得门店传真号码
	 * 
	 * @return 门店传真号码
	 */
	@Column(length = 32)
	public String getFaxNum() {
		return faxNum;
	}

	/**
	 * 设置门店传真号码
	 * 
	 * @param faxNum 门店传真号码
	 */
	public void setFaxNum(String faxNum) {
		this.faxNum = faxNum;
	}

	/**
	 * 取得门店详细描述
	 * 
	 * @return 门店详细描述
	 */
	public String getDescTxt() {
		return descTxt;
	}

	/**
	 * 设置门店详细描述
	 * 
	 * @param desc 门店详细描述
	 */
	public void setDescTxt(String descTxt) {
		this.descTxt = descTxt;
	}

	/**
	 * 取得仓库信息集合
	 * 
	 * @return 仓库信息集合
	 */
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "shop")
	public Set<Store> getStoreSet() {
		return storeSet;
	}

	/**
	 * 设置仓库信息集合
	 * 
	 * @param storeSet 仓库信息集合
	 */
	public void setStoreSet(Set<Store> storeSet) {
		this.storeSet = storeSet;
	}

	/**
	 * 取得仓库名称
	 * 
	 * @return 仓库名称
	 */
	@Transient
	public String getStoreName() {
		List<String> storeNameList = Lists.newArrayList();

		for (Store store : getStoreSet()) {
			storeNameList.add(store.getName());
		}
		return StringUtils.join(storeNameList, ",");
	}

	/**
	 * 取得关联仓库信息集合
	 * 
	 * @return 关联仓库信息集合
	 */
	@Transient
	public String[] getStoreIds() {
		return storeIds;
	}

	/**
	 * 设置关联仓库信息集合
	 * 
	 * @param storeIds 关联仓库信息集合
	 */
	public void setStoreIds(String[] storeIds) {
		this.storeIds = storeIds;
	}

	/**
	 * 取得非关联仓库信息集合
	 * 
	 * @return 非关联仓库信息集合
	 */
	@Transient
	public String[] getNoStoreIds() {
		return noStoreIds;
	}

	/**
	 * 设置非关联仓库信息集合
	 * 
	 * @param noStoreIds 非关联仓库信息集合
	 */
	public void setNoStoreIds(String[] noStoreIds) {
		this.noStoreIds = noStoreIds;
	}

	/**
	 * 取得该门店未关联的仓库信息列表
	 * 
	 * @return
	 */
	@Transient
	public List<Store> getNoStoreList() {
		if (null == storeSet || storeSet.size() == 0) {
			return this.allStoreList;
		} else {
			if (null != allStoreList) {
				List<Store> storeList = Lists.newArrayList();
				for (Store store : getStoreSet()) {
					storeList.add(store);
				}

				this.allStoreList.removeAll(storeList);
			}
		}
		return allStoreList;
	}

	/**
	 * 取得该门店关联的仓库信息列表
	 * 
	 * @return
	 */
	@Transient
	public List<Store> getStoreList() {
		List<Store> storeList = Lists.newArrayList();
		for (Store store : getStoreSet()) {
			storeList.add(store);
		}
		return storeList;
	}

	public void setAllStoreList(List<Store> allStoreList) {
		this.allStoreList = allStoreList;
	}

	/**
	 * 添加仓库
	 * 
	 * @param store 仓库信息
	 */
	public void addStore(Store store) {
		if (!storeSet.contains(store)) {
			storeSet.add(store);
		}
	}

}
