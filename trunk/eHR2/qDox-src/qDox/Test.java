package qDox;

import static constant.UtilConstant.CHARSET_NAME;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.qdox.JavaDocBuilder;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaField;

public class Test {
    public static void main(String[] args) throws FileNotFoundException, IOException {

        JavaDocBuilder builder = new JavaDocBuilder();
        File javaFile = new File(
                "C:\\eHR\\eclipse-java-europa-winter-win32\\eclipse\\workspace\\eHR2\\src\\cn\\hb\\entity\\hr\\personnel\\Person.java");

        builder.addSource(new InputStreamReader(new FileInputStream(javaFile), CHARSET_NAME));
        JavaClass cls = builder.getClassByName("cn.hb.entity.hr.personnel.Person");

        JavaField[] fields = cls.getFields();

        Writer fileWriter = new OutputStreamWriter(new FileOutputStream("c:\\12.txt"), CHARSET_NAME);
        List<String> getList = new ArrayList<String>();
        List<String> setList = new ArrayList<String>();
        for (int i = 0; i < fields.length; i++) {
            System.out.println(fields[i].getName());
            System.out.println(fields[i].getType());
            getList.add(getGetMethodComment(fields[i].getComment()));
            getList.add(getGetMethodString(fields[i].getName(), fields[i].getType().getValue()));

            setList.add(getSetMethodComment(fields[i].getName(), fields[i].getComment()));
            setList.add(getSetMethodString(fields[i].getName(), fields[i].getType().getValue()));
        }

        for (String str : getList) {
            fileWriter.write(str);
        }
        for (String str : setList) {
            fileWriter.write(str);
        }
        fileWriter.close();

    }

    /**
     * Get方法生成
     * 
     * @param filedName
     * @return
     */
    protected static String getGetMethodString(String filedName, String filedType) {
        StringBuffer strBuf = new StringBuffer();
        strBuf.append("\tpublic " + filedType + " get" + get1substringUpperCase(filedName) + "() {\n");
        strBuf.append("\t\treturn " + filedName + ";\n");
        strBuf.append("\t}\n");
        return strBuf.toString();
    }

    /**
     * Get方法注释生成
     * 
     * @param filedName
     * @param comment
     * @return
     */
    protected static String getGetMethodComment(String comment) {
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
     * @param filedName
     * @param comment
     * @return
     */
    protected static String getSetMethodComment(String filedName, String comment) {
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
    protected static String getSetMethodString(String filedName, String filedType) {
        StringBuffer strBuf = new StringBuffer();
        strBuf.append("\tpublic void set" + get1substringUpperCase(filedName) + "(" + filedType + " " + filedName
                + ") {\n");
        strBuf.append("\t\tthis." + filedName + " = " + filedName + ";\n");
        strBuf.append("\t}\n");
        return strBuf.toString();
    }

    protected static String get1substringUpperCase(String str) {
        if (str != null && str.length() >= 1) {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        } else {
            return str;
        }
    }
}
