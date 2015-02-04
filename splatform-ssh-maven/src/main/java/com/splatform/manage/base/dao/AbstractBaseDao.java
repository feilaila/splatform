/**
 * 
 */
package com.splatform.manage.base.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.splatform.manage.base.exception.SPlatformDaoException;
import com.splatform.manage.module.page.Page;


/**
 * @author dao抽象基础类 已经实现了一些基础功能
 * 
 */
@Repository
public abstract class AbstractBaseDao<T> extends HibernateDaoSupport {

	// hibernate3使用
	protected SessionFactory sessionFacotry;
	/**
	 * - 【注入 SessionFactory】 - 注入一个sessionFactory属性,并注入到父类(HibernateDaoSupport
	 * )里
	 * **/
	// 注入一个bean, 默认(name = "sessionFactory"), 因此只写@Resource
	@Autowired
	public void setSessionFacotry(SessionFactory sessionFacotry) {
		super.setSessionFactory(sessionFacotry);
		this.sessionFacotry = sessionFacotry;
	}

	// 获取当前session
	protected Session getCurrentSession() {
		return sessionFacotry.openSession();// 还是可以得到session
	}

	
	//###############################
	//#################################
	// hibernate 4 使用 不用继承HibernateDaoSupport,也没有HibernateDaoSupport
	// @Autowired
	// protected SessionFactory sessionFactory;

	// protected Session getCurrentSession() {
	// return sessionFactory.getCurrentSession();// 还是可以得到session
	// }

	
	
	@Autowired
	protected JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public abstract Long addObject(T clazz);

	public abstract void updateObject(T clazz);

	public abstract void deleteObject(T clazz);

	public abstract T getObject(T clazz);

	public Serializable save(T o) {
		Session session = this.getCurrentSession();
		Serializable result = session.save(o);
		session.flush();
		this.releaseSession(session);//关闭连接
		return result;
	}

	/**
	 * 删除
	 * 
	 * @param o
	 */
	public void delete(T o) {
		Session session = this.getCurrentSession();
		session.delete(o);
		session.flush();
		this.closeSession(session);//关闭连接
	}

	/**
	 * 更新
	 * 
	 * @param o
	 */
	public void update(T o) {
		Session session = this.getCurrentSession();
		session.update(o);
		session.flush();
		this.closeSession(session);//关闭连接
	}

	/**
	 * 保存或者更新
	 * 
	 * @param o
	 */
	public void saveOrUpdate(T o) {
		Session session = this.getCurrentSession();
		session.saveOrUpdate(o);
		session.flush();
		this.closeSession(session);//关闭连接
	}

	/**
	 * 执行hql返回list
	 * 
	 * @param hql
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> find(String hql) {
		Session session = this.getCurrentSession();
		List<T> tList = session.createQuery(hql).list();	
		this.closeSession(session);//关闭连接
		return tList;
	}

	/**
	 * 查询集合
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> find(String hql, Object[] param) {
		Session session = this.getCurrentSession();
		Query q = session.createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		List<T> tList = q.list();		
		this.closeSession(session);//关闭连接
		return tList;
	}

	/**
	 * 查询集合
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> find(String hql, List<Object> param) {
		Session session = this.getCurrentSession();
		Query q = session.createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		List<T> tList = q.list();		
		this.closeSession(session);//关闭连接
		return tList;
	}

	/**
	 * 查询集合(带分页)
	 * 
	 * @param hql
	 * @param param
	 * @param page
	 *            查询第几页
	 * @param rows
	 *            每页显示几条记录
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> find(String hql, Object[] param, Integer page, Integer rows) {
		if (page == null || page < 1) {
			page = 1;
		}
		if (rows == null || rows < 1) {
			rows = 10;
		}
		Session session = this.getCurrentSession();
		Query q = session.createQuery(hql);

		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		 
		
		List<T> tList = q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
		this.closeSession(session);//关闭连接
		return tList;
	}

	/**
	 * 查询集合(带分页)
	 * 
	 * @param hql
	 * @param param
	 * @param page
	 * @param rows
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> find(String hql, List<Object> param, Integer page,
			Integer rows) {
		if (page == null || page < 1) {
			page = 1;
		}
		if (rows == null || rows < 1) {
			rows = 10;
		}
		Session session = this.getCurrentSession();
		Query q = session.createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		List<T> tList = q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
		this.closeSession(session);//关闭连接
		return tList;
	}

	/**
	 * 获得一个对象
	 * 
	 * @param c
	 *            对象类型
	 * @param id
	 * @return Object
	 */
	@SuppressWarnings("unchecked")
	public T get(Class<T> c, Serializable id) {
		Session session = this.getCurrentSession();
		T t = (T) session.get(c, id);
		this.closeSession(session);//关闭连接
		return t;
	}

