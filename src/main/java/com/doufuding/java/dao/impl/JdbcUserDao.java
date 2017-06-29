package com.doufuding.java.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.doufuding.java.util.DbUtil;
import com.doufuding.javaee.dao.UserDao;

public class JdbcUserDao implements UserDao{

	@Override
	public String getUserName(int userId) {
		DataSource dataSource = DbUtil.getDataSource();
		Connection connection = null;
		PreparedStatement psUserName = null;
		ResultSet rSet = null;
		String userName = null;
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String sql = "select user_name from bg_user where user_id=?";
		try {
			psUserName = connection.prepareStatement(sql);
			psUserName.setInt(1, userId);
			rSet = psUserName.executeQuery();
			userName = rSet.getString("user_name");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return userName;
	}

	@Override
	public boolean addUser(String userName, String userPassword) {
		DataSource dataSource = DbUtil.getDataSource();
		Connection connection = null;
		PreparedStatement psAddUser = null;
		int rows = 0;
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String sql = "insert into bg_user(user_name, user_password) values(?,?)";
		try {
			psAddUser = connection.prepareStatement(sql);
			psAddUser.setString(1, userName);
			psAddUser.setString(2, userPassword);
			rows = psAddUser.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (rows != 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateUserName(int userId, String userName) {
		return false;
	}

	@Override
	public boolean deleteUser(int userId) {
		return false;
	}

	@Override
	public boolean updateUserPassword(int userId, String userPassword) {
		// TODO Auto-generated method stub
		return false;
	}

}
