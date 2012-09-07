package com.tjhx.common.web;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springside.modules.web.Servlets;

/**
 * 本地静态内容展示与下载的Servlet.
 * 
 * 演示文件高效读取,客户端缓存控制及Gzip压缩传输.
 * 
 * 演示访问地址为： staticServlet?contentPath=static/img/logo.jpg
 * staticServlet?contentPath=static/img/logo.jpg&download=true
 */
public class StaticContentServlet extends BaseContentServlet {

	private static final long serialVersionUID = -1223956577871672196L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 取得参数
		String contentPath = request.getParameter("contentPath");
		if (StringUtils.isBlank(contentPath)) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "contentPath parameter is required.");
			return;
		}

		// 获取请求内容的基本信息.
		ContentInfo contentInfo = getContentInfo(contentPath);

		// 根据Etag或ModifiedSince Header判断客户端的缓存文件是否有效, 如仍有效则设置返回码为304,直接返回.
		if (!Servlets.checkIfModifiedSince(request, response, contentInfo.lastModified)
				|| !Servlets.checkIfNoneMatchEtag(request, response, contentInfo.etag)) {
			return;
		}

		// 设置Etag/过期时间
		Servlets.setExpiresHeader(response, Servlets.ONE_YEAR_SECONDS);
		Servlets.setLastModifiedHeader(response, contentInfo.lastModified);
		Servlets.setEtag(response, contentInfo.etag);

		// 设置MIME类型
		response.setContentType(contentInfo.mimeType);

		// 设置弹出下载文件请求窗口的Header
		if (request.getParameter("download") != null) {
			Servlets.setFileDownloadHeader(response, contentInfo.fileName);
		}

		// 构造OutputStream
		OutputStream output;
		if (checkAccetptGzip(request) && contentInfo.needGzip) {
			// 使用压缩传输的outputstream, 使用http1.1 trunked编码不设置content-length.
			output = buildGzipOutputStream(response);
		} else {
			// 使用普通outputstream, 设置content-length.
			response.setContentLength(contentInfo.length);
			output = response.getOutputStream();
		}

		// 高效读取文件内容并输出,然后关闭input file
		FileUtils.copyFile(contentInfo.file, output);
		output.flush();
	}

}
