package com.tjhx.service.io;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tjhx.service.ServiceException;

@Service
public class FileManager {

	/**
	 * 保存用户上传的文件
	 * 
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	public void saveUploadFile(MultipartFile imgFile, String fileName) {
		if (null == imgFile.getOriginalFilename()) {
			return;
		}
		File file = this.clearFile(fileName);
		try {
			imgFile.transferTo(file);
		} catch (Exception e) {
			throw new ServiceException(e);
		}

	}

	/**
	 * 清理文件(指定的文件名如果存在,则删除)
	 * 
	 * @param fileName
	 * @return
	 */
	private File clearFile(String fileName) {
		// TODO ？？？？？
		File file = new File("c:/" + fileName);
		if (file.exists()) {
			file.delete();
		}
		return file;
	}

}
