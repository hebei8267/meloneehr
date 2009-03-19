/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.generator;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.freedom.domain.FileInfo;

/**
 * 文件生成
 * 
 * @author 何贝
 * @since JDK1.5
 */
public interface IFileGenerator {
    /**
     * 代码文件生成
     * 
     * @param fileInfoList 文件信息类容列表
     * @param dataMap 数据字典Map
     * @throws IOException
     */
    public void createCodeFile(List<FileInfo> fileInfoList, Map<String, String> dataMap) throws IOException;
}
