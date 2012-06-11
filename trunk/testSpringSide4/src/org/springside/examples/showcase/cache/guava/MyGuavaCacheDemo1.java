package org.springside.examples.showcase.cache.guava;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springside.examples.showcase.common.entity.User;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class MyGuavaCacheDemo1 {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		// User对象的缓存在8秒后被移除.
		LoadingCache<String, User> cahce = CacheBuilder.newBuilder().expireAfterAccess(8, TimeUnit.SECONDS).maximumSize(10)
				.build(new CacheLoader<String, User>() {
					@Override
					public User load(String key) throws Exception {
						User b = new User();
						return b;
					}
				});

		User b = cahce.get("q");
		System.out.println(b);
		User b2 = cahce.get("q");
		System.out.println(b2);
		Thread.currentThread();
		Thread.sleep(TimeUnit.SECONDS.toMillis(10));
		User b3 = cahce.get("q");
		System.out.println(b3);
	}

	// 运行后查看控制台输出
}
