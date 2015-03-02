package com.sh.manage.controller;

import java.util.ArrayList;
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
import com.sh.manage.entity.AppUser;
import com.sh.manage.entity.SysAttachment;
import com.sh.manage.entity.SysGroup;
import com.sh.manage.entity.SysRole;
import com.sh.manage.entity.SysUser;
import com.sh.manage.module.page.Page;
import com.sh.manage.pojo.LoginUser;
import com.sh.manage.pojo.SysUserDTO;
import com.sh.manage.service.GroupService;
import com.sh.manage.service.RoleService;
import com.sh.manage.service.UploadService;
import com.sh.manage.service.UserService;
import com.sh.manage.utils.WebUtils;

/**
 * 用户管理控制
 * @author 
 */

@Controller
public class UserController {
	
	private Logger logger = Logger.getLogger(UserController.class);


	/**
	 * 用户会员管理service
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
	 * 跳转会员管理页面
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/aumanage")
	public ModelAndView appUserManagePage(
			@RequestParam(value = "parentId", required = false, defaultValue = "") Integer parentId,
			@RequestParam(value = "status", required = false, defaultValue = "") Integer status,
			@RequestParam(value = "groupId", required = false, defaultValue = "") Integer groupId,
			@RequestParam(value = "usercode", required = false, defaultValue = "") String usercode,
			@RequestParam(value = "startDate", required = false, defaultValue = "") String startDate,
			@RequestParam(value = "endDate", required = false, defaultValue = "") String endDate,
			@RequestParam(value = "pageNo", required = false, defaultValue = "") Integer pageNo) {
		// 获取会员以及等级
		if (null == pageNo) {
			pageNo = initPageNo;
		}
		//返回会员列表页
		ModelAndView model = new ModelAndView("/appuser/appuser_manage");
		model.addObject("status", status);
		model.addObject("groupId", groupId);
		model.addObject("usercode", usercode);
		model.addObject("startDate", startDate);
		model.addObject("endDate", endDate);
		//返回的page对象
		page = userService.findAllAppUser(groupId == null ? 0 : groupId,
				usercode, startDate.replaceAll("-", ""), endDate.replaceAll("-", ""), status == null ? 0 : status,
				pageNo, pageSize);
		// 会员列表
		List<AppUser> appUserList = (List<AppUser>) page.getList();

		// 会员组织
		List<SysGroup> groupList = groupService.findAll();
		// 翻页带参数
		if(null!=status && status>0){
			page.addParam("status",""+status);
		}
		if(null!=groupId && groupId>0){
			page.addParam("groupId",""+groupId);
		}
		if(null != usercode){
			page.addParam("usercode",""+usercode);
		}
		if(null != startDate){
			page.addParam("startDate",""+startDate);
		}
		if(null != endDate){
			page.addParam("endDate",""+endDate);
		}		
				
		model.addObject("pageSize", pageSize);
		model.addObject("page", page);
		model.addObject("appUserList", appUserList);
		model.addObject("groupList", groupList);
		model.addObject("parentId", parentId);
		return model;
	}

	
	/**
	 * 跳转会员新增页面
	 * @return
	 */
	@RequestMapping(value="/toAddAppUser.do")
    public ModelAndView appUserAddPage(HttpServletRequest req,
			HttpServletResponse resp) {
		ModelAndView model = new ModelAndView("/appuser/appuser_add");
		List<SysGroup> dbGroupList = groupService.getAllGroupList();
		model.addObject("groupList", dbGroupList);
        return model;
    }
	
	
	/**
	 * 当前组织下的会员添加
	 * @return
	 */
	@RequestMapping(value = "/auserAdd.do", method = RequestMethod.POST)
	public ResponseEntity<String> auserAdd(
			@RequestParam(value = "groupId", required = false, defaultValue = "0") Integer groupId,
			@RequestParam(value = "startDate", required = false, defaultValue = "") String startDate,
			@RequestParam(value = "usercode", required = false, defaultValue = "") String usercode,
			@RequestParam(value = "endDate", required = false, defaultValue = "") String endDate,
			@RequestParam(value = "password", required = false, defaultValue = "") String password,
			@RequestParam(value = "terminalId", required = false, defaultValue = "") String terminalId,
			@RequestParam(value = "email", required = false, defaultValue = "") String email,
			@RequestParam(value = "remark", required = false, defaultValue = "") String remark,
			@RequestParam(value = "name", required = false, defaultValue = "") String name,
			@RequestParam(value = "status", required = false, defaultValue = "0") Integer status,
			@RequestParam(value = "limitYear", required = false, defaultValue = "0") Integer limitYear,
			HttpServletRequest request,HttpServletResponse response,
			Model model) {
		logger.info("controller:..会员添加!");
		String msg="";
		boolean isCorrect = true;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", "text/html;charset=UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		//new appUser
		AppUser aUser = new AppUser();
		aUser.setEmail(email);
		aUser.setEndDate(endDate.replaceAll("-", ""));//时间格式化，去掉-
		aUser.setName(name);
		aUser.setPassword(password);
		aUser.setGroupId(groupId);
		aUser.setStartDate(startDate.replaceAll("-", ""));//时间格式化，去掉-
		aUser.setStatus(status);
		aUser.setUserName(usercode);
		aUser.setTerminalId(terminalId);
		aUser.setLimitYear(limitYear);
		aUser.setRemark(remark);
		
		try{
			userService.addAppUser(aUser);
			msg="会员添加成功!";
		}catch(Exception e){
			logger.error("controller:会员添加异常!"+usercode,e);
			msg="会员添加出现异常";
			model.addAttribute("msg", msg);
			return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/aumanage.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
			
		}
		logger.info("controller:会员添加结束!");
		return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/aumanage.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
	}
	
	
	/**
	 * 当前组织下的会员修改
	 * @return
	 */
	@RequestMapping(value = "/auserEdit.do", method = RequestMethod.POST)
	public ResponseEntity<String> auserEdit(
			@RequestParam(value = "groupId", required = false, defaultValue = "0") Integer groupId,
			@RequestParam(value = "startDate", required = false, defaultValue = "") String startDate,
			@RequestParam(value = "usercode", required = false, defaultValue = "") String usercode,
			@RequestParam(value = "endDate", required = false, defaultValue = "") String endDate,
			@RequestParam(value = "terminalId", required = false, defaultValue = "") String terminalId,
			@RequestParam(value = "email", required = false, defaultValue = "") String email,
			@RequestParam(value = "remark", required = false, defaultValue = "") String remark,
			@RequestParam(value = "name", required = false, defaultValue = "") String name,
			@RequestParam(value = "status", required = false, defaultValue = "0") Integer status,
			@RequestParam(value = "limitYear", required = false, defaultValue = "0") Integer limitYear,
			@RequestParam(value = "auid", required = false, defaultValue = "0") Integer auid,
			HttpServletRequest request,HttpServletResponse response,
			Model model) {
		logger.info("controller:..会员修改!");
		String msg="";
		boolean isCorrect = true;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", "text/html;charset=UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		try{
			//  get/new appUser
			AppUser aUser = userService.findAppUser(auid);
			
			aUser.setEmail(email);
			aUser.setEndDate(endDate);
			aUser.setName(name);
			aUser.setGroupId(groupId);
			aUser.setStartDate(startDate);
			aUser.setStatus(status);
			aUser.setUserName(usercode);
			aUser.setTerminalId(terminalId);
			aUser.setLimitYear(limitYear);
			aUser.setRemark(remark);
			
			userService.editAppUser(aUser);
			
			msg="会员修改成功!";
		}catch(Exception e){
			logger.error("controller:会员修改异常!"+usercode,e);
			msg="会员修改出现异常";
			model.addAttribute("msg", msg);
			return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/aumanage.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
			
		}
		logger.info("controller:会员修改结束!");
		return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/aumanage.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
	}

	
	
	
	/**
	 * 当前组织下的会员删除
	 * @return
	 */
	@RequestMapping(value = "/auserDel.do", method = RequestMethod.POST)
	public ResponseEntity<String> auserDel(
			@RequestParam(value = "auid", required = false, defaultValue = "0") Integer auid,
			HttpServletRequest request,HttpServletResponse response,
			Model model) {
		logger.info("controller:..会员删除!");
		String msg="";
		boolean isCorrect = true;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", "text/html;charset=UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		
		try{
		//  get/new appUser
			AppUser aUser = userService.findAppUser(auid);
			
			if(null != aUser){
				userService.delAppUser(aUser);
				msg="会员删除成功!";
			}
		}catch(Exception e){
			logger.error("controller:会员删除异常!"+auid,e);
			msg="会员删除出现异常";
			model.addAttribute("msg", msg);
			return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/aumanage.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
		}
		logger.info("controller:会员删除结束!");
		return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/aumanage.do")+"'</script>",responseHeaders, HttpStatus.CREATED);
	}

	
	
