/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.generator;

import java.util.List;

import org.freedom.domain.FileInfo;

/**
 * Get Set 方法生成
 * 
 * @author 何贝
 * @since JDK1.5
 */
public interface IGetSetMethodGenerator {
    /**
     * Get Set 方法生成
     * 
     * @param fileInfoList 文件信息类容列表
     * @param inputSrcFilePath 输入文件夹路径
     * @param outputSrcFilePath 输出文件夹路径
     * @throws Exception
     */
    public void createMethodFile(List<FileInfo> fileInfoList, String inputSrcFilePath, String outputSrcFilePath)
            throws Exception;
}
