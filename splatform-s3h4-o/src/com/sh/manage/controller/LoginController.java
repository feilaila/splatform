package com.sh.manage.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sh.manage.constants.Constants;
import com.sh.manage.constants.SessionConstants;
import com.sh.manage.entity.SysAttachment;
import com.sh.manage.entity.SysMenu;
import com.sh.manage.entity.SysUser;
import com.sh.manage.module.page.ZTreeNode;
import com.sh.manage.pojo.LoginUser;
import com.sh.manage.security.MD5;
import com.sh.manage.service.LoginService;
import com.sh.manage.service.MenuService;
import com.sh.manage.service.SystemService;
import com.sh.manage.service.UploadService;
import com.sh.manage.service.UserService;
import com.sh.manage.utils.IPUtil;
import com.sh.manage.utils.JsonUtils;
import com.sh.manage.utils.ResponseUtils;
import com.sh.manage.utils.SafeUtil;
import com.sh.manage.utils.TimeUtil;

/**
 * 后台登陆管理
 * 
 * @author
 * 
 */
@Controller
@RequestMapping("unite")
public class LoginController {
	
	
	private Logger logger = Logger.getLogger(LoginController.class);

	@Autowired
	private SystemService systemService;

	/**
	 * 用户会员管理service
	 */
	@Autowired
	private UserService userService;
	/**
	 * 注入menuService包装
	 */
	@Autowired
	private MenuService menuService;

	/**
	 * 注入loginService包装
	 * @return
	 */
	@Autowired
	private LoginService loginService;
	
	/**
	 * 上传管理service
	 */
	@Autowired
	private UploadService uploadService;
	
	
	/**
	 * 所有菜单数据串
	 * 格式：{ id:2, pId:0, name:"随意勾选 2", checked:true, open:true},
	 */
	private String menuStrs = "";
	

	// 跳转登陆页
	@RequestMapping(value = "/tologin.do")
	public ModelAndView loginPage() {
		return new ModelAndView("/main/login");
	}

	// 跳转首页
	@RequestMapping(value = "/index.do")
	public ModelAndView mainPage(HttpServletRequest req,
			HttpServletResponse resp) {
		
		HttpSession session = req.getSession();
		ModelAndView model = new ModelAndView("/main/index");
		
		//获取缓存中用户的数据
		LoginUser _loginUser = (LoginUser) session.getAttribute(SessionConstants.LOGIN_USER);
		List<SysMenu> userMenuList = _loginUser.getMenuList();
		
		
		/**缓存中用户的数据*/
		List<ZTreeNode> _nodeList = _loginUser.getNodeList();
		model.addObject("nodeList", _nodeList);
		
//		String allDtree = menuService.getAllDTree((LoginUser)session.getAttribute(SessionConstants.LOGIN_USER));
//		
//		model.addObject("allDtree",allDtree);
		
		//ZTree节点集合
		List<ZTreeNode> nodes = loginService.generUserZtreeNode(userMenuList);
		
		JSONArray jsonArr = JSONArray.fromObject(nodes);
		model.addObject("ztreeNodes",jsonArr);
		//设置缓存
		session.setAttribute("ztreeNodes", jsonArr);
		
		//取得头像
    	SysAttachment sysAttachment = new SysAttachment();
		SysAttachment attachment = null;
		if(null !=_loginUser.getFaceimgAid() && _loginUser.getFaceimgAid() > 0){
			sysAttachment.setAid(_loginUser.getFaceimgAid());//附件id
			sysAttachment.setType(Constants.ATTACH_TYPE_FACEIMG);//头像类型
			attachment = uploadService.getFile(sysAttachment);
			model.addObject("attachment",attachment);
			session.removeAttribute("faceimgpath");
			session.setAttribute("faceimgpath", attachment.getFilepath());
		}
		
		logger.info(jsonArr);
		return model;
	}

