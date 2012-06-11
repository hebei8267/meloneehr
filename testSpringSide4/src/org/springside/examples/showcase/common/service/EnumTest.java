package org.springside.examples.showcase.common.service;

import org.springside.examples.showcase.cache.memcached.MemcachedObjectType;

public class EnumTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(MemcachedObjectType.USER.getPrefix());
		System.out.println(MemcachedObjectType.USER.getExpiredTime());
	}

}
