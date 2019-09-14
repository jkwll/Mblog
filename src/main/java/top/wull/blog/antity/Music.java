package top.wull.blog.antity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="music")
//知识篇
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Music {
	@Id @Column(name="music_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer music_id;
	String url;
	String flag;
	public Integer getMusic_id() {
		return music_id;
	}
	public void setMusic_id(Integer music_id) {
		this.music_id = music_id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	@Override
	public String toString() {
		return "Music [music_id=" + music_id + ", url=" + url + ", flag=" + flag + "]";
	}
	public Music(Integer music_id, String url, String flag) {
		super();
		this.music_id = music_id;
		this.url = url;
		this.flag = flag;
	}
	
	
}
