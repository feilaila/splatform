package com.splatform.manage.entity;

import java.io.Serializable;

/**
 * @author
 * 
 */

public class SysUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7488904616028381006L;


	private Long uid;

	/**
	 * 邮箱
	 * 
	 * */
	private String email;
	/**
	 * 姓名
	 * 
	 * */
	private String name;
	/**
	 * 用户名
	 * 
	 * */
	private String usercode;
	/**
	 * 密码
	 * 
	 * */
	private String password;
	/**
	 * 手机号
	 * 
	 * */
	private String terminalId;

	/**
	 * 创建时间
	 * 
	 * */
	private String createTime;
	/**
	 * 密码修改时间
	 * 
	 * */
	private String changePwdTime;
	/**
	 * 有效期
	 * 
	 * */
	private String validTime;
	/**
	 * 锁定状态 1 未锁定 2锁定
	 */
	private Integer lockStatus;
	/**
	 * 有效性 1 有效，9失效
	 */
	private Integer status;
	
	private String lastLoginIP;
	
	private String lastLoginTime;
	
//	/**
//	 * 角色名称
//	 */
//	private String roleName;
//	/**
//	 * 角色id
//	 */
//	private Integer roleId;
//	
//	
//	/**
//	 * 组织id
//	 */
//	private Integer groupId;
	
	
//	/**
//	 * 用户列表
//	 */
//	@ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST},fetch = FetchType.LAZY)
//	@JoinTable(name = "t_sys_user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
//	@OrderBy("id ASC")
//	private List<SysRole> roleList = new ArrayList<SysRole>();

//	public List<SysRole> getRoleList() {
//		return roleList;
//	}
//
//	public void setRoleList(List<SysRole> roleList) {
//		this.roleList = roleList;
//	}
	
	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getChangePwdTime() {
		return changePwdTime;
	}

	public void setChangePwdTime(String changePwdTime) {
		this.changePwdTime = changePwdTime;
	}

	public String getValidTime() {
		return validTime;
	}

	public void setValidTime(String validTime) {
		this.validTime = validTime;
	}

	public Integer getLockStatus() {
		return lockStatus;
	}

	public void setLockStatus(Integer lockStatus) {
		this.lockStatus = lockStatus;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getLastLoginIP() {
		return lastLoginIP;
	}

	public void setLastLoginIP(String lastLoginIP) {
		this.lastLoginIP = lastLoginIP;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	
	

//	public String getRoleName() {
//		return roleName;
//	}
//
//	public void setRoleName(String roleName) {
//		this.roleName = roleName;
//	}
//
//	public Integer getRoleId() {
//		return roleId;
//	}
//
//	public void setRoleId(Integer roleId) {
//		this.roleId = roleId;
//	}
//
//	public Integer getGroupId() {
//		return groupId;
//	}
//
//	public void setGroupId(Integer groupId) {
//		this.groupId = groupId;
//	}
	
	
}
