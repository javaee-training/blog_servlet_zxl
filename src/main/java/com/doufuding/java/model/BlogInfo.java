package com.doufuding.java.model;

import java.util.Date;
import java.util.List;

public class BlogInfo {
	
	private Integer id;
	private Date createTime;
	private Date updateTime;
	private List<TagInfo> tags;
	private String title;
	private String content;
	private Integer createUserId;
	private UserInfo createUserInfo;
	
	public BlogInfo() {
		
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public Integer getCreateUserId() {
		return createUserId;
	}
	
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}
	
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public UserInfo getCreateUserInfo() {
		return createUserInfo;
	}
	
	public void setCreateUserInfo(UserInfo createUserInfo) {
		this.createUserInfo = createUserInfo;
	}
	
	public List<TagInfo> getTags() {
		return tags;
	}
	
	public void setTagId(List<TagInfo> tags) {
		this.tags = tags;
	}
	
}
