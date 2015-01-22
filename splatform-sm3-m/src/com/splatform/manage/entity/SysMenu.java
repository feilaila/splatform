package com.splatform.manage.entity;

import java.io.Serializable;

/**
 * @author 
 * 
 */

public class SysMenu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7488904616028381006L;

	private Integer id;

	private String menuName;
	
	private String menuCode;
	
	private int menuPid;
	
	private String menuUrl;
	
	private int leafYn;
	
	private String menuBtns;
	
	private String iconTag;
	
	private int hasChild;
	
	


//	/**
//	 * 菜单列表
//	 * 
//	 * */
//	@ManyToMany(mappedBy = "menuSet", fetch = FetchType.LAZY)
//	@OrderBy("id ASC")
//	private List<SysGroup> groupList;
//	
//	public List<SysGroup> getGroupList() {
//		return groupList;
//	}
//
//	public void setGroupList(List<SysGroup> groupList) {
//		this.groupList = groupList;
//	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public int getLeafYn() {
		return leafYn;
	}

	public void setLeafYn(int leafYn) {
		this.leafYn = leafYn;
	}

	public String getMenuBtns() {
		return menuBtns;
	}

	public void setMenuBtns(String menuBtns) {
		this.menuBtns = menuBtns;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getMenuPid() {
		return menuPid;
	}

	public void setMenuPid(int menuPid) {
		this.menuPid = menuPid;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	public String getIconTag() {
		return iconTag;
	}

	public void setIconTag(String iconTag) {
		this.iconTag = iconTag;
	}

	public int getHasChild() {
		return hasChild;
	}

	public void setHasChild(int hasChild) {
		this.hasChild = hasChild;
	}

}
