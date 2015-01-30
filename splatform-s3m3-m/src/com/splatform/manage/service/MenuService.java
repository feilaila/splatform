package com.splatform.manage.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.splatform.manage.dao.MenuMapper;
import com.splatform.manage.entity.SysMenu;

/**
 * 
 * 菜单服务类
 * 
 * @author
 * 
 */
@Service
public class MenuService extends BaseService {

	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(MenuService.class);

	@Autowired
	private MenuMapper menuMapper;

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	public List<SysMenu> findAllMenus() {
		return menuMapper.getAll();
	}

	/**
	 * 添加一个菜单
	 * @param menu
	 * @return
	 */
	public String addInfo(SysMenu menu) {
		if (menuMapper.insertSelective(menu) == 1) {
			return "添加成功";
		}
		return "添加失败";
	}
	
	/**
	 * @return
	 */
	public List<SysMenu> getAll() {
		return menuMapper.getAll();
	}
	/**
	 * 根据id删除
	 * @param id
	 * @return
	 */
	public String delete(String id) {
		if (menuMapper.deleteByPrimaryKey(id) == 1) {
			return "删除成功";
		}
		return "删除失败";
	}
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public SysMenu findById(String id) {
		return menuMapper.selectByPrimaryKey(id);
	}
	/**
	 * 更新menu
	 * @param menu
	 * @return
	 */
	public String update(SysMenu menu) {
		if (menuMapper.updateByPrimaryKeySelective(menu) == 1) {
			return "更新成功";
		}
		return "更新失败";
	}
}