	public T get(String hql, Object[] param) {
		Session session = this.getCurrentSession();
		List<T> l = this.find(hql, param);
		if (l != null && l.size() > 0) {
			this.closeSession(session);//关闭连接
			return l.get(0);
		} else {
			this.closeSession(session);//关闭连接
			return null;
		}
	}

	public T get(String hql, List<Object> param) {
		Session session = this.getCurrentSession();
		List<T> l = this.find(hql, param);
		if (l != null && l.size() > 0) {
			this.closeSession(session);//关闭连接
			return l.get(0);
		} else {
			this.closeSession(session);//关闭连接
			return null;
		}
	}

	public Long count(String hql) {
		Session session = this.getCurrentSession();
		Long result = (Long) session.createQuery(hql).uniqueResult();
		this.closeSession(session);//关闭连接
		return result;
	}

	public Long count(String hql, Object[] param) {
		Session session = this.getCurrentSession();
		Query q = session.createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		Long result = (Long) q.uniqueResult();
		this.closeSession(session);//关闭连接
		return result;
	}

	public Long count(String hql, List<Object> param) {
		Session session = this.getCurrentSession();
		Query q = session.createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		
		Long result = (Long) q.uniqueResult();
		this.closeSession(session);//关闭连接
		return result;
	}

	
	public Integer executeHql(String hql) {
		Session session = this.getCurrentSession();
		Integer result = session.createQuery(hql).executeUpdate();
		this.closeSession(session);//关闭连接
		return result;
	}

	public Integer executeHql(String hql, Object[] param) {
		Session session = this.getCurrentSession();
		Query q = session.createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		Integer result = q.executeUpdate();
		this.closeSession(session);//关闭连接
		return result;
	}

	public Integer executeHql(String hql, List<Object> param) {
		Session session = this.getCurrentSession();
		Query q = session.createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		Integer result = q.executeUpdate();
		this.closeSession(session);//关闭连接
		return result;
	}

	/**
	 * sql查询 分页查询列表
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> querySqlList(final String sqlStr,
			final Object[] paras, final int pageNo, final int pageSize)
			throws SPlatformDaoException {
		Session session = this.getCurrentSession();
		List<Object[]> objList = new ArrayList<Object[]>();

		Query query = session.createSQLQuery(sqlStr);

		if (null != paras && paras.length > 0) {
			int count = 0;
			for (Object para : paras) {
				query.setParameter(count++, para);
			}
		}
		query.setFirstResult((pageNo - 1) * pageSize);
		query.setMaxResults(pageSize);
		objList = query.list();
		this.closeSession(session);//关闭连接
		return objList;
	}

	/**
	 * 根据条件查询，返回结果数量
	 */
	public int getCount(String sqlStr, Object[] paras)
			throws SPlatformDaoException {
		Session session = this.getCurrentSession();
		try {
			Long count;
			sqlStr = "select count(*) "
					+ sqlStr.substring(sqlStr.indexOf("from"));

			Query query = session.createQuery(sqlStr);
			int i = 0;
			if (null != paras && paras.length > 0) {
				for (Object para : paras) {
					query.setParameter(i++, para);
				}
			}
			count = Long.parseLong(query.uniqueResult().toString());
			return Integer.parseInt(count.toString());
		} catch (Exception e) {
			throw new SPlatformDaoException("通过条件获得总数量失败", e);
		} finally{
			this.closeSession(session);//关闭连接
		}
	}

	/**
	 * 查询数据的列表
	 */
	@SuppressWarnings("unchecked")
	public List<T> queryList(final String sqlStr, final Object[] paras)
			throws SPlatformDaoException {
		Session session = this.getCurrentSession();
		List<T> objList = new ArrayList<T>();
		int count = 0;
		Query query = session.createSQLQuery(sqlStr);
		if (null != paras && paras.length > 0) {
			for (Object para : paras) {
				query.setParameter(count++, para);
			}
		}
		objList = query.list();
		this.closeSession(session);//关闭连接
		return objList;
	}

