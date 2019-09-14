package top.wull.blog.service.impl;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import top.wull.blog.antity.Essay;
import top.wull.blog.antity.EssayType;
import top.wull.blog.dao.EssayDao;
import top.wull.blog.dao.EssayTypeDao;
import top.wull.blog.service.EssayService;
import top.wull.blog.util.PageBean;
@Service("essayService")
@Transactional(readOnly=false)
public class EssayServiceImpl implements EssayService{
	@Resource(name="essayDao")
	private EssayDao ed ;
	@Resource(name="essayTypeDao")
	private EssayTypeDao etd ;

	public void addEssay(Essay essay) {

		ed.save(essay);			
		
	}

	public List getAllKnowledgeEssay() {
		List<Essay> list = ed.getAll(Essay.class);
		Collections.reverse(list); // 倒序排列
		return list;
	}

	public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize) {
		//1 调用Dao查询总记录数
		Integer totalCount = ed.getTotalCount(dc);
		//2 创建PageBean对象
		PageBean pb = new PageBean(currentPage, totalCount, pageSize);
		//3 调用Dao查询分页列表数据
		Integer Start = pb.getTotalCount()-pb.getPageSize()*pb.getCurrentPage();
		//到最后一页的时候，start可能会小于零
		if(Start<0){
			pageSize = pageSize + Start;
			Start = 0 ;
		}
		//System.out.println("开始："+Start+"大小："+pageSize);
		List<Essay> list = ed.getPageList(dc,Start,pageSize);
		Collections.reverse(list); // 倒序排列
		//4 列表数据放入pageBean中.并返回
		pb.setList(list);
		return pb;
	}

	public void updateEssay(Essay essay) {
		ed.update(essay);
	}
	public void updateEssayCountByURL(String url) {
		// TODO Auto-generated method stub
		ed.updateEssayCountByURL(url);
	}
	public List<Essay> getMaxCountEssay() {
		return ed.getMaxCountEssay();
	}


	public List<Essay> getNewEssay(Integer i) {
		return ed.getNewsEssay(i);
	}

	public List<Essay> getEssayByEssayType(EssayType et) {
		return ed.getEssayByEssayType( et);
	}

	public Essay getByUrl(String url) {
		// TODO Auto-generated method stub
		return ed.getByUrl(url);
	}

	public void updateEssayCount(String url) {
		// TODO Auto-generated method stub
		ed.updateEssayCount(url);
	}

	public void updateById(Essay e) {
		ed.updateById(e);
		// TODO Auto-generated method stub
		
	}

	public  Essay getById(Integer essay_id) {
		// TODO Auto-generated method stub
		return ed.getById(essay_id);
	}

	public Integer getMaxEssayId() {
		return ed.getMaxEssayId();
		// TODO Auto-generated method stub
		
	}



}
