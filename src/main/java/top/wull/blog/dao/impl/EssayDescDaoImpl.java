package top.wull.blog.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import top.wull.blog.antity.Essay;
import top.wull.blog.antity.EssayDesc;
import top.wull.blog.dao.EssayDescDao;
import top.wull.common.dao.impl.BaseDaoImpl;
@Repository("essayDescDao")
@SuppressWarnings("unchecked")

public class EssayDescDaoImpl extends BaseDaoImpl<EssayDesc> implements EssayDescDao{

	public EssayDesc getDescById(Integer id) {
		Session session = getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(EssayDesc.class);
		criteria.add(Restrictions.eq("essay_id",id));
		List list = criteria.list();
		
		return (EssayDesc) list.get(0);

	}
	
	

}
