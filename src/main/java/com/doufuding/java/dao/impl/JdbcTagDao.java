package com.doufuding.java.dao.impl;

import java.util.List;

import com.doufuding.java.model.TagInfo;
import com.doufuding.javaee.dao.TagDao;

public class JdbcTagDao implements TagDao{

	@Override
	public TagInfo geTagInfo(int tagId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateTagName(int tagId, String tagName, int updateUserId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<TagInfo> geTagInfos() {
		// TODO Auto-generated method stub
		return null;
	}

}
