<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
	<title>Cache演示</title>
	<script>
		$(document).ready(function() {
			$("#cache-tab").addClass("active");
		});
	</script>
</head>

<body>
	<h2>Cache演示</h2>

	<h3>技术说明：</h3>
	<ul>
		<li>演示基于Guava的单JVM内的，简单Cache</li>
		<li>演示基于Ehcache的JVM内的，可集群共享的，功能丰富的Cache</li>
		<li>演示基于Memcached的中央式cache，使用Spymemcached客户端</li>
		
	</ul>

	<h3>用户故事：</h3>
	<ul>
		<li>Guava Cache的使用</li>
		---------------------------------------------------------------------------------------
		<br>GuavaCacheDemo.java演示了数据源多种配置如何实现
		<br>MyGuavaCacheDemo1.java演示了缓存时间
		<br>MyGuavaCacheDemo2.java演示了缓存大小
		<br><br>

		<li>Ehcache与Spring的集成</li>
		---------------------------------------------------------------------------------------
		<br>EhcacheDemo.java---集群配置时，其原理是每个机器都启动一个Ehcache节点，然后Ehcache通过RMI等方式自身同步各个节点资源
		<br><br>
		
		<li>Memcached的使用</li>
		---------------------------------------------------------------------------------------
		<br>AccountManager.java中getUserFromMemcached()方法演示从缓存中取得和保存数据
		<br>具体配置参见cache/applicationContext-memcached.xml，其中模拟了Memcached服务器
		<br>示例效果见CRUD演示页面列表
		<br>MyMenCached.java中演示了实际环境中连接Memcached服务器的操作方法
		<br>关于Memcached服务器在linux环境中的安装与启动请参见memcached.txt
	</ul>
</body>
</html>