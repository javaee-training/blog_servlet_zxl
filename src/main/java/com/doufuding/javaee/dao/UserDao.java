package com.doufuding.javaee.dao;

public interface UserDao {
	
	//获取用户名
	public String getUserName(int userId);
	
	//添加用户
	public boolean addUser(String userName, String userPassword);
	
	//修改用户名
	public boolean updateUserName(int userId, String userName);
	
	//修改密码
	public boolean updateUserPassword(int userId, String userPassword);
	
	//修改用户名
	public boolean deleteUser(int userId);
	
	//获得用户人数
	public int getCount();
	
}
