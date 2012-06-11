package org.springside.examples.showcase.cache.memcached;

/**
 * 统一定义Memcached中存储的各种对象的Key前缀和超时时间.
 * 
 * @see org.springside.examples.showcase.common.service.AccountManager#getInitializedUser(String)
 * 
 * @author calvin
 */
public enum MemcachedObjectType {
	USER("user:", 60 * 60 * 1);// 1小时

	private String prefix;
	private int expiredTime;

	MemcachedObjectType(String prefix, int expiredTime) {
		this.prefix = prefix;
		this.expiredTime = expiredTime;
	}

	public String getPrefix() {
		return prefix;
	}

	public int getExpiredTime() {
		return expiredTime;
	}

}
