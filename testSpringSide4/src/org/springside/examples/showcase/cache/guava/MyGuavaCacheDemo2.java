package org.springside.examples.showcase.cache.guava;

import java.util.concurrent.TimeUnit;

import org.springside.examples.showcase.common.entity.User;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

public class MyGuavaCacheDemo2 {
	public static void main(String[] args) throws InterruptedException {
		// 下面来看maximumSize(1) 的作用,是指最大缓存对象的数目
		CacheBuilder<String, User> cahceBuilder = CacheBuilder.newBuilder().expireAfterAccess(8, TimeUnit.SECONDS).maximumSize(1)
				.removalListener(new RemovalListener<String, User>() {
					public void onRemoval(RemovalNotification<String, User> rn) {
						System.out.println(rn.getKey() + "被移除");
					}

				});
		LoadingCache<String, User> cahce = cahceBuilder.build(new CacheLoader<String, User>() {
			@Override
			public User load(String key) throws Exception {
				User b = new User();
				return b;
			}
		});
		User User_1_0 = cahce.apply("User_1");// 缓存User_1对象
		System.out.println(User_1_0);
		User User_2_0 = cahce.apply("User_2");// 因为maximumSize设为1,故不能同时缓存两个或两个以上的对象,所以必须先移除之前的缓存对象User_1
												// 再缓存User_2对象
		System.out.println(User_2_0);
		User User_1_1 = cahce.apply("User_1");// 获取User_1
												// 对象,如果存在则返回,否则创建新的对象,并将之缓存(User_2会被移除).
		System.out.println(User_1_1);

	}
}
