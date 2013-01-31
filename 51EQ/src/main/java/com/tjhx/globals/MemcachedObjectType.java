package com.tjhx.globals;

/**
 * 统一定义Memcached中存储的各种对象Key和超时时间.
 */
public enum MemcachedObjectType {
	/** 产品列表 */
	SUPPLIER_MAP("Supplier_Map", 60 * 60 * 24);// 24小时

	/** Memcached对象Key */
	private String objKey;
	/** 超时时间 */
	private int expiredTime;

	MemcachedObjectType(String objKey, int expiredTime) {
		this.objKey = objKey;
		this.expiredTime = expiredTime;
	}

	/**
	 * 取得Memcached对象Key
	 * 
	 * @return Memcached对象Key
	 */
	public String getObjKey() {
		return objKey;
	}

	/**
	 * 取得超时时间
	 * 
	 * @return 超时时间
	 */
	public int getExpiredTime() {
		return expiredTime;
	}

}
