package com.tjhx.common.utils;

import java.io.File;

import org.apache.commons.lang3.StringUtils;

public class FileUtils {

	/**
	 * 自动建立文件夹
	 * 
	 * @param filePath 立文件夹路径
	 */
	public static void mkdir(String filePath) {
		File _filePath = new File(filePath);
		if (!_filePath.exists()) {
			_filePath.mkdir();
		}
	}

	/**
	 * 得到文件后缀(如sample.jpg->.jpg)
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getSuffix(String fileName) {
		if (StringUtils.isBlank(fileName)) {
			return "";
		}
		int pos = fileName.lastIndexOf(".");
		if (pos < 0) {
			return fileName.substring(fileName.length() - 3).toLowerCase();
		} else {
			return fileName.substring(pos).toLowerCase();
		}
	}

	/**
	 * 得到文件名称无后缀(如sample.jpg->sample)
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileNameNoSuffix(String fileName) {
		if (StringUtils.isBlank(fileName)) {
			return "";
		}
		int pos = fileName.lastIndexOf(".");
		return fileName.substring(0, pos);
	}

}
