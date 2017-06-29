package com.doufuding.java.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.doufuding.java.util.DbUtil;
import com.doufuding.java.util.Md5Util;
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
		} finally {
			if (rSet != null) {
				try {
					rSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					psUserName.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			DbUtil.closeConnection(connection);
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
			psAddUser.setString(2, Md5Util.getMd5(userPassword));
			rows = psAddUser.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (psAddUser != null) {
				try {
					psAddUser.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			DbUtil.closeConnection(connection);
		}
		if (rows != 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateUserName(int userId, String userName) {
		DataSource dataSource = DbUtil.getDataSource();
		Connection connection = null;
		PreparedStatement psUpdateUserName = null;
		int rows = 0;
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String sql = "update bg_user set user_name= ? where user_id = ?";
		try {
			psUpdateUserName = connection.prepareStatement(sql);
			psUpdateUserName.setString(1, userName);
			psUpdateUserName.setInt(2, userId);
			rows = psUpdateUserName.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (psUpdateUserName != null) {
				try {
					psUpdateUserName.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			DbUtil.closeConnection(connection);
		}

		if (rows != 0) {
			return true;
		}

		return false;
	}

	@Override
	public boolean deleteUser(int userId) {
		DataSource dataSource = DbUtil.getDataSource();

		Connection connection = null;
		PreparedStatement psDeleteUser = null;
		int rows = 0;

		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String sql = "delete * from bg_user where user_id = ?";

		try {
			psDeleteUser = connection.prepareStatement(sql);
			psDeleteUser.setInt(1, userId);
			rows = psDeleteUser.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (psDeleteUser != null) {
				try {
					psDeleteUser.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			DbUtil.closeConnection(connection);		
		}
		if (rows != 0) {
			return true;
		}

		return false;
	}

	@Override
	public boolean updateUserPassword(int userId, String userPassword) {
		DataSource dataSource = DbUtil.getDataSource();
		
		Connection connection = null;
		PreparedStatement psUpdatePwd = null;
		int rows = 0;
		
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		String sql = "update bg_user set user_password = ? where user_id = ?";
		try {
			psUpdatePwd = connection.prepareStatement(sql);
			psUpdatePwd.setString(1, userPassword);
			psUpdatePwd.setInt(2, userId);
			rows = psUpdatePwd.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (psUpdatePwd != null) {
				try {
					psUpdatePwd.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			DbUtil.closeConnection(connection);
		}
		
		if (rows != 0) {
			return true;
		}
		
		return false;
	}

	@Override
	public int getCount() {
		DataSource dataSource = DbUtil.getDataSource();
		
		Connection connection = null;
		PreparedStatement psCount = null;
		ResultSet rSet = null;
		int count = 0;
		
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql = "select count(*) from bg_user";
		
		try {
			psCount = connection.prepareStatement(sql);
			rSet = psCount.executeQuery();
			if (rSet.next()) {
				count = rSet.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rSet != null) {
				try {
					rSet.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					psCount.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			DbUtil.closeConnection(connection);
		}
		
		return count;
	}

}
