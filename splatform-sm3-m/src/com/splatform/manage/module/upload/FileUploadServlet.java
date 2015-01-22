package com.splatform.manage.module.upload;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.splatform.manage.module.config.ResourceConfig;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 
 * <p>
 * Company: 
 * <p>
 * Author: 
 * <p>
 * Version: 1.0
 * <p>
 */
@SuppressWarnings("serial")
public class FileUploadServlet extends HttpServlet {
	@Override
	public void init() throws ServletException {
		super.init();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		FileUploadModel file = new FileUploadModel();
		file.fileUpload(req);
		// String err = "";
		// try {
		// /* 检查文件大小 */
		// if (maxSize > 0 && uploadFile.getSize() > maxSize) {
		// printInfo(response, "上传文件的大小超出限制", "");
		// return;
		// }
		// printInfo(resp, err, file.getProFileName());
		// } catch (Exception ex) {
		// }
		printInfo(resp, "", ResourceConfig.getSysUploadWebPath()
				+ file.getProFileName());
	}

	/**
	 * 使用I/O流输出 json格式的数据
	 * 
	 * @param response
	 * @param err
	 * @param newFileName
	 * @throws IOException
	 */
	public void printInfo(HttpServletResponse response, String err,
			String newFileName) throws IOException {

		PrintWriter out = response.getWriter();
		out
				.println("{\"err\":\"" + err + "\",\"msg\":\"" + newFileName
						+ "\"}");
		out.flush();
		out.close();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	public void destroy() {
		super.destroy();
	}
}
