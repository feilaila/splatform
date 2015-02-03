package com.sh.manage.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.sh.manage.module.config.ResourceConfig;
import com.sh.manage.module.page.Page;
import com.sh.manage.service.GroupService;
import com.sh.manage.service.RoleService;
import com.sh.manage.service.UserService;

/**
 * 上传文件控制
 * @author 
 */

@Controller
public class UploadController {
	
	private Logger logger = Logger.getLogger(UploadController.class);


	/**
	 * 上传文件管理service
	 */
	@Autowired
	private UserService userService;

	/**
	 * 会员角色管理service
	 */
	@Autowired
	private RoleService roleService;
	/**
	 * 注入groupSerice
	 */
	@Autowired
	private GroupService groupService;

	/** 当前页 */
	private int initPageNo = 1;

	/** 页面大小 */
	private int pageSize = 5;

	/** Page对象 */
	private Page page;

	/**
	 * 上传文件
	 * @return
	 */
	@RequestMapping(value = "/uploadImg")
	public ResponseEntity<String> uploadImg(
			@RequestParam(value = "userId", required = false, defaultValue = "") Integer userId,
			HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		logger.info("controller:..图片上传!");
		String msg="";
		boolean isCorrect = true;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", "text/html;charset=UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		
		String fileName = "";
		String newFileUrl = "";
		JSONObject result = new JSONObject();
        //保存  
        try {  
        	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request; 
        	CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("Filedata");
        	//这里是表单的名字，在swfupload.js中this.ensureDefault("file_post_name", "filedata");  
        	//文件原名称
    		fileName = file.getOriginalFilename();
    		//web文件存放路径
    		String webPath = ResourceConfig.getSysUploadAbsPath();
    		File targetFile = new File(webPath, fileName);  
            if(!targetFile.exists()){  
                targetFile.mkdirs();
            }
            file.transferTo(targetFile);  
            
            newFileUrl = request.getContextPath()+ResourceConfig.getSysUploadWebPath()+fileName;
            result.put("newFileUrl", newFileUrl);
            result.put("aid", 55);
        } catch (Exception e) {  
            e.printStackTrace();  
            return new ResponseEntity<String>("2222",responseHeaders, HttpStatus.CREATED);
        }  
        model.addAttribute("fileUrl", request.getContextPath()+ResourceConfig.getSysUploadWebPath()+fileName);  
		
		return new ResponseEntity<String>(result.toString(),responseHeaders, HttpStatus.CREATED);
	}

	
	
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Page getPage() {
		return page;
	}

	public int getInitPageNo() {
		return initPageNo;
	}

	public void setInitPageNo(int initPageNo) {
		this.initPageNo = initPageNo;
	}

	public void setPage(Page page) {
		this.page = page;
	}

}
