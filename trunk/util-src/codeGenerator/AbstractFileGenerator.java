package codeGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public abstract class AbstractFileGenerator {
	public static final String ROOT_PATH = "c:\\";

	protected static Writer getFileWriter(String fileName) throws IOException {
		OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8");
		return writer;
	}

	protected static String getEntityPackageName(String cellValue) {
		return cellValue.replaceAll(".dao.", ".entity.");
	}

	protected static String getDaoClassName(String classNameStr) {
		return classNameStr + "Dao";
	}

	protected static String getComponentName(String cellValue) {
		return cellValue.substring(0, 1).toLowerCase() + cellValue.substring(1);
	}

	protected static String getFolderName(String cellValue) {
		return cellValue.replace(".", "\\") + "\\";
	}

	protected static void createFolder(String dir) {
		new File(dir).mkdirs();
	}

}