	/*****************************************************/
	/**********功能分割线*************/
	/*****************************************************/
	
	/**
	 * 跳转系统用户管理页面
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/umanage.do")
	public ModelAndView sysUserManagePage(HttpServletRequest req,
			HttpServletResponse resp,
			@RequestParam(value = "parentId", required = false, defaultValue = "") Integer parentId,
			@RequestParam(value = "ownId", required = false, defaultValue = "") Integer ownId,
			@RequestParam(value = "usercode", required = false, defaultValue = "") String usercode,
			@RequestParam(value = "startDate", required = false, defaultValue = "") String startDate,
			@RequestParam(value = "endDate", required = false, defaultValue = "") String endDate,
			@RequestParam(value = "pageNo", required = false, defaultValue = "") Integer pageNo) {
		HttpSession session = req.getSession();
		// 获取用户以及等级
		if (null == pageNo) {
			pageNo = initPageNo;
		}
		Integer ownUid = 0;
		//获取用户信息
    	LoginUser _loginUser = (LoginUser) session.getAttribute(SessionConstants.LOGIN_USER);
		if (null != _loginUser) {
			//获取登录用户id
			ownUid = _loginUser.getId();
		}
		// 返回会员列表页
		ModelAndView model = new ModelAndView("/system/sysuser_manage");
		model.addObject("usercode", usercode);
		model.addObject("startDate", startDate);
		model.addObject("endDate", endDate);
		// 返回的page对象
		page = userService.findAllSysUser(
				usercode, startDate.replaceAll("-", ""),
				endDate.replaceAll("-", ""),
				pageNo, pageSize);
		// 会员列表
		List<SysUser> sysUserList = (List<SysUser>) page.getList();

		// 会员等级
		//List<SysRole> roleList = roleService.findSysUserRole(4);
		// 翻页带参数
		if(null != usercode){
			page.addParam("usercode",usercode);
		}
		if(null != startDate){
			page.addParam("startDate",startDate);
		}
		if(null != endDate){
			page.addParam("endDate",endDate);
		}
		
		model.addObject("pageSize", pageSize);
		model.addObject("page", page);
		model.addObject("parentId", parentId);
		model.addObject("ownId", ownId);//当前菜单id
		model.addObject("ownUid", ownUid);//当前登录用户id
		model.addObject("sysUserList", sysUserList);
		//model.addObject("roleList", roleList);
		return model;
	}

	
	
	/**
	 * 跳转用户添加页面
	 */
	@RequestMapping(value="/toAddSysUser.do")
    public ModelAndView userAddPage(HttpServletRequest req,
			HttpServletResponse resp,
			@RequestParam(value = "parentId", required = false, defaultValue = "") Integer parentId) {
		HttpSession session = req.getSession();
		ModelAndView model = new ModelAndView("/system/sysuser_add");
		
		//获取用户信息
    	LoginUser _loginUser = (LoginUser) session.getAttribute(SessionConstants.LOGIN_USER);
		if (null != _loginUser) {
			// 组织列表
			List<SysGroup> groupList = groupService.findAll();
			model.addObject("groupList", groupList);
			model.addObject("parentId", parentId);
			logger.info("groupList.size:"+groupList.size());
		}
        return model;
    }
	
	
	/**
	 * 用户添加
	 * @return
	 */
	@RequestMapping(value = "/doAddsuser.do", method = RequestMethod.POST)
	public ResponseEntity<String> suserAdd(
			@RequestParam(value = "parentId", required = false, defaultValue = "") Integer parentId,
			@RequestParam(value = "suGroupId", required = false, defaultValue = "0") Integer suGroupId,
			@RequestParam(value = "roleName", required = false, defaultValue = "") String roleName,
			@RequestParam(value = "startDate", required = false, defaultValue = "") String startDate,
			@RequestParam(value = "usercode", required = false, defaultValue = "") String usercode,
			@RequestParam(value = "endDate", required = false, defaultValue = "") String endDate,
			@RequestParam(value = "password", required = false, defaultValue = "") String password,
			@RequestParam(value = "terminalId", required = false, defaultValue = "") String terminalId,
			@RequestParam(value = "email", required = false, defaultValue = "") String email,
			@RequestParam(value = "name", required = false, defaultValue = "") String name,
			@RequestParam(value = "status", required = false, defaultValue = "1") Integer status,
			HttpServletRequest request,HttpServletResponse response,
			Model model) {
		logger.info("controller:..用户添加!");
		String msg="";
		boolean isCorrect = true;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", "text/html;charset=UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		try{
			//new sysUser
			SysUser sUser = new SysUser();
			
			// get|new role
			//SysGroup group = groupService.findSysGroup(suGroupId);
			
			sUser.setEmail(email);
			sUser.setValidTime(endDate.replaceAll("-", ""));//时间格式化，去掉-
			sUser.setName(name);
			sUser.setPassword(password);
			sUser.setCreateTime(startDate.replaceAll("-", ""));//时间格式化，去掉-
			sUser.setStatus(status);//默认有效
			sUser.setLockStatus(Constants.USER_LOCK_NO);//默认为未锁定 0
			sUser.setUsercode(usercode);
			sUser.setTerminalId(terminalId);
			sUser.setGroupId(suGroupId);
			
//			sUser.getRoleList().add(role);//添加关联关系
//			
//			role.getUserList().add(sUser);//添加关联关系
			
			int result = userService.addSysUser(sUser);
			if(result > 0){
				msg="用户添加成功!";
			}else{
				msg="用户添加失败!";
			}
		}catch(Exception e){
			logger.error("controller:用户添加异常!"+usercode,e);
			msg="用户添加出现异常";
			model.addAttribute("msg", msg);
			return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/umanage.do?parentId="+parentId)+"'</script>",responseHeaders, HttpStatus.CREATED);
		}
		logger.info("controller:用户添加结束!");
		return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/umanage.do?parentId="+parentId)+"'</script>",responseHeaders, HttpStatus.CREATED);
	}
	
	
	
