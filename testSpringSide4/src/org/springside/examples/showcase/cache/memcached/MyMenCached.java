package org.springside.examples.showcase.cache.memcached;

import java.net.InetSocketAddress;
import java.util.concurrent.Future;

import net.spy.memcached.MemcachedClient;

public class MyMenCached {
	public static void main(String[] args) {
		//-------------------------------------------------
		//----	/usr/local/bin/memcached -d -m 10 -u root -l 172.29.225.91 -p 11211 -c 256 -P /tmp/memcached.pid
		// 需要Memcached服务器支持，参见memcached.txt
		try {
			/* 建立MemcachedClient 实例，并指定memcached服务的IP地址和端口号 */
			MemcachedClient mc = new MemcachedClient(new InetSocketAddress("172.29.225.91", 11211));

//			/* 将key值，过期时间(秒)和要缓存的对象set到memcached中 */
//			Future<Boolean> b = mc.set("neea:testDaF:ksIdno", 60, "someObject");
//			System.out.println(b.get().booleanValue());
//			System.out.println(mc.get("neea:testDaF:ksIdno"));
//
//			Future<Boolean> b1 = mc.set("neea:testDaF:ksIdno", 60, "someObjectNew");
//			System.out.println(b1.get().booleanValue());
//			System.out.println(mc.get("neea:testDaF:ksIdno"));

			
			System.out.println(mc.get("user:1"));
			mc.shutdown();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