	/**
	 * 查询数据的列表
	 */
	@SuppressWarnings("unchecked")
	public List<Object> querysqlList(final String sqlStr, final Object[] paras)
			throws SPlatformDaoException {
		Session session = this.getCurrentSession();
		List<T> objList = new ArrayList<T>();
		int count = 0;
		Query query = session.createSQLQuery(sqlStr);
		if (null != paras && paras.length > 0) {
			for (Object para : paras) {
				query.setParameter(count++, para);
			}
		}
		objList = query.list();
		this.closeSession(session);//关闭连接
		return (List<Object>) objList;
	}

	// /**
	// * 分页查询列表
	// */
	// @SuppressWarnings("unchecked")
	// public List<Object[]> querySqlList(final String sqlStr, final Object[]
	// paras, final int pageNo, final int pageSize) throws DAOException {
	// List<T> objList = new ArrayList<>();
	//
	// int count = 0;
	//
	// Query query = session.createSQLQuery(sqlStr);
	//
	// if (null != paras && paras.length > 0) {
	// for (Object para : paras) {
	// query.setParameter(count++, para);
	// }
	// }
	// query.setFirstResult((pageNo - 1) * pageSize);
	// query.setMaxResults(pageSize);
	// objList = query.list();
	// return objList;
	// }

	/**
	 * 查询数据的列表
	 */
	@SuppressWarnings("unchecked")
	public List<T> queryhqlList(final String sqlStr, final Object[] paras)
			throws SPlatformDaoException {
		Session session = this.getCurrentSession();
		List<T> objList = new ArrayList<T>();
		int count = 0;
		Query query = session.createQuery(sqlStr);
		if (null != paras && paras.length > 0) {
			for (Object para : paras) {
				query.setParameter(count++, para);
			}
		}
		//启用二级缓存
		query.setCacheable(true);//必须设置
		objList = query.list();
		this.closeSession(session);//关闭连接
		return objList;
	}

	/**
	 * 分页查询列表
	 */
	@SuppressWarnings("unchecked")
	public Page queryList(final String sqlStr, final Object[] paras,
			final int pageNo, final int pageSize) throws SPlatformDaoException {
		List<T> objList = new ArrayList<T>();
		int count = 0;
		Session session = this.getCurrentSession();
		Query query = session.createQuery(sqlStr);
		if (null != paras && paras.length > 0) {
			for (Object para : paras) {
				query.setParameter(count++, para);
			}
		}
		query.setFirstResult((pageNo - 1) * pageSize);
		query.setMaxResults(pageSize);
		//启用二级缓存
		query.setCacheable(true);//必须设置
		objList = query.list();
		int resCount = this.getCount(sqlStr, paras);
		Page page = new Page();
		page.setPageSize(pageSize);
		page.search(resCount);
		page.turnToPage(pageNo);
		page.setList(objList);
		this.closeSession(session);//关闭连接
		return page;
	}

	/**
	 * 分页查询纯Sql
	 */

	@SuppressWarnings("unchecked")
	public Page querySqlListByPage(final String sqlStr, final Object[] paras,
			final int pageNo, final int pageSize) {
		List<T> objList = new ArrayList<T>();
		int count = 0;
		Session session = this.getCurrentSession();
		Query query = session.createSQLQuery(sqlStr);
		if (null != paras && paras.length > 0) {
			for (Object para : paras) {
				query.setParameter(count++, para);
			}
		}
		query.setFirstResult((pageNo - 1) * pageSize);
		query.setMaxResults(pageSize);
		//启用二级缓存
		query.setCacheable(true);//必须设置
		objList = query.list();
		int resCount = this.getCountBySql(sqlStr, paras);
		Page page = new Page();
		page.setPageSize(pageSize);
		page.search(resCount);
		page.turnToPage(pageNo);
		page.setList(objList);
		this.closeSession(session);//关闭连接
		return page;
	}

	/**
	 * 分页查询纯Sql
	 * 
	 * @param sqlStr
	 *            sql查询语句
	 * @param paras
	 *            sql语句中需要传递的参数
	 * @return
	 * @throws
	 * @history add by fuzl
	 */
	@SuppressWarnings("unchecked")
	public Page queryModelListByPage(final String sqlStr, final Object[] paras,
			final int pageNo, final int pageSize,
			@SuppressWarnings("rawtypes") final Class clazz) {
		List<T> objList = new ArrayList<T>();
		int count = 0;
		Session session = this.getCurrentSession();
		Query query = session.createSQLQuery(sqlStr)
				.addEntity(clazz);

		if (null != paras && paras.length > 0) {
			for (Object para : paras) {
				query.setParameter(count++, para);
			}
		}
		query.setFirstResult((pageNo - 1) * pageSize);
		query.setMaxResults(pageSize);
		//启用二级缓存
		query.setCacheable(true);//必须设置 
		objList = query.list();

		int resCount = this.getCountBySql(sqlStr, paras);
		Page page = new Page();
		page.setPageSize(pageSize);
		page.search(resCount);
		page.turnToPage(pageNo);
		page.setList(objList);
		this.closeSession(session);//关闭连接
		return page;
	}
	