	/**
	 * 跳转用户修改页面
	 * @return
	 */
	@RequestMapping(value = "/toEditSysUser.do", method = RequestMethod.GET)
	public ModelAndView toEditSysUserPage(
			@RequestParam(value = "parentId", required = false, defaultValue = "") Integer parentId,
			@RequestParam(value = "uid", required = false, defaultValue = "") Integer uid,
			HttpServletRequest request,HttpServletResponse response) {
		HttpSession session = request.getSession();
		ModelAndView model = new ModelAndView("/system/sysuser_edit");
		
		//获取用户信息
    	LoginUser _loginUser = (LoginUser) session.getAttribute(SessionConstants.LOGIN_USER);
		if (null != _loginUser) {
			try {
				// get/new sysUser
				SysUser sUser = userService.findSysUser(uid);
				// 组织列表
				List<SysGroup> groupList = groupService.findAll();
				model.addObject("groupList", groupList);
				model.addObject("parentId", parentId);
				model.addObject("sysUser", sUser);
			} catch (Exception e) {
				logger.error("跳转用户修改页面error...");
			}
		}else{
			logger.info("用户未登录");
		}
		return model;
	}
	
	
	/**
	 * 用户修改
	 * @return
	 */
	@RequestMapping(value = "/doEditSysUser.do", method = RequestMethod.POST)
	public ResponseEntity<String> suserEdit(
			@RequestParam(value = "parentId", required = false, defaultValue = "") Integer parentId,
			@RequestParam(value = "uid", required = false, defaultValue = "0") Integer uid,
			@RequestParam(value = "suGroupId", required = false, defaultValue = "0") Integer suGroupId,
			@RequestParam(value = "roleName", required = false, defaultValue = "") String roleName,
			@RequestParam(value = "startDate", required = false, defaultValue = "") String startDate,
			@RequestParam(value = "usercode", required = false, defaultValue = "") String usercode,
			@RequestParam(value = "endDate", required = false, defaultValue = "") String endDate,
			@RequestParam(value = "terminalId", required = false, defaultValue = "") String terminalId,
			@RequestParam(value = "email", required = false, defaultValue = "") String email,
			@RequestParam(value = "name", required = false, defaultValue = "") String name,
			@RequestParam(value = "status", required = false, defaultValue = "1") Integer status,
			HttpServletRequest request,HttpServletResponse response,
			Model model) {
		logger.info("controller:..用户修改!");
		String msg="";
		boolean isCorrect = true;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", "text/html;charset=UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		
		
		try{
			// get/new sysUser
			SysUser sUser = userService.findSysUser(uid);
			
			// get|new role
//			SysRole role = roleService.findSysRole(suRoleId);
			
			sUser.setEmail(email);
			sUser.setValidTime(endDate.replaceAll("-", ""));//时间格式化，去掉-
			sUser.setName(name);
			sUser.setCreateTime(startDate.replaceAll("-", ""));//时间格式化，去掉-
			sUser.setStatus(status);//默认有效
			sUser.setUsercode(usercode);			
			sUser.setTerminalId(terminalId);
			sUser.setGroupId(suGroupId);
			
			//更新，先删后增关系
//			List<SysRole> roleList = sUser.getRoleList();
//			List<SysRole> delRoleList = new ArrayList<SysRole>();
//			//List<SysRole> addRoleList = new ArrayList<SysRole>();
//			for(SysRole r:roleList){
//				if(r.getId()!=suRoleId){
//					delRoleList.add(r);//for 循环等同于iterator
//				}
//			}
//			sUser.getRoleList().removeAll(delRoleList);//删除不需要的关系
//			sUser.getRoleList().add(role);//添加新增的关联关系,可以批量插入关系
//			role.getUserList().add(sUser);//添加关联关系

			userService.editSysUser(sUser);
			msg="用户修改成功!";
		}catch(Exception e){
			logger.error("controller:用户修改异常!"+usercode,e);
			msg="用户修改出现异常";
			model.addAttribute("msg", msg);
			return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/umanage.do?parentId="+parentId)+"'</script>",responseHeaders, HttpStatus.CREATED);
			
		}
		logger.info("controller:用户修改结束!");
		return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/umanage.do?parentId="+parentId)+"'</script>",responseHeaders, HttpStatus.CREATED);
	}
	
