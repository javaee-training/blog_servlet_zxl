package com.doufuding.javaee.dao;

import java.util.List;

import com.doufuding.java.model.TagInfo;

public interface TagDao {
	
	//添加tag
	public boolean addTagInfo(String tagName, int createUserId, String createTime);
	
	//获得tag
	public TagInfo getTagInfo(int tagId);
	
	//修改tag_name
	public boolean updateTagName(int tagId, String tagName, int updateUserId);
	
	//获得所有标签
	public List<TagInfo> geTagInfos();
}
