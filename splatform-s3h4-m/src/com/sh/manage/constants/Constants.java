package com.sh.manage.constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Title. XX类<br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2014年11月26日
 * <p>
 * Company: ff
 * <p>
 * Author: fuzl
 * <p>
 * Version: 1.0
 * <p>
 */
public class Constants {
	// Field descriptor #6 Ljava/lang/String;
	public static final String CHARSET_UTF8 = "UTF-8";

	// Field descriptor #6 Ljava/lang/String;
	public static final String CHARSET_GBK = "GBK";

	// 按钮声明
	public static final String ADD_BTN = "add_btn";
	public static final String EDIT_BTN = "edit_btn";
	public static final String DEL_BTN = "del_btn";
	public static final String QUERY_BTN = "query_btn";

	// 超级管理员
	public static final String USER_NAME = "admin";
	/**
	 * 平台超级用户的ID定义 多个
	 */
	public final static List<String> SUPER_ADMIN_ID_LIST = new ArrayList<String>();

	/**
	 * 帐号锁定状态：0,正常;1,锁定
	 */
	public final static Integer USER_LOCK_NO = 0;

	public final static Integer USER_LOCK_YES = 1;

	/**
	 * 帐户状态：1有效，9无效
	 */
	public final static Integer USER_STATUS_VALID = 1;
	
	public final static Integer USER_STATUS_INVALID = 9;

	public static final Integer SYS_TYPE_0 = 0;
	
	/**
	 * 附件类型  1头像图片 2大图 3 文件  4  音乐  5  视频
	 */
	public final static int ATTACH_TYPE_FACEIMG = 1;
	public final static int ATTACH_TYPE_BIGIMG = 2;
	public final static int ATTACH_TYPE_FILE = 3;
	public final static int ATTACH_TYPE_MUSIC = 4;
	public final static int ATTACH_TYPE_VIDEO = 5;

	/**
	 * 课程状态
	 * 状态 0待审核  1已审核  2 已下线 ;默认为0
	 */
	public static final int COURSE_STATUS_INIT = 0;
	public static final int COURSE_STATUS_ONLINE = 1;
	public static final int COURSE_STATUS_OFFLINE = 2;

	/**
	 * 默认用户密码  abc123
	 */
	public static final String SYS_USER_PWD = "abc123";
	
	static {
		SUPER_ADMIN_ID_LIST.add("0");
		SUPER_ADMIN_ID_LIST.add("41369");
		SUPER_ADMIN_ID_LIST.add("41370");
		SUPER_ADMIN_ID_LIST.add("68084");
	}
}
