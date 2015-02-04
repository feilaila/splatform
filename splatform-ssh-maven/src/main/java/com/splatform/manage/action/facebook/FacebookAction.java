package com.splatform.manage.action.facebook;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.splatform.manage.base.action.BaseAction;

/**
 * <p>descrption: </p>
 * 
 * @author fuzl
 * @date   2015年2月3日
 * @Copyright 2015 Snail Soft, Inc. All rights reserved.
 */
@Controller
public class FacebookAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5494132188826655295L;

	@Action(value = "/doAddRole", results = {
			@Result(name = "error", type = "redirectAction", location = "${url}"),
			//@Result(name = "SUCCESS", type = "redirectAction", location = "${url}"),
			//@Result(name = "SUCCESS", type = "stringType", params = {"stringName","result"})
			@Result(name = SUCCESS, location = "/WEB-INF/centralmanage/central_role_add.jsp") 
	})
	public String callback(){
		
		return null;
	}
	
	
	
	@Action(value = "/test", 
			results = {
			@Result(name = SUCCESS, location = "/WEB-INF/index.jsp")})
	public String test(){
		System.out.println("test:"+this.getRequest().getParameter("type"));
		return SUCCESS;
	}
}
