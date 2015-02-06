package com.sh.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Title. 邮件控制类<br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2014年11月30日
 * <p>
 * Company: ff
 * <p>
 * Author: fuzl
 * <p>
 * Version: 1.0
 * <p>
 */
@Controller
public class MailController {
	
	// 跳转邮件管理页
	@RequestMapping(value = "/mailmanage.do")
	public ModelAndView mailManagePage(
			@RequestParam(value = "parentId", required = false, defaultValue = "") Integer parentId,
    		@RequestParam(value = "ownId", required = false, defaultValue = "") Integer ownId) {
		ModelAndView model = new ModelAndView("/mail/mail_manage");
		model.addObject("parentId", parentId);
		model.addObject("ownId", ownId);
		return model;
	}
}
