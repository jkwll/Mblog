package top.wull.blog.dao;

import top.wull.blog.antity.Music;
import top.wull.common.dao.BaseDao;

public interface MusicDao extends BaseDao<Music>{

	String getRandomMusicUrl();


}
