package generator.impl;

import generator.IFileGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import static constant.UtilConstant.CHARSET_NAME;
import static constant.UtilConstant.ENTITY_PACKAGE;
import static constant.UtilConstant.DAO_PACKAGE;
import static constant.UtilConstant.DAO;
import static constant.UtilConstant.POINT;
import static constant.UtilConstant.SEPARATOR;

/**
 * @author kaka
 * 
 * 文件生成父类
 */
public abstract class AbstractFileGenerator implements IFileGenerator {

    protected Writer getFileWriter(String fileName) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(fileName), CHARSET_NAME);
        return writer;
    }

    protected String getDaoPackageName(String cellValue) {
        return cellValue.replaceAll(ENTITY_PACKAGE, DAO_PACKAGE);
    }

    protected String getDaoClassName(String classNameStr) {
        return classNameStr + DAO;
    }

    protected String getComponentName(String str) {
        if (str != null && str.length() >= 1) {
            return str.substring(0, 1).toLowerCase() + str.substring(1);
        } else {
            return str;
        }
    }

    protected String get1substringUpperCase(String str) {
        if (str != null && str.length() >= 1) {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        } else {
            return str;
        }
    }

    /**
     * Get方法注释生成
     * 
     * @param filedName
     * @param comment
     * @return
     */
    protected String getGetMethodComment(String comment) {
        StringBuffer strBuf = new StringBuffer();
        strBuf.append("\t/**" + "\n");
        strBuf.append("\t * 取得" + comment + "\n");
        strBuf.append("\t * " + "\n");
        strBuf.append("\t * @return " + comment + "\n");
        strBuf.append("\t */" + "\n");
        return strBuf.toString();
    }

    /**
     * Set方法注释生成
     * 
     * @param comment
     * @return
     */
    protected String getSetMethodComment(String filedName, String comment) {
        StringBuffer strBuf = new StringBuffer();
        strBuf.append("\t/**" + "\n");
        strBuf.append("\t * 设置" + comment + "\n");
        strBuf.append("\t * " + "\n");
        strBuf.append("\t * @param " + filedName + " " + comment + "\n");
        strBuf.append("\t */" + "\n");
        return strBuf.toString();
    }

    /**
     * Set方法
     * 
     * @param filedName
     * @return
     */
    protected String getSetMethodString(String filedName, String filedType) {
        StringBuffer strBuf = new StringBuffer();
        strBuf.append("\tpublic void set" + get1substringUpperCase(filedName) + "(" + filedType + " " + filedName
                + ") {\n");
        strBuf.append("\t\tthis." + filedName + " = " + filedName + ";\n");
        strBuf.append("\t}\n");
        return strBuf.toString();
    }

    /**
     * Get方法生成
     * 
     * @param filedName
     * @return
     */
    protected String getGetMethodString(String filedName, String filedType) {
        StringBuffer strBuf = new StringBuffer();
        strBuf.append("\tpublic " + filedType + " get" + get1substringUpperCase(filedName) + "() {\n");
        strBuf.append("\t\treturn " + filedName + ";\n");
        strBuf.append("\t}\n");
        return strBuf.toString();
    }

    /**
     * Class注释生成
     * 
     * @param key
     * @return
     */
    protected String getClassComment(String key) {
        return "/** " + key + " */";
    }

    /**
     * Filed注释生成
     * 
     * @param key
     * @return
     */
    protected String getFiledComment(String key) {
        return "    /** " + key + " */";
    }

    protected static String getFolderName(String cellValue) {
        return cellValue.replace(POINT, SEPARATOR) + SEPARATOR;
    }

    protected static void createFolder(String dir) {
        new File(dir).mkdirs();
    }

}