	/**
	 * 用户删除
	 * @return
	 */
	@RequestMapping(value = "/doDelSysUser.do", method = RequestMethod.POST)
	public ResponseEntity<String> suserDel(
			@RequestParam(value = "parentId", required = false, defaultValue = "") Integer parentId,
			@RequestParam(value = "uid", required = false, defaultValue = "0") Integer uid,
			@RequestParam(value = "suRoleId", required = false, defaultValue = "0") Integer suRoleId,
			@RequestParam(value = "status", required = false, defaultValue = "9") Integer status,
			HttpServletRequest request,HttpServletResponse response,
			Model model) {
		logger.info("controller:..用户删除!");
		String msg="";
		boolean isCorrect = true;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", "text/html;charset=UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		try{
			SysUser sUser 
			= userService.findSysUser(uid);
			
			// get|new role
			@SuppressWarnings("unused")
			SysRole role = roleService.findSysRole(suRoleId);
			
			sUser.setStatus(status);//默认失效
			sUser.getRoleList().add(null);//添加关联关系
			
			//role.getUserList().add(sUser);//添加关联关系
			
			userService.editSysUser(sUser);//删除是逻辑删除，仅仅是失效，用户;
			msg="用户删除成功!";
		}catch(Exception e){
			logger.error("controller:用户删除异常!"+uid,e);
			msg="用户删除出现异常";
			model.addAttribute("msg", msg);
			return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/umanage.do?parentId="+parentId)+"'</script>",responseHeaders, HttpStatus.CREATED);
			
		}
		logger.info("controller:用户删除结束!");
		return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/umanage.do?parentId="+parentId)+"'</script>",responseHeaders, HttpStatus.CREATED);
	}
	
