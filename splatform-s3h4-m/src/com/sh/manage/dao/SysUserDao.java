/**
 * 
 */
package com.sh.manage.dao;

import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.sh.manage.entity.SysMenu;
import com.sh.manage.entity.SysUser;
import com.sh.manage.module.page.Page;
import com.sh.manage.pojo.LoginUser;
import com.sh.manage.pojo.SysUserDTO;

/**
 * @author 
 *
 */
@Repository
public class SysUserDao extends AbstractBaseDao<SysUser>{

	@Override
	public Integer addObject(SysUser clazz) {
		// TODO Auto-generated method stub
		return (Integer) this.getCurrentSession().save(clazz);
	}

	@Override
	public void updateObject(SysUser clazz) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteObject(SysUser clazz) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SysUser getObject(SysUser clazz) {
		return this.get("from SysUser s where s.usercode = ? and s.password = ?", new Object[]{clazz.getUsercode(),clazz.getPassword()});
	}

	/**
	 * 查找系统用户
	 * @param uid
	 * @return
	 */
	public List<SysUser> findSysUser(Integer uid) {
		String hql = "from SysUser where uid = " + uid;
		return this.find(hql);
	}
	
	/**
	 * sql查询用户和关联信息
	 */
	@SuppressWarnings("unchecked")
	public List<SysUserDTO> findSysUserDTO(Integer uid) {
		StringBuffer sbf = new StringBuffer();
		Object[] params = new Object[]{};
		sbf.append("select u.uid,u.usercode,u.email,u.terminal_id terminalId,u.name,u.create_time createTime,g.group_name groupName,u.faceimg_aid faceimgAid from t_sys_user u,t_sys_group g where 1=1 and u.group_id = g.id and uid = ?");
		params = ArrayUtils.add(params, uid);
		return (List<SysUserDTO>) this.querysqlDTOList(sbf.toString(),params,SysUserDTO.class);
	}
	

	/**
	 * 获取全部系统用户
	 * @param username
	 * @param startDate
	 * @param endDate
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page getAllSysUser(String usercode, String startDate,
			String endDate, Integer pageNo, int pageSize) {
		StringBuffer sbf = new StringBuffer();
		sbf.append("select rt.* from (select s.uid,s.email,s.name,s.usercode,s.password,s.terminal_id,s.valid_time,s.create_time,s.change_pwd_time,s.status,s.lock_status,s.last_login_time,s.last_login_ip,s.group_id,s.faceimg_aid,g.group_name groupName from t_sys_user s left join t_sys_group g on s.group_id = g.id ");
		sbf.append(" where 1 = 1 ");//有效的用户and s.status = 1
		Object[] params = new Object[]{};
		
		if(!StringUtils.isEmpty(usercode)){
			//params = ArrayUtils.add(params, username);
			sbf.append(" and s.usercode like '%"+usercode+"%'");
		}
		if(!StringUtils.isEmpty(startDate)){
			params = ArrayUtils.add(params, startDate);
			sbf.append(" and s.create_time >= ?");
		}
		if(!StringUtils.isEmpty(endDate)){
			params = ArrayUtils.add(params, endDate);
			sbf.append(" and s.valid_time <= ?");
		}

		sbf.append(") as rt");
		return this.queryModelListByPage(sbf.toString(), params, pageNo, pageSize, SysUser.class);
	}
	/**
	 * 
	 * @param loginUser
	 * @return
	 */
	public List<SysMenu> getUserMenu(LoginUser loginUser) {
		return null;
	}

	

}
