package com.sh.manage.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sh.manage.constants.Constants;
import com.sh.manage.constants.SessionConstants;
import com.sh.manage.entity.MukeCourse;
import com.sh.manage.entity.MukeCourseType;
import com.sh.manage.entity.SysAttachment;
import com.sh.manage.module.page.Page;
import com.sh.manage.pojo.LoginUser;
import com.sh.manage.pojo.MukeCourseDTO;
import com.sh.manage.service.CourseService;
import com.sh.manage.service.UploadService;
import com.sh.manage.service.UserService;
import com.sh.manage.utils.TimeUtil;
import com.sh.manage.utils.WebUtils;

/**
 * 课程管理控制
 * @author 
 */

@Controller
public class CourseController {
	
	private Logger logger = Logger.getLogger(CourseController.class);


	/**
	 * 课程课程管理service
	 */
	@Autowired
	private UserService userService;

	/**
	 * 课程管理service
	 */
	@Autowired
	private CourseService courseService;
	
	/**
	 * 上传管理service
	 */
	@Autowired
	private UploadService uploadService;
	

	/** 当前页 */
	private int initPageNo = 1;

	/** 页面大小 */
	private int pageSize = 5;

	/** Page对象 */
	private Page page;

	/**
	 * 跳转课程管理页面
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/coursemanage.do")
	public ModelAndView courseManagePage(
			@RequestParam(value = "parentId", required = false, defaultValue = "") Integer parentId,
			@RequestParam(value = "ownId", required = false, defaultValue = "") Integer ownId,
			@RequestParam(value = "name", required = false, defaultValue = "") String name,
			@RequestParam(value = "startDate", required = false, defaultValue = "") String startDate,
			@RequestParam(value = "pageNo", required = false, defaultValue = "") Integer pageNo) {
		// 获取课程以及等级
		if (null == pageNo) {
			pageNo = initPageNo;
		}
		// 返回课程列表页
		ModelAndView model = new ModelAndView("/course/course_manage");
		model.addObject("name", name);
		model.addObject("startDate", startDate);
		// 返回的page对象
		page = courseService.findAllMukeCourse(
				name, startDate.replaceAll("-", ""),
				pageNo, pageSize);
		// 课程列表
		List<MukeCourse> courseList = (List<MukeCourse>) page.getList();

		// 课程类型
		List<MukeCourseType> courseTypeList = courseService.findAllCourseType();
		
		// 翻页带参数
		if(null != name){
			page.addParam("name",name);
		}
		if(null != startDate){
			page.addParam("startDate",startDate);
		}
		model.addObject("startDate", startDate);
		model.addObject("name", name);
		model.addObject("pageSize", pageSize);
		model.addObject("page", page);
		model.addObject("parentId", parentId);
		model.addObject("ownId", ownId);
		model.addObject("courseList", courseList);
		model.addObject("courseTypeList", courseTypeList);
		//model.addObject("roleList", roleList);
		return model;
	}

	
	
	/**
	 * 跳转课程添加页面
	 */
	@RequestMapping(value="/toAddCourse.do")
    public ModelAndView courseAddPage(HttpServletRequest req,
			HttpServletResponse resp) {
		HttpSession session = req.getSession();
		ModelAndView model = new ModelAndView("/course/course_add");
		
		//获取课程信息
    	LoginUser _loginUser = (LoginUser) session.getAttribute(SessionConstants.LOGIN_USER);
		if (null != _loginUser) {
			// 课程类型
			List<MukeCourseType> courseTypeList = courseService.findAllCourseType();
			model.addObject("courseTypeList", courseTypeList);
			model.addObject("loginUser",_loginUser);
			logger.info("courseTypeList.size:"+courseTypeList.size());
		}
        return model;
    }
	
	
	/**
	 * 课程添加
	 * @return
	 */
	@RequestMapping(value = "/doAddCourse.do", method = RequestMethod.POST)
	public ResponseEntity<String> courseAdd(
			@RequestParam(value = "typeId", required = false, defaultValue = "0") Integer typeId,
			@RequestParam(value = "img", required = false, defaultValue = "") String img,
			@RequestParam(value = "info", required = false, defaultValue = "") String info,
			@RequestParam(value = "name", required = false, defaultValue = "") String name,
			@RequestParam(value = "title", required = false, defaultValue = "") String title,
			@RequestParam(value = "uid", required = false, defaultValue = "") Integer uid,
			HttpServletRequest request,HttpServletResponse response,
			Model model) {
		logger.info("controller:..课程添加!");
		String msg="";
		boolean isCorrect = true;
		HttpHeaders responseHeaders = new HttpHeaders();
		HttpSession session = request.getSession();
		responseHeaders.set("Content-Type", "text/html;charset=UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		try{
			//获取课程信息
	    	LoginUser _loginUser = (LoginUser) session.getAttribute(SessionConstants.LOGIN_USER);
			if (null != _loginUser) {

				//new course
				MukeCourse course = new MukeCourse();
				
				// get|new role
				//SysGroup group = groupService.findSysGroup(suGroupId);
				course.setCreateTime(TimeUtil.now());
				course.setImg(img);
				course.setInfo(info);
				course.setName(name);
				course.setTitle(title);
				course.setTypeId(typeId);
				course.setUid(_loginUser.getId());
				course.setStatus(Constants.COURSE_STATUS_INIT);//状态 0待审核  1已审核  2 已下线 ;默认为0
								
				int result = courseService.addCourse(course);
				if(result > 0){
					msg="课程添加成功!";
				}else{
					msg="课程添加失败!";
				}
			
			}
		}catch(Exception e){
			logger.error("controller:课程添加异常!"+name,e);
			msg="课程添加出现异常";
			model.addAttribute("msg", msg);
			return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/coursemanage.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
		}
		logger.info("controller:课程添加结束!");
		return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/coursemanage.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
	}
	
	
	/**
	 * 课程修改
	 * @return
	 */
	@RequestMapping(value = "/courseEdit.do", method = RequestMethod.POST)
	public ResponseEntity<String> courseEdit(
			@RequestParam(value = "id", required = false, defaultValue = "0") Integer id,
			@RequestParam(value = "typeId", required = false, defaultValue = "0") Integer typeId,
			@RequestParam(value = "img", required = false, defaultValue = "") String img,
			@RequestParam(value = "info", required = false, defaultValue = "") String info,
			@RequestParam(value = "name", required = false, defaultValue = "") String name,
			@RequestParam(value = "title", required = false, defaultValue = "") String title,
			@RequestParam(value = "uid", required = false, defaultValue = "") Integer uid,
			HttpServletRequest request,HttpServletResponse response,
			Model model) {
		logger.info("controller:..课程修改!");
		String msg="";
		boolean isCorrect = true;
		HttpHeaders responseHeaders = new HttpHeaders();
		HttpSession session = request.getSession();
		responseHeaders.set("Content-Type", "text/html;charset=UTF-8");
		response.setContentType("text/html;charset=UTF-8");
			
		try{
			//获取课程信息
	    	LoginUser _loginUser = (LoginUser) session.getAttribute(SessionConstants.LOGIN_USER);
			if (null != _loginUser) {
				//get/new course
				MukeCourse course = courseService.findCourse(id);
				course.setImg(img);
				course.setInfo(info);
				course.setName(name);
				course.setTitle(title);
				course.setTypeId(typeId);
				course.setUid(_loginUser.getId());
				
				courseService.editCourse(course);
				msg="课程修改成功!";
			}else{
				msg="用户未登录!";
			}
		}catch(Exception e){
			logger.error("controller:课程修改异常!"+name,e);
			msg="课程修改出现异常";
			model.addAttribute("msg", msg);
			return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/coursemanage.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
			
		}
		logger.info("controller:课程修改结束!");
		return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/coursemanage.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
	}
	/**
	 * 课程删除
	 * @return
	 */
	@RequestMapping(value = "/courseDel.do", method = RequestMethod.POST)
	public ResponseEntity<String> courseDel(
			@RequestParam(value = "id", required = false, defaultValue = "0") Integer id,
			@RequestParam(value = "status", required = false, defaultValue = "9") Integer status,
			HttpServletRequest request,HttpServletResponse response,
			Model model) {
		logger.info("controller:..课程删除!");
		String msg="";
		boolean isCorrect = true;
		HttpHeaders responseHeaders = new HttpHeaders();
		HttpSession session = request.getSession();
		responseHeaders.set("Content-Type", "text/html;charset=UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		try{
			
			//获取课程信息
	    	LoginUser _loginUser = (LoginUser) session.getAttribute(SessionConstants.LOGIN_USER);
			if (null != _loginUser) {
				//get/new course
				MukeCourse course = courseService.findCourse(id);
				
				courseService.delCourse(course);
				msg="课程删除成功!";
			}else{
				msg="用户未登录!";
			}
			
//			SysUser sUser 
//			= userService.findSysUser(uid);
//			
//			// get|new role
//			SysRole role = roleService.findSysRole(suRoleId);
//			
//			sUser.setStatus(status);//默认失效
//			sUser.getRoleList().add(null);//添加关联关系
//			
//			//role.getUserList().add(sUser);//添加关联关系
//			
//			userService.editSysUser(sUser);//删除是逻辑删除，仅仅是失效，课程;
			
		}catch(Exception e){
			logger.error("controller:课程删除异常!"+id,e);
			msg="课程删除出现异常";
			model.addAttribute("msg", msg);
			return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/coursemanage.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
			
		}
		logger.info("controller:课程删除结束!");
		return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/coursemanage.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
	}
	
	
	
