package top.wull.blog.action;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import top.wull.blog.antity.Essay;
import top.wull.blog.service.EssayService;
import top.wull.blog.service.EssayTypeService;
import top.wull.blog.service.MoodService;
import top.wull.blog.service.MusicService;
import top.wull.blog.util.PageBean;
@Controller("indexAction")
@Scope("prototype")
public class IndexAction extends BaseAction {
	/*
	 * 写在dao里面的方法，连续运行十几次就不行了，不知道为什么
	 * */
	@Resource(name="essayService")
	EssayService  es ;
	@Resource(name="moodService")
	MoodService  ms ;
	Essay e = new Essay();
	private Integer currentPage = 1;
	private Integer pageSize = 6;
	@Resource(name="musicService")
	MusicService  mus ;
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public String life(){
		return "life";
	}
	
	public String list() throws Exception {
		//封装离线查询对象
		DetachedCriteria dc = DetachedCriteria.forClass(Essay.class);
		//判断并封装参数
		dc.add(Restrictions.isNotNull("recommend"));//推荐的文章
		//1 调用Service查询分页数据(PageBean)
		PageBean pb = es.getPageBean(dc,currentPage,pageSize);
		//2 将PageBean放入request域,转发到列表页面显示
		/*Map<String, Object> data = new HashMap<>();
		data.put("news", news());
		data.put("newEssaylist", newEssaylist(8));
		data.put("showMaxCountEssay", showMaxCountEssay());
		data.put("pageBean", pb);
		ActionContext.getContext().put("data", data);			
		*/
		//ActionContext.getContext().put("news", news());			
		ActionContext.getContext().put("news", newEssaylist(6));			
		ActionContext.getContext().put("newEssaylist", newEssaylist(8));			
		ActionContext.getContext().put("showMaxCountEssay", showMaxCountEssay());		
		ActionContext.getContext().put("pageBean", pb);
		ActionContext.getContext().put("music", getRandomMusicUrl());
		
		System.out.println("dddddd");
		return "list";
	}
	
	String getRandomMusicUrl(){
		return mus.getRandomMusicUrl();
	}
	//主页的最新动态
	public List news(){
		//封装离线查询对象
		/*DetachedCriteria dc = DetachedCriteria.forClass(Essay.class);
		//1 调用Service查询分页数据(PageBean)
		PageBean pb = es.getPageBean(dc,1,6);
		return pb.getList();*/
		//ms.getNews(6);
		return null;
		
	}

}
