package utils;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;

public class CreateTable {
	public static void main(String[] args) {
		CreateTable.createTable();
	}

	public static void createTable() {
		AnnotationSessionFactoryBean factory = (AnnotationSessionFactoryBean) LocalApplicationContext
				.getAppContext().getBean("&sessionFactory");

		factory.dropDatabaseSchema();
		factory.createDatabaseSchema();
	}
}