	/**
	 * 课程查看
	 * @return
	 */
	@RequestMapping(value = "/courseView.do", method = RequestMethod.GET)
	public ModelAndView courseView(
			@RequestParam(value = "id", required = false, defaultValue = "0") Integer id,
			@RequestParam(value = "status", required = false, defaultValue = "9") Integer status,
			HttpServletRequest request,HttpServletResponse response) {
		logger.info("controller:..课程查看!");
		ModelAndView model = new ModelAndView("/course/course_view");
		HttpSession session = request.getSession();
		try{
			//获取课程信息
	    	LoginUser _loginUser = (LoginUser) session.getAttribute(SessionConstants.LOGIN_USER);
			if (null != _loginUser) {
				//get/new course
				MukeCourse course = courseService.findCourse(id);
				
				SysAttachment sysAttachment = new SysAttachment();
				sysAttachment.setAid(course.getVideoId());//附件id
				sysAttachment.setType(Constants.ATTACH_TYPE_VIDEO);//视频类型
				SysAttachment attachment = uploadService.getFile(sysAttachment);
				model.addObject("attachment", attachment);
				model.addObject("course", course);
			}
		}catch(Exception e){
			logger.error("controller:课程查看异常!"+id,e);
		}
        return model;
	}
	
	/**
	 * 课程编辑页面
	 * @return
	 */
	@RequestMapping(value = "/toEditCourse.do", method = RequestMethod.POST)
	public ModelAndView toEditCourse(
			@RequestParam(value = "courseId", required = false, defaultValue = "") Integer courseId,
			@RequestParam(value = "parentId", required = false, defaultValue = "") Integer parentId,
			@RequestParam(value = "ownId", required = false, defaultValue = "") Integer ownId,
			HttpServletRequest request,HttpServletResponse response) {
		logger.info("controller:..课程编辑页面!");
		ModelAndView model = new ModelAndView("/course/course_edit");
		HttpSession session = request.getSession();
		try{
			//获取课程信息
	    	LoginUser _loginUser = (LoginUser) session.getAttribute(SessionConstants.LOGIN_USER);
			if (null != _loginUser) {
				//get/new course
				MukeCourseDTO course = courseService.findCourseDTO(courseId);
				if(null!= course.getVideoId() && course.getVideoId() > 0){
					SysAttachment sysAttachment = new SysAttachment();
					sysAttachment.setAid(course.getVideoId());//附件id
					sysAttachment.setType(Constants.ATTACH_TYPE_VIDEO);//视频类型
					SysAttachment attachment = uploadService.getFile(sysAttachment);
					model.addObject("attachment", attachment);
				}
				model.addObject("course", course);
				
			}
		}catch(Exception e){
			logger.error("controller:课程编辑页面异常!"+courseId,e);
		}
		model.addObject("parentId", parentId);
		model.addObject("ownId", ownId);
        return model;
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
