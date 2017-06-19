package com.doufuding.java.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgresDriver {
	private String url = "jdbc:postgresql://localhost:5432/db_blog";
	private Connection connection = null;
	private String userName = "postgres";
	private String userPassword = "5031434714";
	
	{
		try {
			Class.forName("org.postgresql.Driver");
		} catch (Exception e) {
			System.out.println("没有发现postgresql驱动。");
		}
	}
	
	public Connection getConnection() {
		try {
			connection = (Connection) DriverManager.getConnection(url, userName, userPassword);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("无法连接数据库。请检查用户名或密码。");
		}
		//System.out.println("数据库链接成功。");
		return connection;
	}
	
	public String getTagName(int tagId) throws SQLException {
		Connection connection = getConnection();
		Statement statement = connection.createStatement();
		String sql = "select tag_name from bg_tag where tag_id='"+tagId+"'";
		ResultSet rSet = statement.executeQuery(sql);
		return rSet.getString("tag_name");
	}
	
}
