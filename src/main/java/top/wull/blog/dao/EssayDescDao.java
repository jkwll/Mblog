package top.wull.blog.dao;

import top.wull.blog.antity.EssayDesc;
import top.wull.blog.antity.EssayType;
import top.wull.common.dao.BaseDao;

public interface EssayDescDao  extends BaseDao<EssayDesc>{

	EssayDesc getDescById(Integer id);

}
