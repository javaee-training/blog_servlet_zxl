package com.doufuding.java.model;

import java.util.Date;

public class BlogInfo {
	
	private Integer id;
	private Date createTime;
	private Date updateTime;
	private String tags;
	private String title;
	private String content;
	private String createUserName;
	private String updateUserName;
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
	
	public String getCreateUserName() {
		return createUserName;
	}
	
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	
	public String getUpdateUserName() {
		return updateUserName;
	}
	
	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
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
	
	public String getTags() {
		return tags;
	}
	
	public void setTags(String tags) {
		this.tags = tags;
	}
	
}
