package com.tjhx.service.io;

import java.io.File;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tjhx.service.ServiceException;

@Service
public class FileManager {

	/**
	 * 保存用户上传的文件
	 * 
	 * @param imgFile
	 * @param fileName 文件路径
	 * @param fileName 文件名
	 */
	public void saveUploadFile(MultipartFile imgFile, String filePath, String fileName) {
		if (null == imgFile.getOriginalFilename()) {
			return;
		}
		File file = this.clearFile(filePath, fileName);
		try {
			imgFile.transferTo(file);
		} catch (Exception e) {
			throw new ServiceException(e);
		}

	}

	/**
	 * 清理文件(指定的文件名如果存在,则删除)
	 * 
	 * @param fileName 文件路径
	 * @param fileName 文件名
	 * @return
	 */
	private File clearFile(String filePath, String fileName) {
		File file = new File(filePath + fileName);
		if (file.exists()) {
			file.delete();
		}
		return file;
	}

}
