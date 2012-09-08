package com.tjhx.common.web;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springside.modules.utils.SpringContextHolder;
import org.springside.modules.web.Servlets;

import com.tjhx.globals.Constants;
import com.tjhx.globals.SysConfig;

/**
 * 读取用户相片Servlet配置(禁止浏览器缓存)
 * 
 * 演示访问地址为： photoServlet?contentPath=img/logo.jpg
 * photoServlet?contentPath=img/logo.jpg&download=true
 */
public class PhotoContentServlet extends HttpServlet {

	private static final long serialVersionUID = 2697800680742328748L;
	private MimetypesFileTypeMap mimetypesFileTypeMap;

	/**
	 * 初始化.
	 */
	@Override
	public void init() throws ServletException {
		// 初始化mimeTypes, 默认缺少css的定义,添加之.
		mimetypesFileTypeMap = new MimetypesFileTypeMap();
		mimetypesFileTypeMap.addMimeTypes("text/css css");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 取得参数-照片名称
		String photoName = request.getParameter("photoName");
		if (StringUtils.isBlank(photoName)) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "contentPath parameter is required.");
			return;
		}
		// 取得参数-用户照片类型
		String fileType = request.getParameter("type");

		// 获取请求内容的基本信息.
		ContentInfo contentInfo = getContentInfo(photoName, fileType);

		// // 根据Etag或ModifiedSince Header判断客户端的缓存文件是否有效, 如仍有效则设置返回码为304,直接返回.
		// if (!Servlets.checkIfModifiedSince(request, response,
		// contentInfo.lastModified)
		// || !Servlets.checkIfNoneMatchEtag(request, response,
		// contentInfo.etag)) {
		// return;
		// }
		//
		// // 设置Etag/过期时间
		// Servlets.setExpiresHeader(response, Servlets.ONE_YEAR_SECONDS);
		// Servlets.setLastModifiedHeader(response, contentInfo.lastModified);
		// Servlets.setEtag(response, contentInfo.etag);

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

		if (contentInfo.file.exists()) {
			// 高效读取文件内容并输出,然后关闭input file
			FileUtils.copyFile(contentInfo.file, output);
		}

		output.flush();
	}

	/**
	 * 创建Content基本信息.
	 */
	protected ContentInfo getContentInfo(String photoName, String fileType) {
		ContentInfo contentInfo = new ContentInfo();

		// 系统配置信息Bean
		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");

		String realFilePath;
		if (StringUtils.isNotBlank(fileType) && Constants.PHOTO_TYPE_USER.equals(fileType)) {
			realFilePath = sysConfig.getUserPhotoPath() + photoName;
		} else {
			realFilePath = sysConfig.getProductPhotoPath() + photoName;
		}

		File file = new File(realFilePath);

		contentInfo.file = file;
		contentInfo.contentPath = photoName;
		contentInfo.fileName = file.getName();
		contentInfo.length = (int) file.length();

		contentInfo.lastModified = file.lastModified();
		contentInfo.etag = "W/\"" + contentInfo.lastModified + "\"";

		contentInfo.mimeType = mimetypesFileTypeMap.getContentType(contentInfo.fileName);

		contentInfo.needGzip = false;

		return contentInfo;
	}

	/**
	 * 定义Content的基本信息.
	 */
	static class ContentInfo {
		protected String contentPath;
		protected File file;
		protected String fileName;
		protected int length;
		protected String mimeType;
		protected long lastModified;
		protected String etag;
		protected boolean needGzip;
	}

}
