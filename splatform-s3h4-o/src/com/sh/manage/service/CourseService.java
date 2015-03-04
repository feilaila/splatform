/**
 * 
 */
package com.sh.manage.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.manage.dao.CourseDao;
import com.sh.manage.dao.SysUserDao;
import com.sh.manage.entity.MukeCourse;
import com.sh.manage.entity.MukeCourseType;
import com.sh.manage.exception.SPlatformServiceException;
import com.sh.manage.module.page.Page;
import com.sh.manage.pojo.MukeCourseDTO;

/**
 * @author
 * 
 */
@Service
public class CourseService extends BaseService {

	private Logger logger = Logger.getLogger(CourseService.class);
	
	@Autowired
	private CourseDao courseDao;

	@Autowired
	private SysUserDao sysUserDao;

	
	/**
	 * 查找课程
	 * @param course
	 */
	public MukeCourse findCourse(Integer id)throws SPlatformServiceException {
		try {
			List<MukeCourse> courseList = courseDao.findMukeCourse(id);
			//找到了课程
			if(null != courseList){
				return courseList.get(0);
			}
			//找不到课程
			return new MukeCourse();
		} catch (Exception e) {
			logger.error("service:查询课程信息出现异常", e);
			throw new SPlatformServiceException("查询课程信息出现异常");
		}
	}
	
	/**
	 * 查找课程其他信息
	 * @param course
	 */
	public MukeCourseDTO findCourseDTO(Integer id)throws SPlatformServiceException {
		try {
			List<MukeCourseDTO> courseList = courseDao.findMukeCourseDTO(id);
			//找到了课程
			if(null != courseList){
				return courseList.get(0);
			}
			//找不到课程
			return new MukeCourseDTO();
		} catch (Exception e) {
			logger.error("service:查询课程信息出现异常", e);
			throw new SPlatformServiceException("查询课程信息出现异常");
		}
	}
	
	/**
	 * 课程添加
	 * @param course
	 */
	public Integer addCourse(MukeCourse course) {
		return (Integer) courseDao.save(course);
	}
	/**
	 * 课程修改
	 * @param course
	 */
	public void editCourse(MukeCourse course) {
		courseDao.update(course);
	}
	/**
	 * 课程删除
	 * @param course
	 */
	public void delCourse(MukeCourse course) {
		courseDao.delete(course);
	}
	
	/**
	 * 课程管理页
	 * @param name
	 * @param replaceAll
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findAllMukeCourse(String name, String createTime,
			Integer pageNo, int pageSize) {
		return courseDao.getAllMukeCourse(name, createTime, pageNo, pageSize);
	}
	/**
	 * 所有课程类型
	 * @return
	 */
	public List<MukeCourseType> findAllCourseType() {
		return courseDao.getAllCourseType();
	}
}
