package top.wull.blog.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import top.wull.blog.antity.Essay;
import top.wull.blog.antity.EssayType;
import top.wull.blog.util.PageBean;

public interface EssayService {
	public void addEssay(Essay essay);
	public void updateEssay(Essay essay);
	public List getAllKnowledgeEssay();
	//分页业务方法
	PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize);
	public List getMaxCountEssay();
	public void updateEssayCountByURL(String url);
	//获取最新文章动态
	public List<Essay> getNewEssay(Integer i);
	public List<Essay> getEssayByEssayType(EssayType et);
	public Essay getByUrl(String url);
	public void updateEssayCount(String url);
	public void updateById(Essay e);
	public  Essay getById(Integer essay_id);
	public Integer getMaxEssayId();
}
