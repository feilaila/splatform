package com.splatform.manage.base.intercepter;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.splatform.manage.constants.Constants;
import com.splatform.manage.pojo.LoginUser;



/**
 * Title. <br>
 * Description.登陆认证通用拦截器
 * 	处理过滤未登录的管理平台操作
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
public class AuthenticateInterceptor implements Interceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 日志记录器
	 */
	private Logger logger = Logger.getLogger(AuthenticateInterceptor.class);
	
	/** 不做权限验证的地址列表。可以使前缀路径或是具体的url */
	private final Set<String> AUTHOR_IGNORE_URLS = new HashSet<String>();
	
	public void destroy() {		
	}

	public void init() {
//		AUTHOR_IGNORE_URLS.add("/");
//		AUTHOR_IGNORE_URLS.add("/js/");
//		AUTHOR_IGNORE_URLS.add("/css/");
//		AUTHOR_IGNORE_URLS.add("/images/");
//		AUTHOR_IGNORE_URLS.add("/unite_login.do");
		AUTHOR_IGNORE_URLS.add("/login.do");
		AUTHOR_IGNORE_URLS.add("/static/");
		AUTHOR_IGNORE_URLS.add("/test.do");
//		AUTHOR_IGNORE_URLS.add("/code.do");
//		AUTHOR_IGNORE_URLS.add("/index.jsp");
//		AUTHOR_IGNORE_URLS.add("/logout.do");
	}

	/**
	 * 处理拦截信息
	 */
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		//获取上下文
		ActionContext context = actionInvocation.getInvocationContext();
		HttpServletRequest req = ServletActionContext.getRequest();
		//获取session中的信息
		Map<String, Object> session = context.getSession();
		//获取登陆用户
		LoginUser loginUser = (LoginUser) session.get(Constants.SESS_LOGIN_USER);
		// 对未登录情况做判断
		if (!inSet(req, AUTHOR_IGNORE_URLS)) {
			//验证未通过的接口
			if(null != loginUser){
				return actionInvocation.invoke();//继续处理请求
			}else{
				return "unite_login";//跳转到统一登陆界面
			}
		}else{
			//验证通过的接口
			logger.info("验证通过..地址:"+req.getServletPath());
			return actionInvocation.invoke();//继续处理请求
			
		}
		
	}
	
	
	/**
	 * 从request请求中获取ip地址
	 * 
	 * @param request
	 *            页面请求
	 * @return
	 */
	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	/**
	 * 判断访问链接是否在set范围内
	 * 
	 * @param req
	 *            HttpServletRequest
	 * @param set
	 *            Set
	 * @return 是 True,否 False
	 */
	private boolean inSet(HttpServletRequest req, Set<String> set) {
		String url = req.getServletPath();
		url = req.getRequestURI();
		
		String s = url.substring(url.lastIndexOf("/"));
		//logger.info("url:"+s);
		if (set.contains(url) || set.contains(s)) {
			return true;
		}
		return false;
	}

}
