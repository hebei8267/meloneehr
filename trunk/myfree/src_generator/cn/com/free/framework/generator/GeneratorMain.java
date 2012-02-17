package cn.com.free.framework.generator;

public class GeneratorMain {
	/**
	 * 生成工具入口
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(final String[] args) throws Exception {
		GeneratorFacade g = new GeneratorFacade();
		// 打印数据库中的表名称
		//g.printAllTableNames();

		// 删除生成器的输出目录
		g.clean();

		// 通过数据库表生成文件,注意: oracle
		g.generateTable("T_USER");
	}
}
