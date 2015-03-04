package com.sh.manage.dao;

import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.sh.manage.entity.MukeCourse;
import com.sh.manage.entity.MukeCourseType;
import com.sh.manage.module.page.Page;
import com.sh.manage.pojo.MukeCourseDTO;

/**
 * @author 
 *
 */
@Repository
public class CourseDao extends AbstractBaseDao<MukeCourse>{

	@Override
	public Integer addObject(MukeCourse clazz) {
		// TODO Auto-generated method stub
		return (Integer) this.getCurrentSession().save(clazz);
	}

	@Override
	public void updateObject(MukeCourse clazz) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteObject(MukeCourse clazz) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MukeCourse getObject(MukeCourse clazz) {
		return this.get("from MukeCourse s where s.id = ? ", new Object[]{clazz.getId()});
	}

	/**
	 * 根据id查找课程
	 * @param uid
	 * @return
	 */
	public List<MukeCourse> findMukeCourse(Integer id) {
		String hql = "from MukeCourse where id = ";
		hql += id;
		return this.find(hql);
	}

	/**
	 * 获取全部课程
	 * @param username
	 * @param startDate
	 * @param endDate
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page getAllMukeCourse(String name, String createTime,Integer pageNo, int pageSize) {
		StringBuffer sbf = new StringBuffer();
		sbf.append("select rt.* from (select s.id,s.create_time createTime,s.img,s.info,s.title,s.name,s.type_id typeId,s.sys_user_id uid,s.video_id videoId,s.status,u.name userName,t.name cTypeName from muke_course s join t_sys_user u on s.sys_user_id=u.uid left join muke_course_type t on s.type_id = t.id ");
		sbf.append(" where 1 = 1 ");//有效的用户and s.status = 1
		
//		sbf.append("select rt.* from (select s.*,u.*,t.* from muke_course s join t_sys_user u on s.sys_user_id=u.uid left join muke_course_type t on s.type_id = t.id ");
//		sbf.append(" where 1 = 1 ");//有效的用户and s.status = 1
		Object[] params = new Object[]{};
		
		if(!StringUtils.isEmpty(name)){
			params = ArrayUtils.add(params, name);
			sbf.append(" and s.name like '%"+name+"%'");
		}
		if(!StringUtils.isEmpty(createTime)){
			params = ArrayUtils.add(params, createTime);
			sbf.append(" and s.create_time >= ?");
		}

		sbf.append(") as rt");
		return this.queryModelDTOListByPage(sbf.toString(), params, pageNo, pageSize, MukeCourseDTO.class);
	}

	/**
	 * 所有课程类型
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<MukeCourseType> getAllCourseType() {
		String sql = "select id,name,number,remark,img from muke_course_type ";
		Object[] params = new Object[]{};
		return (List<MukeCourseType>) this.queryModelSqlList(sql,params,MukeCourseType.class);
	}

	/**
	 * 查询课程其他信息
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<MukeCourseDTO> findMukeCourseDTO(Integer id) {
		StringBuffer sbf = new StringBuffer();
		sbf.append("select rt.* from (select s.id,s.create_time createTime,s.img,s.info,s.title,s.name,s.type_id typeId,s.sys_user_id uid,s.video_id videoId,s.status,u.name userName,t.name cTypeName from muke_course s join t_sys_user u on s.sys_user_id=u.uid left join muke_course_type t on s.type_id = t.id ");
		sbf.append(" where 1 = 1 ");//有效的用户and s.status = 1
		
//		sbf.append("select rt.* from (select s.*,u.*,t.* from muke_course s join t_sys_user u on s.sys_user_id=u.uid left join muke_course_type t on s.type_id = t.id ");
//		sbf.append(" where 1 = 1 ");//有效的用户and s.status = 1
		Object[] params = new Object[]{};
		
		if(id > 0){
			params = ArrayUtils.add(params, id);
			sbf.append(" and s.id = ? ");
		}
	

		sbf.append(") as rt");
		return (List<MukeCourseDTO>) this.querysqlDTOList(sbf.toString(), params, MukeCourseDTO.class);
	}
}