	/**
	 * 执行登陆请求
	 * 
	 * @param req
	 * @param resp
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/user_login.do", method = RequestMethod.POST)
	public @ResponseBody String doLogin(HttpServletRequest request,
			HttpServletResponse resp,
			@RequestParam("usercode") String usercode,
			@RequestParam("password") String password,
			@RequestParam("rand") String rand) {
		HttpSession session = request.getSession();
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", "text/html;charset=UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		String msg = "";
		try {
			do{
				// 增加基础输入数据合法校验。
				int errCode = SafeUtil.instance().vlidate(request);
				if (errCode != 0) {
					msg = "13";
					break;
				}
				// 登录密码检验
				if (StringUtils.isBlank(password)) {
					msg = "3";
					break;
				}

				// 验证码检查
				if (StringUtils.isBlank(rand)) {
					msg = "4";
					break;
				}

				if (!rand.equalsIgnoreCase((String) request.getSession().getAttribute("rand"))) {
					msg = "5";
					break;
				}
				
				// 根据帐户名获得用户对象
				SysUser sysUser = new SysUser();
				sysUser.setUsercode(usercode);
				sysUser.setPassword(password);
				
				
				
				SysUser loginUser = systemService.getUserInfoByUsername(sysUser);
				
				
				// 用户名校验
				if (loginUser == null) {
					msg = "6";
					break;
				}

				// 错误登录次数验证,但是超级用户不受此干扰。
				if(!Constants.USER_NAME.equals(usercode)){
					
//					int errTimes = loginService.logonErrTimes(user.getUserCode());
//					if (errTimes >= 5) {
//						loginService.lockUser(userCode);
//						msg = "15";
//						break;
//					}
//					
				}
				

				// 密码校验
				if (!MD5.digest2Str(password).equals(loginUser.getPassword())) {
					// 记录错误日志
//					loginService.logErr(user.getUserCode(), request.getRemoteHost());
	//
//					msg = "7";
//					break;
				}

				// 校验是否锁定
				if (Constants.USER_LOCK_NO != loginUser.getLockStatus()) {
					msg = "8";
					break;
				}

				// 校验帐户是否有效
				if (Constants.USER_STATUS_VALID != loginUser.getStatus()) {
					msg = "9";
					break;
				}

				// 检查用户是否在有效期之内 validTime
//				if (!"".equals(loginUser.getValidTime())) {
//					int result = TimeUtil.nowDate().compareTo(loginUser.getValidTime());
//					if (result > 0) {
//						msg = "11";
//						break;
//					}
//				}
				
				
				//记录登陆时间和登陆IP
				loginUser.setLastLoginIP(IPUtil.getIpAddr(request));
				loginUser.setLastLoginTime(TimeUtil.now());
				//更新
				userService.editSysUser(loginUser);
				
				
				//获取用户权限信息
				LoginUser _loginUser = new LoginUser();
				_loginUser.setId(loginUser.getUid());
				_loginUser.setUserCode(loginUser.getUsercode());
				_loginUser.setUserPwd(loginUser.getPassword());
				_loginUser.setName(loginUser.getName());
				_loginUser.setFaceimgAid(loginUser.getFaceimgAid());
				
				//权限菜单列表
				_loginUser.setMenuList(loginService.getMenuList(_loginUser.getId()));
				//权限操作列表
				_loginUser.setSoperList(loginService.getSoperList(_loginUser.getId()));
				
				
				//代替nodeList
				 List<ZTreeNode> treeNodeList = loginService.getNodeList(_loginUser);
				_loginUser.setNodeList(treeNodeList);
				
				/**
				 * 所有菜单节点  加入缓存
				 */
				List<SysMenu> menuList = (List<SysMenu>) loginService.getAllMenuList();
				//所有菜单数据串 格式：{ id:2, pId:0, name:"随意勾选 2", checked:true, open:true},
				List<String> items = new ArrayList<String>();
				String _temp = "{ id:'0', pId:'-1', name:"+"'全选'"+",iconOpen:'"+request.getContextPath()+"/static/js/ztree/zTreeStyle/img/diy/1_open.png'"+", iconClose:'"+request.getContextPath()+"/static/js/ztree/zTreeStyle/img/diy/1_close.png'"+",open:true}";
				items.add(_temp);
		    	for(SysMenu menu:menuList){
		    		_temp = "{id:'"+menu.getId()+"',pId:'"+menu.getMenuPid()+"',name:'"+
		    		menu.getMenuName()+"'"+",icon:'"+request.getContextPath()+"/static/js/ztree/zTreeStyle/img/diy/2.png'";
		    		if(menu.getHasChild() == 1){
		    			//存在子菜单默认打开
		    			_temp+=",icon:'"+request.getContextPath()+"/static/js/ztree/zTreeStyle/img/diy/4.png'"+",open:true";
		    		}
		    		_temp +="}";
		    		items.add(_temp);
		    	}
				menuStrs = JsonUtils.toJson(items);
				menuStrs = menuStrs.replaceAll("\"", "");
		    	logger.info("menuStrs:"+menuStrs.toString());
		    		
		    	
				
