package generator;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import file.ExcelUtils;

public class DaoFileGenerator extends AbstractFileGenerator {

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
        fileWriter = getFileWriter(ROOT_PATH + getFolderName(packageNameStr) + classNameStr + "Dao.java");

        StringBuffer fileContentBuf = new StringBuffer();
        fileContentBuf.append("package " + packageNameStr + ";\n\n");
        fileContentBuf.append("import org.springframework.stereotype.Component;\n");
        fileContentBuf.append("import cn.hb.core.dao.impl.HibernateDaoImpl;\n");
        fileContentBuf.append("import " + getEntityPackageName(packageNameStr) + "." + classNameStr + ";\n");
        fileContentBuf.append("\n/**\n");
        fileContentBuf.append(" * " + annotationStr);
        fileContentBuf.append("Dao\n */\n");
        fileContentBuf.append("@Component(\"" + getComponentName(classNameStr) + "Dao\")\npublic class ");
        fileContentBuf.append(classNameStr);
        fileContentBuf.append("Dao extends HibernateDaoImpl<");
        fileContentBuf.append(classNameStr);
        fileContentBuf.append("> {\n}\n");

        fileWriter.write(fileContentBuf.toString());
        fileWriter.close();
    }
}
