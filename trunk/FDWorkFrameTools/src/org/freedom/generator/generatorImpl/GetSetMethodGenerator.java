/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.generator.generatorImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

import org.freedom.domain.FileInfo;
import org.freedom.generator.IGetSetMethodGenerator;

import com.thoughtworks.qdox.JavaDocBuilder;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaField;

import static org.freedom.constant.GeneratorUtilConstant.CHARSET_NAME;
import static org.freedom.constant.GeneratorUtilConstant.POINT;

/**
 * Get Set Method文件生成实现
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class GetSetMethodGenerator extends AbstractGenerator implements IGetSetMethodGenerator {

    public void createMethodFile(List<FileInfo> fileInfoList, String inputSrcFilePath, String outputSrcFilePath)
            throws IOException {

        for (FileInfo fileInfo : fileInfoList) {
            JavaDocBuilder builder = new JavaDocBuilder();
            File _file = new File(fileInfo.getFileAllPath());

            try {
                // 添加java文件,并自定文件读取是的编码方式
                builder.addSource(new InputStreamReader(new FileInputStream(_file), CHARSET_NAME));
            } catch (Exception e) {
                System.out.println("method source generator failure! file path: " + fileInfo.getFileAllPath());
                // System.out.println("Exception Message: " + e.getMessage());
                // 跳过错误,继续处理
                continue;
            }

            // 取得java类型定义名词
            JavaClass cls = builder.getClassByName(getJavaClassName(fileInfo));

            // 创建输出文件夹
            createFolder(getOutputFilePath(fileInfo, inputSrcFilePath, outputSrcFilePath));

            Writer fileWriter = new OutputStreamWriter(new FileOutputStream(getOutputFileAllPath(fileInfo,
                    inputSrcFilePath, outputSrcFilePath)), CHARSET_NAME);

            JavaField[] fields = cls.getFields();
            for (int i = 0; i < fields.length; i++) {

                int _index = fields[i].getType().getValue().lastIndexOf(POINT) == -1 ? 0 : fields[i].getType()
                        .getValue().lastIndexOf(POINT) + 1;
                String _type = fields[i].getType().getValue().substring(_index);

                fileWriter.write(getGetMethodComment(fields[i].getComment()));
                fileWriter.write(getGetMethodString(fields[i].getName(), _type));

                fileWriter.write(getSetMethodComment(fields[i].getName(), fields[i].getComment()));
                fileWriter.write(getSetMethodString(fields[i].getName(), _type));
            }

            fileWriter.close();
        }

    }

    /**
     * 取得java类型定义名词
     * 
     * @param fileInfo
     * @return
     */
    private String getJavaClassName(FileInfo fileInfo) {
        return fileInfo.getPackageName() + POINT + fileInfo.getShortFileName();
    }

    /**
     * 取得输出文件路径 包括文件名称
     * 
     * @param fileInfo
     * @return
     */
    private String getOutputFileAllPath(FileInfo fileInfo, String inputSrcFilePath, String outputSrcFilePath) {
        return fileInfo.getFileAllPath().replace(inputSrcFilePath, outputSrcFilePath);
    }

    /**
     * 取得输出文件路径 不包括文件名称
     * 
     * @param fileInfo
     * @return
     */
    private String getOutputFilePath(FileInfo fileInfo, String inputSrcFilePath, String outputSrcFilePath) {
        return fileInfo.getFilePath().replace(inputSrcFilePath, outputSrcFilePath);
    }

}
