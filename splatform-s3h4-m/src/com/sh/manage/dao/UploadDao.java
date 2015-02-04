package com.sh.manage.dao;

import java.util.List;


import org.springframework.stereotype.Repository;

import com.sh.manage.entity.SysAttachment;

/**
 * @author 
 *
 */
@Repository
public class UploadDao extends AbstractBaseDao<SysAttachment>{

	@Override
	public Integer addObject(SysAttachment clazz) {
		return (Integer) this.getCurrentSession().save(clazz);
	}

	@Override
	public void updateObject(SysAttachment clazz) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteObject(SysAttachment clazz) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SysAttachment getObject(SysAttachment clazz) {
		return this.get("from SysAttachment s where s.aid = ? and s.type= ? ", new Object[]{clazz.getAid(),clazz.getType()});
	}

	/**
	 * 查找系统用户
	 * @param uid
	 * @return
	 */
	public List<SysAttachment> findSysUser(Integer aid,Integer uid,Integer type) {
		String hql = "from SysAttachment where aid = ";
		hql += aid;
		return this.find(hql);
	}

	

}
