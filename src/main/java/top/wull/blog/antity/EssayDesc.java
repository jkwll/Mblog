package top.wull.blog.antity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
@Entity
@Table(name="essayDesc")
public class EssayDesc {
	@Id @Column(name="essay_id")

	Integer essay_id;
	@Column(name="content" ,length=1000*1000)
	String content;
	public EssayDesc(){
		
	}
	public Integer getEssay_id() {
		return essay_id;
	}
	public void setEssay_id(Integer essay_id) {
		this.essay_id = essay_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public EssayDesc(Integer essay_id, String content) {
		super();
		this.essay_id = essay_id;
		this.content = content;
	}
	@Override
	public String toString() {
		return "EssayDesc [essay_id=" + essay_id + ", content=" + content + "]";
	}
	
}
