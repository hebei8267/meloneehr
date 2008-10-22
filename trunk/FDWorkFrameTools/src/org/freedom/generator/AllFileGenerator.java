/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.generator;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.freedom.domain.FileInfo;
import org.freedom.file.ExcelFileUtils;
import org.freedom.file.FindFilesUtils;
import org.freedom.generator.impl.DaoFileGenerator;
import org.freedom.generator.impl.DaoTestFileGenerator;
import org.freedom.generator.impl.EntityFileGenerator;

/**
 * 代码生成Main
 * 
 * @author 何 贝
 */
public class AllFileGenerator {
    public final static String DATA_DICTIONARY_FILE_PATH = "C:\\eHR\\eclipse-java-europa-winter-win32\\eclipse\\workspace\\eHR2\\file\\Data.xls";

    public final static String UML_SRC_FILE_PATH = "C:\\eHR\\uml-src\\";

    public static void main(String[] args) throws IOException {
        AllFileGenerator fg = new AllFileGenerator();

        Map<String, String> dataMap = fg.getDataDictionaryContent(DATA_DICTIONARY_FILE_PATH);

        FindFilesUtils utils = new FindFilesUtils();

        List<FileInfo> fileInfoList = utils.getFileInfoList(UML_SRC_FILE_PATH);
        // entityFile生成
        fg.entityFileGenerator(fileInfoList, dataMap);
        // daoFile生成
        fg.daoFileGenerator(fileInfoList, dataMap);
        // daoTestFile生成
        fg.daoTestFileGenerator(fileInfoList, dataMap);
    }

    /**
     * entityFile生成
     * 
     * @param fileInfoList 文件内容List
     * @param dataMap 数据字典Map
     * @throws IOException
     */
    private void entityFileGenerator(List<FileInfo> fileInfoList, Map<String, String> dataMap) throws IOException {
        EntityFileGenerator entityFg = new EntityFileGenerator();
        entityFg.createCodeFile(fileInfoList, dataMap);
    }

    /**
     * daoTestFile生成
     * 
     * @param fileInfoList 文件内容List
     * @param dataMap 数据字典Map
     * @throws IOException
     */
    private void daoTestFileGenerator(List<FileInfo> fileInfoList, Map<String, String> dataMap) throws IOException {
        DaoTestFileGenerator daoTestFg = new DaoTestFileGenerator();
        daoTestFg.createCodeFile(fileInfoList, dataMap);
    }

    /**
     * daoFile生成
     * 
     * @param fileInfoList 文件内容List
     * @param dataMap 数据字典Map
     * @throws IOException
     */
    private void daoFileGenerator(List<FileInfo> fileInfoList, Map<String, String> dataMap) throws IOException {
        DaoFileGenerator daoFg = new DaoFileGenerator();
        daoFg.createCodeFile(fileInfoList, dataMap);
    }

    /**
     * 取得数据字典内容
     * 
     * @param filePaht 数据字典文件路径
     * @return
     */
    private Map<String, String> getDataDictionaryContent(String filePaht) {
        File file = new File(filePaht);

        List<List<List<String>>> workContent = ExcelFileUtils.readExcelFile(file);

        Map<String, String> dataMap = new HashMap<String, String>();

        for (List<List<String>> sheetContent : workContent) {
            for (List<String> rowContent : sheetContent) {

                String key = "";
                String value = "";
                for (int i = 0; i < rowContent.size(); i++) {
                    String cellValue = rowContent.get(i);

                    if (i == 0) {
                        key = cellValue;
                    } else if (i == 1) {
                        value = cellValue;
                    }
                }

                dataMap.put(key, value);
            }
        }

        return dataMap;
    }
}
