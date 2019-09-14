package top.wull.blog.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import top.wull.blog.antity.EssayDesc;
import top.wull.blog.dao.EssayDao;
import top.wull.blog.dao.EssayDescDao;
import top.wull.blog.service.EssayDescService;
@Service("essayDescService")
@Transactional(readOnly=false)

public class EssayDescServiceimpl implements EssayDescService {
	@Resource(name="essayDescDao")
	private EssayDescDao edd ;
	public EssayDesc getDescById(Integer id){
		// TODO Auto-generated method stub
		return edd.getDescById(id);
	}
	public void saveEssayDesc(EssayDesc ed) {
		// TODO Auto-generated method stub
		edd.save(ed);
		
	}

}
