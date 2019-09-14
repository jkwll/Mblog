package top.wull.blog.action;
import java.util.Date;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import top.wull.blog.antity.Mood;
import top.wull.blog.service.MoodService;
import top.wull.blog.util.PageBean;
@Controller("moodAction")
@Scope("prototype")
public class MoodAction extends ActionSupport {
	@Resource(name="moodService")
	MoodService  ms ;
	Integer mood_id;
	String content;
	String picsrc;
	Integer flag;
	Date time;
	Mood mood;
	
	private Integer currentPage = 1;
	private Integer pageSize = 9;	
	
	
	public String getPicsrc() {
		return picsrc;
	}
	public void setPicsrc(String picsrc) {
		this.picsrc = picsrc;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Mood getMood() {
		return mood;
	}
	public void setMood(Mood mood) {
		this.mood = mood;
	}
	public Integer getMood_id() {
		return mood_id;
	}
	public void setMood_id(Integer mood_id) {
		this.mood_id = mood_id;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public String mood(){
		//封装离线查询对象
		DetachedCriteria dc = DetachedCriteria.forClass(Mood.class);
		//判断并封装参数
		dc.addOrder(Order.asc("time"));
		dc.add(Restrictions.eq("flag", 1));
		//1 调用Service查询分页数据(PageBean)
		PageBean pb = ms.getPageBean(dc,currentPage,pageSize);
		for (int i = 0 ;  i < pb.getList().size() ; i ++) {
			Mood mood = (Mood) pb.getList().get(i);
			//String src = mood.getPicsrc();
			//防止没有图片导致空指针异常
/*			if(src!=null && !src.equals("")){
				mood.setHpicsrc(src.substring(0, src.lastIndexOf(".")-"lue".length()) +
						src.substring(src.lastIndexOf(".")) );
			}
*/
			}

		ActionContext.getContext().put("pageBean", pb);
		return "moodlist";//前台mood页面
	}
	public String list(){
		return "moodlist";//后台mood管理页面
	}
	public String add(){
		return "moodadd";
	}


}
