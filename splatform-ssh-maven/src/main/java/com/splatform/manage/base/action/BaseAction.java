package com.splatform.manage.base.action;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


/**
 * Title. <br>
 * Description.基础action尽量都要继承此action
 * <p>
 * Copyright: Copyright (c) 2014-2-21
 * <p>
 * Company:
 * <p>
 * Author:
 * <p>
 * Version: 1.0
 * <p>
 */
public class BaseAction extends ActionSupport implements ServletContextAware,
		ServletResponseAware, ServletRequestAware, SessionAware {

	/**
	 * 日志记录
	 */
	public Logger logger = Logger.getLogger(getClass());
	/**
	 * 
	 */
	private static final long serialVersionUID = -4828238878062224699L;
	// 全局上下文
	protected ActionContext act = ActionContext.getContext();
	
	protected String path;
	/**
	 * 获取request
	 */
	protected HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * 获取response
	 */
	protected HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * 获取ServletContext
	 */
	protected ServletContext getServletContext() {
		return ServletActionContext.getServletContext();
	}

	/**
	 * 获取Attribute
	 */
	protected Object getAttribute(String attributeName) {
		return ServletActionContext.getRequest().getAttribute(attributeName);
	}

	/**
	 * 设置Attribute
	 */
	protected void setAttribute(String attributeName, Object obj) {
		ServletActionContext.getRequest().setAttribute(attributeName, obj);
	}

	/**
	 * 获取Parameter
	 */
	protected String getParameter(String name) {
		return ServletActionContext.getRequest().getParameter(name);
	}

	/**
	 * 获取Parameter数组
	 */
	protected String[] getParameterArray(String name) {
		return ServletActionContext.getRequest().getParameterValues(name);
	}

	/**
	 * 获取session
	 * 
	 * @param name
	 * @return
	 */

	protected Object getSession(String name) {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		return session.get(name);
	}

	/**
	 * 设置Session
	 * 
	 * @param name
	 * @param value
	 */
	@SuppressWarnings("unchecked")
	protected void setSession(String name, Object value) {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		session.put(name, value);
	}

	/**
	 * 移除Session
	 * 
	 * @param name
	 */
	protected void removeSession(String name) {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		session.remove(name);
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public String getPath() {
		return this.getServletContext().getContextPath();
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public void setSession(Map arg0) {
		// TODO Auto-generated method stub
	}
	
}
