package com.doufuding.javaee.dao;

import java.util.List;

import com.doufuding.java.model.BlogInfo;

public interface ArticleDao {
	
	//插入文章
	public boolean addArticle();
	
	//获得文章
	public BlogInfo getBlogInfo(int blogId);
	
	//获得所有文章
	public List<BlogInfo> getBlogInfos();
	
	//修改文章
	public boolean updateArticle(BlogInfo blogInfo);
	
}
