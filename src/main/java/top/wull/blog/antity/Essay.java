package top.wull.blog.antity;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
@Entity
@Table(name="essay")
//知识篇
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Essay implements Comparable<Essay>{
	@Id @Column(name="essay_id")
	//主键自增长 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer essay_id;
	Integer count;
	@Column(name="editor" ,length=20)
	String editor;//编辑人
	Integer flag;
	@Column(name="introduction" ,length=1000)
	String introduction ;//导语
	String keywords;
	String picsrc;
	Integer recommend;
	Date time;
	String title;	
	//定义该Essay关联外键EssayType
	@ManyToOne(targetEntity=EssayType.class)
	@JoinColumn(name="type_id" , nullable=false)
	@Cascade(CascadeType.ALL)
	EssayType essayType;
	
	String url;
	//String url;
	
	//定义该Essay关联外键EssayType。
	//@OneToOne(targetEntity=EssayDesc.class)
	//@JoinColumn(name="essayDesc_id" , nullable=false)
	//共享主键
/*	@PrimaryKeyJoinColumn 
	@Cascade(CascadeType.ALL)//表示联级操作，文章id改了，EssayDesc的essay_id也改
	EssayDesc essayDesc;
*/	
	public Essay(){}
	public Essay(Integer essay_id, String title, Date time, String editor, String introduction, EssayType essayType,
			String picsrc, String url, String keywords, Integer flag, Integer recommend, Integer count) {
		super();
		this.essay_id = essay_id;
		this.title = title;
		this.time = time;
		this.editor = editor;
		this.introduction = introduction;
		this.essayType = essayType;
		this.picsrc = picsrc;
		//this.essayDesc = essayDesc;
		this.url = url;
		this.keywords = keywords;
		this.flag = flag;
		this.recommend = recommend;
		this.count = count;
	}

/*	public EssayDesc getEssayDesc() {
		return essayDesc;
	}

	public void setEssayDesc(EssayDesc essayDesc) {
		this.essayDesc = essayDesc;
	}
*/
	public String getKeywords() {
		return keywords;
	}


	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}


	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getRecommend() {
		return recommend;
	}
	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}
	public Integer getEssay_id() {
		return essay_id;
	}
	public void setEssay_id(Integer essay_id) {
		this.essay_id = essay_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(this.time);
		//return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getEditor() {
		return editor;
	}
	public void setEditor(String editor) {
		this.editor = editor;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
	public EssayType getEssayType() {
		return essayType;
	}
	public void setEssayType(EssayType essayType) {
		this.essayType = essayType;
	}
	public String getPicsrc() {
		return picsrc;
	}
	public void setPicsrc(String picsrc) {
		this.picsrc = picsrc;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public int compareTo(Essay o) {
		// TODO Auto-generated method stub
		 int i =   o.getRecommend()-this.getRecommend();//先按照推荐等级排序  
	        if(i == 0){  
	            return  o.getFlag()-this.flag;//如果推荐等级相等了再用flag进行排序  
	        }  
	        return i;  
	}
}
