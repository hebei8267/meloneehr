package uploadify;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class Uploadify {

	@RequestMapping("/uploadify")
	public String init(Model model) {

		return "uploadify";
	}

	@RequestMapping("/uploadFile")
	public String uploadFile(HttpServletRequest request) throws IllegalStateException, IOException {
		MultipartFile imgFile = getMultipartFile(request);
System.out.println(imgFile.getOriginalFilename());
		saveUploadFile(imgFile, "c:/aaa/", imgFile.getOriginalFilename());
		return "uploadify";
	}

	public void saveUploadFile(MultipartFile imgFile, String filePath, String fileName) throws IllegalStateException,
			IOException {
		if (0 == imgFile.getSize() || null == imgFile.getOriginalFilename()) {
			return;
		}

		// 自动建立文件夹
		mkdir(filePath);
		// 清理文件(指定的文件名如果存在,则删除)
		File file = this.delFile(filePath, fileName);

		imgFile.transferTo(file);

	}

	private File delFile(String filePath, String fileName) {
		return delFile(filePath + fileName);
	}

	public void mkdir(String filePath) {
		File _filePath = new File(filePath);
		if (!_filePath.exists()) {
			_filePath.mkdir();
		}
	}

	private File delFile(String fileFullPath) {
		File _file = new File(fileFullPath);
		if (_file.exists()) {
			_file.delete();
		}

		return _file;
	}

	private MultipartFile getMultipartFile(HttpServletRequest request) {
		// 转型为MultipartHttpRequest：
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// 获得文件
		MultipartFile imgFile = multipartRequest.getFile("file");

		return imgFile;
	}
}