				// 默认登陆人员为Admin
				session.setAttribute(SessionConstants.LOGIN_USER, _loginUser);
				session.setAttribute("name", _loginUser.getName());
				session.setAttribute("uid", _loginUser.getId());
				session.setAttribute("usercode", usercode);
				
				session.setAttribute("menuStrs", menuStrs);
				session.setAttribute("menuList", menuList);
				session.setAttribute("treeNodeList", treeNodeList);
				msg = "0";//都校验通过，跳转主页
			}while(false);
		} catch (Exception e) {
			logger.error("登陆出现错误..."+e.getMessage());
		}
		
		return ResponseUtils.newJsonOKResp("成功！", msg);
	}

	
	
	/**
	 * 执行登出请求
	 * 
	 * @param req
	 * @param resp
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/user_logout.do", method = RequestMethod.GET)
	public ModelAndView doLogout(HttpServletRequest req,
			HttpServletResponse resp) {
		HttpSession session = req.getSession();

		/* 判断session是否存在登陆信息 */
		if (null != session.getAttribute(SessionConstants.LOGIN_USER)) {
			session.invalidate();
			//SysUser loginUser = (SysUser) session.getAttribute(SessionConstants.LOGIN_USER);
			
			return new ModelAndView("/main/login");
		} else {
			// 登陆成功跳转主页
			return new ModelAndView("/main/index");
		}
	}
	


	/**
	 * 执行App登陆请求
	 * 
	 * @param req
	 * @param resp
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/app_login", method = RequestMethod.POST)
	public @ResponseBody String doAppLogin(HttpServletRequest request,
			HttpServletResponse resp,
			@RequestParam("usercode") String usercode,
			@RequestParam("password") String password,
			@RequestParam("rand") String rand) {
		
		logger.info("Controller... doAppLogin");
		HttpSession session = request.getSession();
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", "text/html;charset=UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		String msg = "";
		
		do{
			// 增加基础输入数据合法校验。
			// 登录密码检验
			if (StringUtils.isBlank(password)) {
				msg = "3";
				break;
			}

			// 验证码检查
			if (StringUtils.isBlank(rand)) {
				msg = "4";
				break;
			}

			// 根据帐户名获得用户对象
			SysUser sysUser = new SysUser();
			sysUser.setUsercode(usercode);
			sysUser.setPassword(password);

			SysUser loginUser = systemService.getUserInfoByUsername(sysUser);

			
			// 用户名校验
			if (loginUser == null) {
				msg = "6";
				break;
			}
			
			// 校验是否锁定
			if (Constants.USER_LOCK_NO != loginUser.getLockStatus()) {
				msg = "8";
				break;
			}

			// 校验帐户是否有效
			if (Constants.USER_STATUS_VALID != loginUser.getStatus()) {
				msg = "9";
				break;
			}
			
			//获取用户权限信息
			LoginUser _loginUser = new LoginUser();
			_loginUser.setId(loginUser.getUid());
			_loginUser.setUserCode(loginUser.getUsercode());
			_loginUser.setUserPwd(loginUser.getPassword());
			_loginUser.setName(loginUser.getName());
			
			//权限菜单列表
			_loginUser.setMenuList(loginService.getMenuList(_loginUser.getId()));
			//权限操作列表
			_loginUser.setSoperList(loginService.getSoperList(_loginUser.getId()));
			
			
			//代替nodeList
			_loginUser.setNodeList(loginService.getNodeList(_loginUser));
			
			//String allDtree = menuService.getAllDTree((SysUser)session.getAttribute(SessionConstants.LOGIN_USER));

			
			// 默认登陆人员为Admin
			session.setAttribute(SessionConstants.LOGIN_USER, _loginUser);
			session.setAttribute("usercode", usercode);
			
			msg = "0";//都校验通过，跳转主页
		}while(false);
		
		return ResponseUtils.newJsonOKResp("成功！", msg);
	}
}
