Hibernate Search相关的Annotation主要有两个：
         
         
@Indexed				标识需要进行索引的对象，
	属性		index		指定索引文件的路径

@Field					标注在类的get属性上，标识一个索引的Field
	属性		index		指定是否索引，与Lucene相同
       		store     	指定是否存储，与Lucene相同
        	name        指定Field的name，默认为类属性的名称
      		analyzer	指定分析器

另外@IndexedEmbedded与 @ContainedIn 用于关联类之间的索引
         
@IndexedEmbedded有两个属性，一个prefix指定关联的前缀，一个depth指定关联的深度
          
如上面两个类中Department类可以通过部门名称name来索引部门，在Employee与部门关联的前缀为dept_，
因此可以通过部门名称dept_name来索引一个部门里的所有员工。




hibernate配置

主要就是添加两个属性，hibernate.search.default.directory_provider
指定Directory的代理，即把索引的文件保存在硬盘中
（org.hibernate.search.store.FSDirectoryProvider）



还是内存里（org.hibernate.search.store.RAMDirectoryProvider）


保存在硬盘的话hibernate.search.default.indexBase属性指定索引保存的路径。






@Indexed注解用来告诉Hibernate Search该持久类是拥有索引的
@DocumentId注解用来标明这个属性是这个对象的ID，并且未被编入索引

