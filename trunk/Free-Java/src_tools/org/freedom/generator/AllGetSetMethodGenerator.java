/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.generator;

import java.io.IOException;
import java.util.List;

import org.freedom.domain.FileInfo;
import org.freedom.file.FindFilesUtils;
import org.freedom.generator.generatorImpl.GetSetMethodGenerator;

/**
 * Get Set 方法生成Main
 * 
 * @author 何 贝
 */
public class AllGetSetMethodGenerator {
   // public final static String INPUT_SRC_FILE_PATH = "E:\\java\\eclipse-java\\eclipse\\workspace\\FDFrame-SpringMVC";
    public final static String INPUT_SRC_FILE_PATH = "C:\\eHR\\eclipse-java-europa-winter-win32\\eclipse\\workspace\\free\\Free-Java\\src_app\\org\\freedom";

    public final static String OUTPUT_SRC_FILE_PATH = "C:\\456";

    public static void main(String[] args) throws IOException {
        GetSetMethodGenerator gen = new GetSetMethodGenerator();
        FindFilesUtils utils = new FindFilesUtils();

        List<FileInfo> fileInfoList = utils.getFileInfoList(INPUT_SRC_FILE_PATH);
        gen.createMethodFile(fileInfoList, INPUT_SRC_FILE_PATH, OUTPUT_SRC_FILE_PATH);

    }

}
