package generator;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import file.ExcelUtils;

public class DaoTestFileGenerator extends AbstractFileGenerator {
    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Documents and Settings\\kaka\\デスクトップ\\MyGame\\code\\MyGame\\generatorFiles\\Dao.xls");

        List<List<List<String>>> workContent = ExcelUtils.readExcel(file);

        for (List<List<String>> sheetContent : workContent) {
            for (List<String> rowContent : sheetContent) {

                String classNameStr = "";
                String packageNameStr = "";
                String annotationStr = "";
                for (int i = 0; i < rowContent.size(); i++) {
                    String cellValue = rowContent.get(i);

                    if (i == 0) {
                        classNameStr = cellValue;
                    } else if (i == 1) {
                        packageNameStr = cellValue;
                    } else {
                        annotationStr = cellValue;
                    }

                }
                createFileContent(classNameStr, packageNameStr, annotationStr);
            }
        }
    }

    private static void createFileContent(String classNameStr, String packageNameStr, String annotationStr)
            throws IOException {
        Writer fileWriter = null;

        createFolder(ROOT_PATH + getFolderName(packageNameStr));
        fileWriter = getFileWriter(ROOT_PATH + getFolderName(packageNameStr) + classNameStr + "DaoTest.java");

        StringBuffer fileContentBuf = new StringBuffer();

        fileContentBuf.append("package " + packageNameStr + ";\n\n");
        fileContentBuf.append("import cn.hb.core.test.dao.HibernateDaoTestCase;\n");
        fileContentBuf.append("import " + getEntityPackageName(packageNameStr) + "." + classNameStr + ";\n");
        fileContentBuf.append("import " + packageNameStr + "." + getDaoClassName(classNameStr) + ";\n\n/**");
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
