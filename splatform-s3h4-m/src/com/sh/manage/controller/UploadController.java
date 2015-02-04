package com.sh.manage.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

import com.sh.manage.constants.Constants;
import com.sh.manage.entity.SysAttachment;
import com.sh.manage.entity.SysUser;
import com.sh.manage.module.config.ResourceConfig;
import com.sh.manage.service.UploadService;
import com.sh.manage.service.UserService;
import com.sh.manage.utils.TimeUtil;

/**
 * 上传文件控制
 * @author 
 */

@Controller
public class UploadController {
	
	private Logger logger = Logger.getLogger(UploadController.class);


	/**
	 * 用户会员管理service
	 */
	@Autowired
	private UserService userService;
	
	
	/**
	 * 上传管理service
	 */
	@Autowired
	private UploadService uploadService;

	/**
	 * 上传文件
	 * @return
	 */
	@RequestMapping(value = "/uploadImg")
	public ResponseEntity<String> uploadImg(
			@RequestParam(value = "userId", required = false, defaultValue = "") Integer userId,
			HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		logger.info("controller:..图片上传!");

		HttpHeaders responseHeaders = new HttpHeaders();
		HttpSession session = request.getSession();
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
            
            newFileUrl = ResourceConfig.getSysUploadWebPath()+fileName;
            result.put("newFileUrl", newFileUrl);
            
            //new attach
            SysAttachment sysAttachment = new SysAttachment();
            sysAttachment.setFilename(fileName);
            sysAttachment.setFilepath(newFileUrl);
            sysAttachment.setNewfilename(fileName);
            sysAttachment.setUploadtime(TimeUtil.now());
            sysAttachment.setUid(userId);
            sysAttachment.setType(Constants.ATTACH_TYPE_FACEIMG);//头像类型
            
            Integer faceimgAid = uploadService.addFile(sysAttachment);
            result.put("aid", faceimgAid);
            //get user
            SysUser sysUser = userService.findSysUser(userId);
            sysUser.setFaceimgAid(faceimgAid);
            userService.editSysUser(sysUser);
            
            session.removeAttribute("faceimgPath");
            session.setAttribute("faceimgPath", newFileUrl);
            logger.info("controller:..图片上传结束!");
        } catch (Exception e) {
            e.printStackTrace();  
            return new ResponseEntity<String>("error",responseHeaders, HttpStatus.CREATED);
        }  
        //model.addAttribute("fileUrl", request.getContextPath()+ResourceConfig.getSysUploadWebPath()+fileName);  
		return new ResponseEntity<String>(result.toString(),responseHeaders, HttpStatus.CREATED);
	}

}
