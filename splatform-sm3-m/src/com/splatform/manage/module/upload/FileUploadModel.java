package com.splatform.manage.module.upload;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import com.splatform.manage.module.config.ResourceConfig;
import com.splatform.manage.utils.FileUtil;
import com.splatform.manage.utils.RandomUtil;
import com.splatform.manage.utils.TimeUtil;



/**
 * Title. <br>
 * Description:文件上传
 * <p>
 * <p>
 * Company: 
 * <p>
 * Author: 
 * <p>
 * Version: 1.0
 * <p>
 * 修改人：
 * <p>
 * <p>
 * 修改内容：
 */
public class FileUploadModel {

	public void fileUpload(HttpServletRequest request) {
		try {
			// 获取web上传文件路径
			String uploadDir = ResourceConfig.getSysUploadWebPath();
			// 前缀 文件夹
			String prefix = TimeUtil.nowDate();
			// 存放文件的临时文件夹
			String tempDir = uploadDir + prefix + File.separator;
			File fileDir = new File(tempDir);
			if (!fileDir.exists()) {
				fileDir.mkdir();
			}
			// 磁盘文件项工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// threshold 极限、临界值，即硬盘缓存 1M 达到1M后flush
			factory.setSizeThreshold(1024 * 1024);
			// repository 贮藏室，即临时文件目录
			factory.setRepository(new File(tempDir));
			// 文件上传核心类
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 设置允许上传的最大文件大小 5M
			upload.setSizeMax(5 * 1024 * 1024);

			// HttpServletRequest request = ServletActionContext.getRequest();
			// 解析HTTP请求消息头
			List<FileItem> fileItems = upload.parseRequest(request);
			log.info("length：" + fileItems.size());
			Iterator<FileItem> iter = fileItems.iterator();
			while (iter.hasNext()) {
				FileItem item = iter.next();
				if (!item.isFormField()) {
					log.info("处理上传的文件 ...");
					// 此时的文件名包含了完整的路径，得注意加工一下
					filename = item.getName();
					// 上传文件的大小
					long fileSize = item.getSize();
					if ("".equals(filename) && fileSize == 0) {
						log.info("获取文件名失败，文件名为空...");
						return;
					} else if (fileSize > 20 * 1024 * 1024) {
						// 如果上传的软件超过20M
						log.info("文件保存失败，上传文件过大，请控制在20M以内");
						return;
					}
					log.info("完整的文件名：" + filename);
					// 文件名
					String suffix = TimeUtil.now() + "_"
							+ RandomUtil.randomAlphabetic(10);
					// 文件扩展名
					String nameExt = "";
					// 获取扩展名即获取文件的格式
					nameExt = FileUtil.getNameExt(item.getName());
					;
					// 处理后的完整的文件名
					String tmpPath = tempDir + suffix + "." + nameExt;
					proFileName = prefix + File.separator + suffix + "."
							+ nameExt;
					log.info("proFileName:" + proFileName);
					log.info("完整文件名tmpPath:" + tmpPath);
					File uploadFile = new File(tmpPath);
					if (!uploadFile.exists()) {
						uploadFile.createNewFile();
					}
					// 将上传的文件写到指定的文件中
					item.write(uploadFile);
					log.info("文件保存完毕");
				} else {
					String name = item.getFieldName();
					String value = item.getString();
					log.info("表单....." + name + "=" + value);
				}
			}
		} catch (Exception e) {
			log.info("上传文件发生异常 ...");
			e.printStackTrace();
		}
	}

	// 日志跟踪
	private final Logger log = Logger.getLogger(FileUploadModel.class);

	// 处理后的完整的文件名
	private String proFileName;

	// 上传文件的完整文件名
	private String filename = "";

	public String getProFileName() {
		return proFileName;
	}

	public void setProFileName(String proFileName) {
		this.proFileName = proFileName;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

}
