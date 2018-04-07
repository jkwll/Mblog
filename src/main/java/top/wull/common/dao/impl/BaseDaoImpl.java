package top.wull.common.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.FlushMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import top.wull.common.dao.BaseDao;


public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

	private Class clazz;//用于接收运行期泛型类型
	
	public BaseDaoImpl() {
		//获得当前类型的带有泛型类型的父类
		ParameterizedType ptClass = (ParameterizedType) this.getClass().getGenericSuperclass();
		//获得运行期的泛型类型
		clazz = (Class) ptClass.getActualTypeArguments()[0];
	}
	@Resource(name="sessionFactory")
	public void setSF(SessionFactory sf){
		super.setSessionFactory(sf);
		System.out.println("---------------------------------------sessionFactory"+sf);
	}

	public void save(T t) {
		//getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		System.out.println("basedao 111   泛型对象：" + t+"  " + t.toString() );
		getHibernateTemplate().save(t);
	}

	public void delete(T t) {
		
		getHibernateTemplate().delete(t);
		
	}

	public void delete(Serializable id) {
		T t = this.getById(id);//先取,再删
		getHibernateTemplate().delete(t);
	}

	public void update(T t) {
		getHibernateTemplate().update(t);
	}

	@SuppressWarnings("unchecked")
	public T getById(Serializable id) {
		return (T) getHibernateTemplate().get(clazz, id);
	}

	public Integer getTotalCount(DetachedCriteria dc) {
		//设置查询的聚合函数,总记录数
		dc.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) getHibernateTemplate().findByCriteria(dc);
		//清空之前设置的聚合函数
		dc.setProjection(null);
		
		if(list!=null && list.size()>0){
			Long count = list.get(0);
			return (Integer)count.intValue();
		}else{
			return null;
		}
	}

	public List<T> getPageList(DetachedCriteria dc, Integer start, Integer pageSize) {
		
		List<T> list = (List<T>) getHibernateTemplate().findByCriteria(dc, start, pageSize);
		
		return list;
	}

	public List<T> getAll(Class<T> entityClazz) {
		// TODO Auto-generated method stub
		System.out.println("运行了获得所有 getall方法");
		return	getHibernateTemplate().loadAll(entityClazz);
		
	}

}
