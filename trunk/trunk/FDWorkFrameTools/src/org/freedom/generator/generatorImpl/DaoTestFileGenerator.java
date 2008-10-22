/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.generator.generatorImpl;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import org.freedom.domain.FileInfo;
import org.freedom.generator.IFileGenerator;

import static org.freedom.constant.GeneratorUtilConstant.ROOT_PATH;
import static org.freedom.constant.GeneratorUtilConstant.DAO_TEST_FILE_PATH;
import static org.freedom.constant.GeneratorUtilConstant.DAO_TEST;
import static org.freedom.constant.GeneratorUtilConstant.JAVA_FILE;

/**
 * Dao Test 文件生成
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class DaoTestFileGenerator extends AbstractGenerator implements IFileGenerator {

    public void createCodeFile(List<FileInfo> fileInfoList, Map<String, String> dataMap) throws IOException {
        for (FileInfo fileInfo : fileInfoList) {
            String classNameStr = dataMap.get(fileInfo.getFileName());
            String packageNameStr = fileInfo.getFilePath();
            String annotationStr = fileInfo.getFileName();

            createCodeFile(classNameStr, packageNameStr, annotationStr);
        }
    }

    /**
     * 代码文件生成
     * 
     * @param classNameStr 类名
     * @param packageNameStr 包名
     * @param annotationStr 类注释名词
     * @throws IOException
     */
    public void createCodeFile(String classNameStr, String packageNameStr, String annotationStr) throws IOException {
        Writer fileWriter = null;

        createFolder(ROOT_PATH + DAO_TEST_FILE_PATH + getFolderName(packageNameStr));
        fileWriter = getFileWriter(ROOT_PATH + DAO_TEST_FILE_PATH + getFolderName(packageNameStr) + classNameStr
                + DAO_TEST + JAVA_FILE);

        StringBuffer fileContentBuf = new StringBuffer();

        fileContentBuf.append("package " + getDaoPackageName(packageNameStr) + ";\n\n");
        fileContentBuf.append("import cn.hb.core.test.dao.HibernateDaoTestCase;\n");
        fileContentBuf.append("import " + packageNameStr + "." + classNameStr + ";\n");
        fileContentBuf.append("import " + getDaoPackageName(packageNameStr) + "." + getDaoClassName(classNameStr)
                + ";\n\n/**");
        fileContentBuf.append("\n * " + annotationStr);
        fileContentBuf.append(" DaoTest\n */\npublic class ");
        fileContentBuf.append(classNameStr);
        fileContentBuf.append("DaoTest extends HibernateDaoTestCase {\n");
        fileContentBuf.append("\tprivate " + classNameStr + "Dao " + getComponentName(classNameStr) + "Dao;\n\n");
        fileContentBuf.append("\tpublic " + classNameStr + "Dao get" + classNameStr + "Dao() {\n");
        fileContentBuf.append("\t\treturn " + getComponentName(classNameStr) + "Dao;\n");
        fileContentBuf.append("\t}\n\n");

        fileContentBuf.append("\tpublic void set" + classNameStr + "Dao(" + classNameStr + "Dao "
                + getComponentName(classNameStr) + "Dao) {\n");
        fileContentBuf.append("\t\tthis." + getComponentName(classNameStr) + "Dao = " + getComponentName(classNameStr)
                + "Dao;\n");
        fileContentBuf.append("\t}\n\n");
        fileContentBuf.append("\tpublic void testCase() {\n");
        fileContentBuf.append("\t\t" + classNameStr + " " + getComponentName(classNameStr) + " = new " + classNameStr
                + "();\n");
        fileContentBuf.append("\t}\n");
        fileContentBuf.append("\n}\n");

        fileWriter.write(fileContentBuf.toString());
        fileWriter.close();
    }

}
