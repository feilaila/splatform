package com.splatform.manage.entity;

import java.io.Serializable;


/**
 * @author
 * 
 */

public class SysRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7488904616028381006L;


	private int id;

	private String roleName;

	private String remark;

	private int operateId;

	private String createTime;

//	/**
//	 * 角色列表
//	 * 
//	 * */
//	@ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST}, fetch = FetchType.EAGER,
//			mappedBy = "roleList")
//	@OrderBy("id ASC")
//	private List<SysUser> userList = new ArrayList<SysUser>();
//
////	@ManyToOne(cascade = CascadeType.ALL, optional = false)
////	//@JoinTable(name = "t_sys_group_role",joinColumns={@JoinColumn(name="role_id")},inverseJoinColumns = { @JoinColumn(name = "group_id") })
////	@JoinColumn(name = "id", referencedColumnName = "group_id")
//	// 外键为id，与t_sys_group_role中的group_id关联
//	@Column(name = "group_id",length=8)
//	private int groupId;
//	
//	
//	/**
//	 * 组的会员关系
//	 */
//	@OneToMany(mappedBy = "roleId",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	private Set<AppUser> aUsers = new HashSet<AppUser>();
//
//	
//	
//	/**
//	 * 角色操作多对多
//	 */
//	@ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST},fetch = FetchType.LAZY)
//	@JoinTable(name = "t_sys_role_operate", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = { @JoinColumn(name = "operate_id") })
//	@OrderBy("id ASC")
//	private Set<SysOperate> operateSet = new HashSet<SysOperate>();
//	
//	
//	public List<SysUser> getUserList() {
//		return userList;
//	}
//
//	public void setUserList(List<SysUser> userList) {
//		this.userList = userList;
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getOperateId() {
		return operateId;
	}

	public void setOperateId(int operateId) {
		this.operateId = operateId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

//	public int getGroupId() {
//		return groupId;
//	}
//
//	public void setGroupId(int groupId) {
//		this.groupId = groupId;
//	}
//
//	public Set<AppUser> getaUsers() {
//		return aUsers;
//	}
//
//	public void setaUsers(Set<AppUser> aUsers) {
//		this.aUsers = aUsers;
//	}
//
//	public Set<SysOperate> getOperateSet() {
//		return operateSet;
//	}
//
//	public void setOperateSet(Set<SysOperate> operateSet) {
//		this.operateSet = operateSet;
//	}
	
}
