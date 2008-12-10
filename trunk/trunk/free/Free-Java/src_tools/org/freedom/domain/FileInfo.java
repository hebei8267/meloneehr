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

    /**
     * 文件package名称 暂时该程序只处理java文件
     */
    private String packageName;

    /**
     * 文件名称 不包括后缀名
     */
    private String shortFileName;

    /**
     * 文件路径 包括文件名称
     */
    private String fileAllPath;

    /**
     * 文件路径 不包括文件名称
     */
    private String filePath;

    private List<String> fileContent = new ArrayList<String>();

    /**
     * 取得文件package名称 暂时该程序只处理java文件
     * 
     * @return 文件package名称 暂时该程序只处理java文件
     */
    public String getPackageName() {
        return packageName;
    }

    /**
     * 设置文件package名称 暂时该程序只处理java文件
     * 
     * @param packageName 文件package名称 暂时该程序只处理java文件
     */
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    /**
     * 取得文件名称 不包括后缀名
     * 
     * @return 文件名称 不包括后缀名
     */
    public String getShortFileName() {
        return shortFileName;
    }

    /**
     * 设置文件名称 不包括后缀名
     * 
     * @param shortFileName 文件名称 不包括后缀名
     */
    public void setShortFileName(String shortFileName) {
        this.shortFileName = shortFileName;
    }

    /**
     * 取得文件路径 包括文件名称
     * 
     * @return 文件路径 包括文件名称
     */
    public String getFileAllPath() {
        return fileAllPath;
    }

    /**
     * 设置文件路径 包括文件名称
     * 
     * @param fileAllPath 文件路径 包括文件名称
     */
    public void setFileAllPath(String fileAllPath) {
        this.fileAllPath = fileAllPath;
    }

    /**
     * 取得文件路径 不包括文件名称
     * 
     * @return 文件路径 不包括文件名称
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * 设置文件路径 不包括文件名称
     * 
     * @param filePath 文件路径 不包括文件名称
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public List<String> getFileContent() {
        return fileContent;
    }

    public void setFileContent(List<String> fileContent) {
        this.fileContent = fileContent;
    }

}
