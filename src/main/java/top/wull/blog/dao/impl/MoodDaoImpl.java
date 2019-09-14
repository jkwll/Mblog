package top.wull.blog.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import top.wull.blog.antity.Essay;
import top.wull.blog.antity.Mood;
import top.wull.blog.dao.MoodDao;
import top.wull.common.dao.impl.BaseDaoImpl;

@Repository("moodDao")
public class MoodDaoImpl extends BaseDaoImpl<Mood> implements MoodDao{
public void showtest(){
	System.out.println("showtest");
}

	public void updateById(Mood mood) {
		//文字一定要加 ''
		System.out.println("0000000000000000");
		String sql;
		String sql1 = "content = '"+mood.getContent()+"'";
		String sql2 = "flag = "+mood.getFlag();
		String sql3 = "picsrc = '"+mood.getPicsrc()+"'";
		String sql4 = "content = '"+mood.getContent()+"'";
		String sql5 = "hpicsrc = '"+mood.getHpicsrc()+"'";
		String sql6 = "time = '"+mood.getTime()+"'";
		String sqlL = "UPDATE mood SET ";
		String sqlR = " WHERE mood_id = '"+mood.getMood_id()+"'";
		String sqlZ = "";
		if(mood.getContent()!=null){
			sqlZ = sql1+",";
		}
		if(mood.getFlag()!=null){
			sqlZ = sqlZ+sql2+",";
		}
		if(mood.getPicsrc()!=null){
			sqlZ = sqlZ+sql3+",";
		}
		if(mood.getContent()!=null){
			sqlZ = sqlZ+sql4+",";
		}	
		if(mood.getHpicsrc()!=null){
			sqlZ = sqlZ+sql5+",";
		}
		if(mood.getTime()!=null){
			sqlZ = sqlZ+sql6+",";
		}
		if(!sqlZ.equals("")){
		    sql = sqlL + sqlZ.substring(0, sqlZ.length()-1) + sqlR;
			getSessionFactory().getCurrentSession().createSQLQuery(sql).executeUpdate();					
		}else{
			System.out.println("拼sql异常");
		}
	}

	public void getNews(Integer i) {
		// TODO Auto-generated method stub
		
	} 

}
