package top.wull.blog.service.impl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import top.wull.blog.dao.MusicDao;
import top.wull.blog.service.MusicService;
@Service("musicService")
@Transactional(readOnly=false)
public class MusicServiceImpl implements MusicService{
	@Resource(name="musicDao")
	private MusicDao mud ;

	public String getRandomMusicUrl() {
		// TODO Auto-generated method stub
		return mud.getRandomMusicUrl();
	}
}
