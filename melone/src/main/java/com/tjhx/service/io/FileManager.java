package com.tjhx.service.io;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tjhx.common.utils.FileUtils;
import com.tjhx.common.utils.PhotoUtils;
import com.tjhx.service.ServiceException;

@Service
public class FileManager {
	public static final String SOURCE_FILE_FLG = "_old";

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

		// 自动建立文件夹
		FileUtils.mkdir(filePath);
		// 清理文件(指定的文件名如果存在,则删除)
		File _file = this.delFile(filePath, _getSourceFileName(fileName));
		File file = this.delFile(filePath, fileName);

		try {
			imgFile.transferTo(_file);
			// 等比例缩放图片
			PhotoUtils.imageResize(_file, file);
		} catch (Exception e) {
			throw new ServiceException(e);
		}

	}

	private String _getSourceFileName(String fileName) {
		if (StringUtils.isBlank(fileName)) {
			return "";
		}
		String _suffix = FileUtils.getSuffix(fileName);
		return FileUtils.getFileNameNoSuffix(fileName) + SOURCE_FILE_FLG + _suffix;
	}

	/**
	 * 清理文件(指定的文件名如果存在,则删除)
	 * 
	 * @param fileName 文件路径
	 * @param fileName 文件名
	 * @return
	 */
	private File delFile(String filePath, String fileName) {
		File _file = new File(filePath + fileName);
		if (_file.exists()) {
			_file.delete();
		}

		return _file;
	}

}
