package generator.impl;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import domain.FileInfo;

/**
 * @author kaka
 * 
 * Dao文件生成
 */
public class DaoFileGenerator extends AbstractFileGenerator {

    public void createCodeFile(List<FileInfo> fileInfoList, Map<String, String> dataMap) throws IOException {

        for (FileInfo fileInfo : fileInfoList) {
            String classNameStr = dataMap.get(fileInfo.getFileName());
            String packageNameStr = fileInfo.getFileName();
            String annotationStr = fileInfo.getFileName();

            createCodeFile(classNameStr, packageNameStr, annotationStr);
        }

    }

    @Override
    protected void createCodeFile(String classNameStr, String packageNameStr, String annotationStr) throws IOException {
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
