package cn.com.free.framework.generator.util;

import java.io.File;
import java.io.IOException;

public class FileUtils extends org.apache.commons.io.FileUtils {

	/**
	 * 创建文件，如果文件路径上的目录不存在，创建相应目录.
	 */
	public static void createFile(final String filename) throws IOException {
		final File file = new File(filename);
		if (file.getParentFile() != null && file.getParentFile().exists() == false) {
			if (file.getParentFile().mkdirs() == false) {
				throw new IOException("创建目录失败：" + file.getParentFile().getAbsolutePath());
			}
		}
		file.createNewFile();
	}

}
