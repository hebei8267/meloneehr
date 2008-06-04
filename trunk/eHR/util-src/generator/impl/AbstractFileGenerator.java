package generator.impl;

import generator.IFileGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * @author kaka
 * 
 * 文件生成父类
 */
public abstract class AbstractFileGenerator implements IFileGenerator {
    public static final String ROOT_PATH = "c:\\11\\";

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
        if (cellValue == null || cellValue.length() < 0) {
            return cellValue;
        } else {
            return cellValue.substring(0, 1).toLowerCase() + cellValue.substring(1);
        }
    }

    protected static String getFolderName(String cellValue) {
        return cellValue.replace(".", "\\") + "\\";
    }

    protected static void createFolder(String dir) {
        new File(dir).mkdirs();
    }

    /**
     * 代码文件生成
     * 
     * @param classNameStr 类名
     * @param packageNameStr 包名
     * @param annotationStr 类注释名词
     * @throws IOException
     */
    protected abstract void createCodeFile(String classNameStr, String packageNameStr, String annotationStr)
            throws IOException;
}
