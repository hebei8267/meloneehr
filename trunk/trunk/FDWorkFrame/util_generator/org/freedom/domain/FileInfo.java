/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.domain;

import java.util.ArrayList;
import java.util.List;

import org.freedom.core.bean.BaseBean;

/**
 * 文件信息类容
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class FileInfo extends BaseBean {

    private static final long serialVersionUID = 2602313105984510524L;

    private String filePath;
    private String fileName;
    private List<String> fileContent = new ArrayList<String>();

    public String getFilePath() {
        return filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public List<String> getFileContent() {
        return fileContent;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileContent(List<String> fileContent) {
        this.fileContent = fileContent;
    }

}
