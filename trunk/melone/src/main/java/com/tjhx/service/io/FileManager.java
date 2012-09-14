package com.tjhx.service.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tjhx.common.utils.FileUtils;
import com.tjhx.common.utils.PhotoUtils;
import com.tjhx.globals.Constants;

@Service
public class FileManager {
	public static final String SOURCE_FILE_FLG = "_old";

	/**
	 * 保存用户上传的文件
	 * 
	 * @param imgFile
	 * @param fileName 文件路径
	 * @param fileName 文件名
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public void saveUploadFile(MultipartFile imgFile, String filePath, String fileName) throws IllegalStateException,
			IOException {
		if (0 == imgFile.getSize() || null == imgFile.getOriginalFilename()) {
			return;
		}

		// 自动建立文件夹
		FileUtils.mkdir(filePath);
		// 清理文件(指定的文件名如果存在,则删除)
		File _file = this.delFile(filePath, _getSourceFileName(fileName));
		File file = this.delFile(filePath, fileName);

		imgFile.transferTo(_file);
		// 等比例缩放图片
		PhotoUtils.imageResize(_file, file);

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
		return delFile(filePath + fileName);
	}

	/**
	 * 清理文件(指定的文件名如果存在,则删除)
	 * 
	 * @param fileFullPath 文件全路径
	 * @return
	 */
	private File delFile(String fileFullPath) {
		File _file = new File(fileFullPath);
		if (_file.exists()) {
			_file.delete();
		}

		return _file;
	}

	/**
	 * 将指定内容写入文件中
	 * 
	 * @param fileFullPath 文件全路径
	 * @param fileContent 文件内容
	 * @throws IOException
	 */
	public void writeFile(String fileFullPath, String fileContent) throws IOException {

		FileOutputStream writerStream = new FileOutputStream(fileFullPath);
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(writerStream, Constants.CHARSET_UTF_8));

		out.write(fileContent);
		out.close();
	}

}
