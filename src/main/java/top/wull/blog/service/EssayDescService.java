package top.wull.blog.service;

import top.wull.blog.antity.EssayDesc;

public interface EssayDescService  {

	EssayDesc getDescById(Integer id);

	void saveEssayDesc(EssayDesc ed);	

}
