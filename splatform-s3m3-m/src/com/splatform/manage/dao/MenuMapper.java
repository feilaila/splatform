/**
 * 
 */
package com.splatform.manage.dao;

import java.util.List;

import com.splatform.manage.entity.SysMenu;

/**
 * @author fuzl
 *
 */
public interface MenuMapper {

	int deleteByPrimaryKey(String id);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);
    
    List<SysMenu> getAll();
}
