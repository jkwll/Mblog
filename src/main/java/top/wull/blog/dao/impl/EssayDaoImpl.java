package top.wull.blog.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import top.wull.blog.antity.Essay;
import top.wull.blog.antity.EssayType;
import top.wull.blog.dao.EssayDao;
import top.wull.common.dao.impl.BaseDaoImpl;

@Repository("essayDao")
@SuppressWarnings("unchecked")
public class EssayDaoImpl extends BaseDaoImpl<Essay> implements EssayDao {
	
/*	public void update(String picsrc){
		Essay essay = getHibernateTemplate().get(Essay.class, 1);
		Integer n  = essay.getCount();
		essay.setCount(++n);
		getHibernateTemplate().update(essay);  
	}
*/	

	//通过url，将此url对应的essay行conunt添加一
	public void updateEssayCountByURL(String url) {
		// TODO Auto-generated method stub
/*		Criteria criteria = getHibernateTemplate().getSessionFactory().
				openSession().createCriteria(Essay.class);		
		criteria.add(Restrictions.eq("url",url));
		Essay essay = (Essay) criteria.list().get(0);
		Integer n = essay.getCount();
		essay.setCount(++n);
		update(essay);
*/
/*		Transaction trans=getHibernateTemplate().getSessionFactory().getCurrentSession().beginTransaction();
		String hql = "update Essay essay set essay.count=essay.count+'1' where essay.url='"+url+"'";	
		Query query =getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		int i = query.executeUpdate();
		System.out.println("i=="+i);
		trans.commit();
*/
		}

	public List<Essay> getMaxCountEssay() {
		/*
		 * 运行这段代码，连续10几次就会出问题
		 * 
		 * hql待写
		 * SELECT * FROM essay ORDER BY count desc
		String hql = "FROM Essay";
		Query query =getHibernateTemplate().getSessionFactory().openSession().createQuery(hql);
		List<Essay> elist =query.list();
		for (Essay essay : elist) {
			System.out.println("essay---------" + essay.toString());
		}
		System.out.println("essay---------132");*/
		  	//封装离线查询对象
			//getPageBean默认是倒序的
			Session session = getSessionFactory().getCurrentSession();
			Criteria criteria = session.createCriteria(Essay.class);
			criteria.addOrder(Order.desc("count"));
			criteria.setMaxResults(5);
			List<Essay> elist =criteria.list();
			return elist;
	}
	public List<Essay> getNewsEssay(Integer i) {
		Session session = getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(Essay.class);
		criteria.setMaxResults(i);
		criteria.addOrder(Order.desc("essay_id"));
		List<Essay> elist = criteria.list();
		return elist;
	}
	public  List<Essay> getEssayByEssayType(EssayType et) {
		Session session = getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(Essay.class);
		criteria.add(Restrictions.eq("essayType",et));
		List<Essay> elist = criteria.list();
		return elist;
		// TODO Auto-generated method stub
		
	}
	
	public Essay getByUrl(String url) {
		// TODO Auto-generated method stub
		Session session = getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(Essay.class);
		criteria.add(Restrictions.eq("url",url));
		return (Essay) criteria.list().get(0);
	}
	//每次点击文章Count添加一次
	public void updateEssayCount(String url) {
/*		Session session = getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(Essay.class);
		System.out.println(url);
		criteria.add(Restrictions.eq("url",url));
		Essay essay = (Essay) criteria.list().get(0);
*/		//String hql = "UPDATE Essay e SET e.count =123456 WHERE e.essay_id = 58";
		//String sql = "UPDATE essay SET count = "+(essay.getCount()+1)+" WHERE url = "+url;
		String sql = "UPDATE essay SET count = count+1 WHERE url = '"+url+"'";
		System.out.println("这里");
		//getSessionFactory().openSession().createSQLQuery(sql).executeUpdate();用他，多次运行就卡死 session没有关闭,需要手动关闭，如果不关闭将导致session关联的数据库连接无法释放，最后资源耗尽而使程序当掉。
		//getSessionFactory().openSession().close();
		getSessionFactory().getCurrentSession().createSQLQuery(sql).executeUpdate();
	}
	public void updateById(Essay essay) {
		// TODO Auto-generated method stub
		//文字一定要加 ''
		String sql;
		if(essay.getPicsrc()!=null){
			 sql = "UPDATE essay SET title = '"+essay.getTitle()+"', flag="+essay.getFlag()+", picsrc='"+essay.getPicsrc()+"', introduction='"+essay.getIntroduction()+"' WHERE essay_id = '"+essay.getEssay_id()+"'";			
		}else{
			 sql = "UPDATE essay SET title = '"+essay.getTitle()+"', flag="+essay.getFlag()+", introduction='"+essay.getIntroduction()+"' WHERE essay_id = '"+essay.getEssay_id()+"'";			
		}
		getSessionFactory().getCurrentSession().createSQLQuery(sql).executeUpdate();	
	}
	
	public Integer getMaxEssayId() {
		// TODO Auto-generated method stub
		//getSessionFactory().getCurrentSession().createSQLQuery(sql).executeUpdate();	
		String sql = "SELECT MAX(essay_id) FROM essay";
		SQLQuery q =getSessionFactory().getCurrentSession().createSQLQuery(sql);
		if( q.list().get(0) == null ){
			return 0;
		}
		return (Integer) q.list().get(0);		
	}
	

}
