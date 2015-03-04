/**
 * 
 */
package com.sh.manage.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.manage.dao.UploadDao;
import com.sh.manage.entity.SysAttachment;

/**
 * @author
 * 
 */
@Service
public class UploadService extends BaseService {

	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(UploadService.class);

	@Autowired
	private UploadDao uploadDao;
	
	/**
	 * 添加文件
	 * @param sysAttachment
	 * @return
	 */
	public Integer addFile(SysAttachment sysAttachment) {
		return uploadDao.addObject(sysAttachment);
	}
	
	/**
	 * 获取附件
	 * @param sysAttachment
	 * @return
	 */
	public SysAttachment getFile(SysAttachment sysAttachment){
		return uploadDao.getObject(sysAttachment);
	}

}
