package top.wull.blog.antity;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
@Entity
@Table(name="mood")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Mood implements Serializable{
	@Id @Column(name="mood_id")
	//主键由数据库自动生成（主要是自动增长型） 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer mood_id;
	String content;
	Date time;
	//被压缩的图片
	String picsrc;
	//高清图片
	String hpicsrc;
	Integer flag;
	

	public Mood(Integer mood_id, String content, Date time, String picsrc,String hpicsrc, Integer flag) {
		super();
		this.mood_id = mood_id;
		this.content = content;
		this.time = time;
		this.picsrc = picsrc;
		this.flag = flag;
		this.hpicsrc = hpicsrc;
	}
	public Mood(){}
	public Integer getMood_id() {
		return mood_id;
	}

	public String getHpicsrc() {
		return hpicsrc;
	}
	public void setHpicsrc(String hpicsrc) {
		this.hpicsrc = hpicsrc;
	}
	public void setMood_id(Integer mood_id) {
		this.mood_id = mood_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return format.format(this.time);
		//return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

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
	@Override
	public String toString() {
		return "Mood [mood_id=" + mood_id + ", content=" + content + ", time=" + time + ", picsrc=" + picsrc + ", flag="
				+ flag + "]";
	}



}
