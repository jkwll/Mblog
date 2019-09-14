package top.wull.blog.action;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import top.wull.blog.antity.Essay;
import top.wull.blog.antity.EssayDesc;
import top.wull.blog.antity.EssayType;
import top.wull.blog.antity.Mood;
import top.wull.blog.service.EssayDescService;
import top.wull.blog.service.EssayService;
import top.wull.blog.service.EssayTypeService;
import top.wull.blog.util.PageBean;
@Controller("EssayAction")
@Scope("prototype")
public class EssayAction extends BaseAction{
	@Resource(name="essayTypeService")
	EssayTypeService  ets ;
	@Resource(name="essayService")
	EssayService  es ;
	@Resource(name="essayDescService")
	EssayDescService eds;
	Essay essay = new Essay();
	String url;
	String content;
	String time;
	String title;
	String essay_id;
	
	private String characters; //搜索的关键字
	private Integer currentPage = 1;
	private Integer pageSize = 6;	
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	
	public String getCharacters() {
		return characters;
	}
	public void setCharacters(String characters) {
		this.characters = characters;
	}
	//i表示查询那个类型的文章  2类 学习和生活
	public String showEssay(int i) throws Exception {
		//封装离线查询对象
		DetachedCriteria dc = DetachedCriteria.forClass(Essay.class);
		//判断并封装参数
		dc.add(Restrictions.eq("flag", i)); //flag=1 就是学无止境
		dc.addOrder(Order.asc("time"));
		//1 调用Service查询分页数据(PageBean)
		PageBean pb = es.getPageBean(dc,currentPage,pageSize);
		//2 将PageBean放入request域,转发到列表页面显示
		//es.getPageBean2(currentPage, pageSize);
		ActionContext.getContext().put("pageBean", pb);
		ActionContext.getContext().put("EssayType", getEssayType(i));
System.out.println("pb=="+pb.toString());
		//用到了session缓存、同一台浏览器，对应用一个session，这里说明用户已经是第二次访问这里了

		//关于详细介绍，查看笔记本
		/*if(ActionContext.getContext().getSession().get("EssayType")!=null){
			System.out.println("111111zheliddd+ActionContext.getContext().getSession()"+ActionContext.getContext().getSession());
			//运行这里不不用查询数据库
			ActionContext.getContext().put("EssayType",ActionContext.getContext().getSession().get("EssayType"));			
		}else{
			//先把查询到的数据放到session里面，然后再传到context分享到jsp页面
			ActionContext.getContext().getSession().put("EssayType", getEssayType(i));
			ActionContext.getContext().put("EssayType", getEssayType(i));
		}	*/	
		commonSelect();
		System.out.println("sdfsdf");
		return "list";
	}
	public void commonSelect() throws Exception{
		ActionContext.getContext().put("newEssaylist", newEssaylist(6));
		ActionContext.getContext().put("showMaxCountEssay", showMaxCountEssay());
		
	}
	public String lookEssay() throws Exception{
		//ServletActionContext.getRequest().setCharacterEncoding("UTF-8");
		//这里不支持中文，所以jsp的名字暂时不能为中文
		String url = ServletActionContext.getRequest().getRequestURI();
		//两个文章类型 位置
		int n = url.indexOf("knowledge");
		if(n == -1 ){
		 n = url.indexOf("life");			
		}
		// url=knowledge/id
		url = url.substring(n, url.length());
		//查询文章信息
		Essay essay = es.getByUrl(url);
		//如果数据库有这个数据
		if(essay!=null){
			//浏览次数+1 
			es.updateEssayCount(url);
			String path = ServletActionContext.getServletContext().getRealPath("/");
			System.out.println("path=="+path);
			File jspfile=new File(path+essay.getUrl()+".jsp");    
			//如果文件系统里面没有这个jsp文件（第一次访问），就在文件系统里面新建jsp文件
			if(!jspfile.exists()) 
			{    
			    try {    
			    	//根据数据库essay新建文件
			    	FileOutputStream jspfos = new FileOutputStream(jspfile);
					OutputStreamWriter  osw = new OutputStreamWriter(jspfos,"UTF-8");//初始化输出流
					jspfile.createNewFile();
					EssayDesc ed = eds.getDescById(essay.getEssay_id());  
					String wstr = ed.getContent();
					osw.write(wstr);
					osw.close();		
			    } catch (IOException e) {    
			        // TODO Auto-generated catch block    
			        e.printStackTrace();    
			    }    
			}    

		}
		ActionContext.getContext().put("EssayType", getEssayType(Integer.valueOf(essay.getEssayType().getFlag())));
		ActionContext.getContext().put("lookEssay", essay);
		if(essay!=null){
			ActionContext.getContext().put("newEssaylist", newEssaylist(6));
			ActionContext.getContext().put("showMaxCountEssay", showMaxCountEssay());



			return "look";
		}
		return null;
	}
	/*
	 * 
	 * 文章搜索
	 */
	public String search() throws Exception{
		//封装离线查询对象
		DetachedCriteria dc = DetachedCriteria.forClass(Essay.class);
		//判断并封装参数
		dc.add(Restrictions.or(Restrictions.like("keywords", "%"+characters+"%"),
				Restrictions.like("title", "%"+characters+"%"))); 
		dc.addOrder(Order.asc("time"));
		PageBean pb = es.getPageBean(dc,currentPage,pageSize);
		ActionContext.getContext().put("pageBean", pb);
		
		//用到了session缓存、同一台浏览器，对应用一个session，这里说明用户已经是第二次访问这里了
				if(ActionContext.getContext().getSession().get("EssayType")!=null){
					System.out.println("111111zheliddd+ActionContext.getContext().getSession()"+ActionContext.getContext().getSession());
					//运行这里不不用查询数据库
					ActionContext.getContext().put("EssayType",ActionContext.getContext().getSession().get("EssayType"));			
				}else{
					//先把查询到的数据放到session里面，然后再传到context分享到jsp页面
					ActionContext.getContext().getSession().put("EssayType", getEssayType(1));
					ActionContext.getContext().put("EssayType", getEssayType(1));
				}		
				ActionContext.getContext().put("newEssaylist", newEssaylist(6));
				ActionContext.getContext().put("showMaxCountEssay", showMaxCountEssay());
		
		return "show";
	}
	/**
	 * 文章导航栏
	 * @return
	 */
	public List<EssayType> getEssayType(Integer i){
		return ets.getEssayTypeByFlag(i);
	}
	/**
	 * 添加文章点击次数
	 * @return
	 */
	public String addCount(){
		es.updateEssayCountByURL(url);
		return "essayadd";
	}
	public String test(){
		String url = this.getClass().getResource("/").getPath();
		System.out.println("url=="+url);
		return "essaytest";
	}
	public String getUrl() {
		return url;
	}
	/*public String admin() {
		return "essayadmin";
	}*/
	

	public void setUrl(String url) {
		this.url = url;
	}
	

	
}
