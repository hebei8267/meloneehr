package cn.com.free.framework.generator;

import java.io.File;
import java.io.IOException;
import java.util.List;

import cn.com.free.framework.generator.provider.db.DbTableFactory;
import cn.com.free.framework.generator.provider.db.DbTableGeneratorModelProvider;
import cn.com.free.framework.generator.provider.db.model.Table;

/**
 * 生成工具Facade
 * 
 * @author
 * 
 */
@SuppressWarnings("unchecked")
public class GeneratorFacade {

	/**
	 * 打印数据库中的表名称
	 */
	@SuppressWarnings("rawtypes")
	public void printAllTableNames() throws Exception {
		List tables = DbTableFactory.getInstance().getAllTables();
		System.out.println("\n----All TableNames BEGIN----");
		for (int i = 0; i < tables.size(); i++) {
			String sqlName = ((Table) tables.get(i)).getSqlName();
			System.out.println("g.generateTable(\"" + sqlName + "\");");
		}
		System.out.println("----All TableNames END----");

	}

	public void clean() throws IOException {
		Generator g = createGeneratorForDbTable();
		g.clean();
	}

	public Generator createGeneratorForDbTable() {
		Generator g = new Generator();
		g.setTemplateRootDir(new File("template").getAbsoluteFile());
		g.setOutRootDir(GeneratorProperties.getRequiredProperty("outRoot"));
		return g;
	}

	public void generateAllTable() throws Exception {
		List<Table> tables = DbTableFactory.getInstance().getAllTables();
		for (int i = 0; i < tables.size(); i++) {
			System.out.println(tables.get(i).getSqlName());
			generateTable(tables.get(i).getSqlName());
		}
	}

	public void generateTable(String tableName) throws Exception {
		Generator g = createGeneratorForDbTable();
		Table table = DbTableFactory.getInstance().getTable(tableName);
		g.generateByModelProvider(new DbTableGeneratorModelProvider(table));
	}

}
