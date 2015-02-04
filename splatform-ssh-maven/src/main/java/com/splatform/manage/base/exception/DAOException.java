package com.splatform.manage.base.exception;

/**
 * 
 * Title. <br>
 * Description. 统一处理数据访问异常
 * <p>
 * Copyright: Copyright (c) 2014-2-19
 * <p>
 * Company:
 * <p>
 * Author: 
 * <p>
 * Version: 1.0
 * <p>
 */
public class DAOException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DAOException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DAOException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public DAOException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public DAOException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	
}
