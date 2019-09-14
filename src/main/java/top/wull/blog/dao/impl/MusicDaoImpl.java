package top.wull.blog.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import top.wull.blog.antity.Music;
import top.wull.blog.dao.MusicDao;
import top.wull.common.dao.impl.BaseDaoImpl;

@Repository("musicDao")
@SuppressWarnings("unchecked")
public class MusicDaoImpl extends BaseDaoImpl<Music> implements MusicDao {

	public String getRandomMusicUrl() {
		String sql = "SELECT url FROM music M JOIN (SELECT CEIL(MAX(music_id)*RAND()) AS ID FROM music) AS m ON M.music_id >= m.ID LIMIT 1";
		SQLQuery q =getSessionFactory().getCurrentSession().createSQLQuery(sql);
		if( q.list().get(0) == null ){
			return "";
		}
		return (String) q.list().get(0);		
		}
	
	

}
