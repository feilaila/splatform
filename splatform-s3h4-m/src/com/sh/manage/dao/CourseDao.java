package com.sh.manage.dao;

import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.sh.manage.entity.MukeCourse;
import com.sh.manage.entity.MukeCourseType;
import com.sh.manage.entity.SysUser;
import com.sh.manage.module.page.Page;
import com.sh.manage.pojo.MukeCoursePojo;

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
		sbf.append("select rt.* from (select s.id,s.create_time,s.img,s.info,s.title,s.name,s.type_id,s.sys_user_id,s.video_id,s.status,u.name userName from muke_course s join t_sys_user u on s.sys_user_id=u.uid left join muke_course_type t on s.type_id = t.id ");
		sbf.append(" where 1 = 1 ");//有效的用户and s.status = 1
		
		sbf.append("select rt.* from (select {course.*},{user.*},{type.*} from muke_course {course} join t_sys_user {user} on {course}.sys_user_id={user}.uid left join muke_course_type {type} on {course}.type_id = {type}.id ");
		sbf.append(" where 1 = 1 ");//有效的用户and s.status = 1
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
		return this.queryMoreModelListByPage(sbf.toString(), params, pageNo, pageSize, MukeCourse.class,SysUser.class,MukeCourseType.class);
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
}