	/**
	 * 用户密码初始化
	 * @return
	 */
	@RequestMapping(value = "/doInitPwd.do", method = RequestMethod.POST)
	public ResponseEntity<String> doInitPwd(
			@RequestParam(value = "parentId", required = false, defaultValue = "") Integer parentId,
			@RequestParam(value = "uid", required = false, defaultValue = "") Integer uid,
			HttpServletRequest request,HttpServletResponse response,
			Model model) {
		logger.info("controller:..用户密码初始化!");
		String msg="";
		boolean isCorrect = true;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", "text/html;charset=UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		try{
			//get/new user
			SysUser sUser  = userService.findSysUser(uid);
			
			sUser.setPassword(Constants.SYS_USER_PWD);//默认密码
			
			userService.editSysUser(sUser);//密码初始化
			msg="用户密码初始化成功!";
		}catch(Exception e){
			logger.error("controller:用户密码初始化异常!"+uid,e);
			msg="用户密码初始化出现异常";
			model.addAttribute("msg", msg);
			return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/umanage.do?parentId="+parentId)+"'</script>",responseHeaders, HttpStatus.CREATED);
			
		}
		logger.info("controller:用户密码初始化结束!");
		return new ResponseEntity<String>("<script>parent.callBack('msgdiv','" + msg + "'," + isCorrect + ");parent.close(); parent.location.href='" + WebUtils.formatURI(request, "/umanage.do?parentId="+parentId)+"'</script>",responseHeaders, HttpStatus.CREATED);
	}
	
	
	/**
	 * 用户查看
	 * @return
	 */
	@RequestMapping(value = "/suserView.do", method = RequestMethod.GET)
	public ModelAndView suserView(
			@RequestParam(value = "parentId", required = false, defaultValue = "") Integer parentId,
			@RequestParam(value = "uid", required = false, defaultValue = "0") Integer uid,
			@RequestParam(value = "ownUid", required = false, defaultValue = "0") Integer ownUid,
			@RequestParam(value = "suRoleId", required = false, defaultValue = "0") Integer suRoleId,
			@RequestParam(value = "status", required = false, defaultValue = "9") Integer status,
			HttpServletRequest request,HttpServletResponse response) {
		logger.info("controller:..用户查看!");
		ModelAndView model = new ModelAndView("/system/sysuser_view");
		HttpSession session = request.getSession();
		try{
			//获取用户信息
	    	LoginUser _loginUser = (LoginUser) session.getAttribute(SessionConstants.LOGIN_USER);
			if (null != _loginUser) {
				SysUserDTO sUser = userService.findSysUserDTO(uid);
				SysAttachment sysAttachment = new SysAttachment();
				sysAttachment.setAid(sUser.getFaceimgAid());//附件id
				sysAttachment.setType(Constants.ATTACH_TYPE_FACEIMG);//头像类型
				SysAttachment attachment = uploadService.getFile(sysAttachment);
				model.addObject("attachment", attachment);
				model.addObject("sysUser", sUser);
				model.addObject("parentId", parentId);
				model.addObject("ownUid", ownUid);
			}
		}catch(Exception e){
			logger.error("controller:用户查看异常!"+uid,e);
		}
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
