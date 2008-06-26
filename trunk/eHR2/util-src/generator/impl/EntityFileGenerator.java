package generator.impl;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import entity.FileInfo;

import static constant.UtilConstant.ROOT_PATH;
import static constant.UtilConstant.ENTITY_FILE_PATH;
import static constant.UtilConstant.JAVA_FILE;
import static constant.UtilConstant.IMPORT_JAVA_KEY;
import static constant.UtilConstant.CLASS_JAVA_KEY;
import static constant.UtilConstant.PRIVATE_JAVA_KEY;
import static constant.UtilConstant.STRING_JAVA_KEY;

/**
 * @author kaka
 * 
 * Entity文件生成
 */
public class EntityFileGenerator extends AbstractFileGenerator {

    public void createCodeFile(List<FileInfo> fileInfoList, Map<String, String> dataMap) throws IOException {
        for (FileInfo fileInfo : fileInfoList) {
            String classNameStr = dataMap.get(fileInfo.getFileName());
            String packageNameStr = fileInfo.getFilePath();

            List<String> fileContent = new ArrayList<String>();

            List<String> setMethodContentList = new ArrayList<String>();
            List<String> getMethodContentList = new ArrayList<String>();

            boolean importSuperClassPackageFlag = false;
            boolean constructMethodFlag = false;
            for (String line : fileInfo.getFileContent()) {

                if (line != null && !line.trim().equals("")) {
                    boolean containFlag = false;

                    for (Entry<String, String> entry : dataMap.entrySet()) {
                        if (line.indexOf(entry.getKey()) != -1) {
                            String tempLine = "";
                            if (line.equals(CLASS_JAVA_KEY)) {
                                tempLine = line.replaceAll(entry.getKey(), entry.getValue() + getSuperClass());

                            } else {
                                tempLine = line.replaceAll(entry.getKey(), entry.getValue());
                            }

                            if (!line.startsWith(IMPORT_JAVA_KEY)) {// 包导行入除外
                                if (line.startsWith(CLASS_JAVA_KEY)) {
                                    // Class注释生成
                                    fileContent.add(getClassComment(entry.getKey()));
                                } else {
                                    // Filed注释生成
                                    fileContent.add(getFiledComment(entry.getKey()));
                                }
                            } else {
                                if (!importSuperClassPackageFlag) {// 导入指定包
                                    fileContent.add(importSuperClassPackage());
                                    importSuperClassPackageFlag = true;
                                }
                            }

                            fileContent.add(tempLine);

                            if (line.startsWith(CLASS_JAVA_KEY) && !constructMethodFlag) {// 导入默认构造函数
                                fileContent.add(getConstructMethod(entry.getValue()));
                                constructMethodFlag = true;
                            }

                            if (line.trim().startsWith(PRIVATE_JAVA_KEY)) {
                                setMethodContentList.add(getSetMethodComment(entry.getValue(), entry.getKey()));
                                setMethodContentList.add(getSetMethodString(entry.getValue(), STRING_JAVA_KEY));

                                getMethodContentList.add(getGetMethodComment(entry.getKey()));
                                getMethodContentList.add(getGetMethodString(entry.getValue(), STRING_JAVA_KEY));
                            }
                            containFlag = true;
                        }
                    }

                    if (!containFlag) {
                        fileContent.add(line);
                    }

                } else {
                    fileContent.add(line);
                }
            }

            fileContent.addAll(fileContent.size() - 2, getMethodContentList);
            fileContent.addAll(fileContent.size() - 2, setMethodContentList);
            createCodeFile(classNameStr, packageNameStr, fileContent);
        }

    }

    private String getConstructMethod(String key) {
        StringBuffer strBuf = new StringBuffer();
        strBuf.append("\tpublic " + key + "() {" + "\n");
        strBuf.append("\t}" + "\n");
        return strBuf.toString();
    }

    private String importSuperClassPackage() {
        return "import cn.hb.core.bean.AbstractEntityBean;";
    }

    private String getSuperClass() {
        return " extends AbstractEntityBean ";
    }

    /**
     * @param classNameStr 类名
     * @param packageNameStr 包名
     * @param fileContent 文件内容
     * @throws IOException
     */
    protected void createCodeFile(String classNameStr, String packageNameStr, List<String> fileContent)
            throws IOException {
        Writer fileWriter = null;

        createFolder(ROOT_PATH + ENTITY_FILE_PATH + getFolderName(packageNameStr));
        fileWriter = getFileWriter(ROOT_PATH + ENTITY_FILE_PATH + getFolderName(packageNameStr) + classNameStr
                + JAVA_FILE);

        for (String line : fileContent) {
            fileWriter.write(line + "\n");
        }
        fileWriter.close();
    }
}