	/**
	 * 分页查询列表
	 */
	@SuppressWarnings("unchecked")
	public List<T> queryForList(final String sqlStr, final Object[] paras,
			final int pageNo, final int pageSize) throws SPlatformDaoException {
		List<T> objList = new ArrayList<T>();
		int count = 0;
		Session session = this.getCurrentSession();
		Query query = session.createQuery(sqlStr);
		if (null != paras && paras.length > 0) {
			for (Object para : paras) {
				query.setParameter(count++, para);
			}
		}
		query.setFirstResult((pageNo - 1) * pageSize);
		query.setMaxResults(pageSize);
		//启用二级缓存
		query.setCacheable(true);//必须设置
		objList = query.list();
		this.closeSession(session);//关闭连接
		return objList;
	}

	/**
	 * 查询配置文件中的sql语句
	 */

	public String querySql(final String sqlName) throws SPlatformDaoException {
		String result = "";
		Session session = this.getCurrentSession();
		result = session.getNamedQuery(sqlName)
				.getQueryString();
		this.closeSession(session);//关闭连接
		return result;
	}

	/**
	 * 执行sql命令
	 */
	public int executeSqlQuery(final String sqlStr, final Object[] paras)
			throws SPlatformDaoException {
		int result = 0;
		int count = 0;
		Session session = this.getCurrentSession();
		Query query = session.createSQLQuery(sqlStr);
		if (null != paras && paras.length > 0) {
			for (Object para : paras) {
				query.setParameter(count++, para);
			}
		}
		result = query.executeUpdate();
		this.closeSession(session);//关闭连接
		return result;
	}

	/**
	 * 根据查询语句返回从结果数
	 */
	public int getCountBySql(String sqlStr, Object[] paras) {
		Long count;
		Session session = this.getCurrentSession();
		sqlStr = "select count(*) from (" + sqlStr + ")";  //oracle 不需要别名
		 //sqlStr = "select count(*) from (" + sqlStr + ") as cnt";// mysql需要加上别名
		Query query = session.createSQLQuery(sqlStr);
		int i = 0;
		if (null != paras && paras.length > 0) {
			for (Object para : paras) {
				query.setParameter(i++, para);
			}
		}
		//启用二级缓存
		//query.setCacheable(true);//必须设置
		//java.lang.ClassCastException: java.math.BigDecimal cannot be cast to [Ljava.lang.Object;
		
		count = Long.parseLong(query.uniqueResult().toString());
		this.closeSession(session);//关闭连接
		return Integer.parseInt(count.toString());
	}

	/**
	 * 查询某个Model的数据的列表
	 * 
	 * @param sqlStr
	 *            sql查询语句
	 * @param paras
	 *            sql语句中需要传递的参数
	 * @return
	 * @throws DataAccessException
	 * @history add by fuzl
	 */
	@SuppressWarnings("rawtypes")
	protected List<?> queryModelSqlList(final String sqlStr,
			final Object[] paras, final Class clazz) throws DataAccessException {
		List<?> objList = new ArrayList<T>();
		int count = 0;
		Session session = this.getCurrentSession();
		Query query = session.createSQLQuery(sqlStr).addEntity(clazz);
		if (null != paras && paras.length > 0) {
			for (Object para : paras) {
				query.setParameter(count++, para);
			}
		}
		objList = query.list();
		this.closeSession(session);//关闭连接
		return objList;
	}
	
	
	
	/**
	 * 关闭session
	 * @param session
	 */
	public void closeSession(Session session) {  
        if (session != null) {  
        	logger.debug("Closing Hibernate Session");  
            try {  
                session.close();  
            }  
            catch (HibernateException ex) {  
            	logger.debug("Could not close Hibernate Session", ex);  
            }  
            catch (Throwable ex) {  
            	logger.debug("Unexpected exception on closing Hibernate Session", ex);  
            }  
        }  
    }
	
	/**
	 * 获取当前会话
	 */
//	protected Session session = this.getCurrentSession();

	
}