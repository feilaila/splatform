/**
 * 
 */
package com.splatform.manage.dao;

import java.util.List;

import com.splatform.manage.entity.SysUser;

/**
 * @author fuzl
 * 
 */
public interface UserMapper {

	int deleteByPrimaryKey(String id);

	int insert(SysUser record);

	int insertSelective(SysUser record);

	SysUser selectByPrimaryKey(String id);
	SysUser selectByUserInfo(String userCode);

	int updateByPrimaryKeySelective(SysUser record);

	int updateByPrimaryKey(SysUser record);

	List<SysUser> getAll();
}
