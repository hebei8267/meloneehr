package com.tjhx.common.web;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springside.modules.web.Servlets;

import com.tjhx.globals.Constants;

/**
 * 读取用户相片Servlet配置(禁止浏览器缓存)
 * 
 * 演示访问地址为： photoServlet?contentPath=img/logo.jpg
 * photoServlet?contentPath=img/logo.jpg&download=true
 */
public class PhotoContentServlet extends BaseContentServlet {

	private static final long serialVersionUID = 2697800680742328748L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 取得参数
		String contentPath = request.getParameter("contentPath");
		if (StringUtils.isBlank(contentPath)) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "contentPath parameter is required.");
			return;
		}
		contentPath = Constants.WEB_INF_PATH + contentPath;

		// 获取请求内容的基本信息.
		ContentInfo contentInfo = getContentInfo(contentPath);
		// 设置MIME类型
		response.setContentType(contentInfo.mimeType);

		// 设置弹出下载文件请求窗口的Header
		if (request.getParameter("download") != null) {
			Servlets.setFileDownloadHeader(response, contentInfo.fileName);
		}

		// 使用普通outputstream, 设置content-length.
		response.setContentLength(contentInfo.length);
		// 构造OutputStream
		OutputStream output = response.getOutputStream();

		// 高效读取文件内容并输出,然后关闭input file
		FileUtils.copyFile(contentInfo.file, output);
		output.flush();
	}

}
