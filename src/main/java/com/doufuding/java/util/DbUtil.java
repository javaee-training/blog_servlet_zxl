package com.doufuding.java.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.postgresql.jdbc2.optional.SimpleDataSource;

public class DbUtil {
	
	private final static String url = "jdbc:postgresql://localhost:5432/db_blog";
	private final static String userName = "postgres";
	private final static String userPassword = "5031434714";
	
	public static DataSource getDataSource() {
		SimpleDataSource dataSource = new SimpleDataSource();
		dataSource.setUrl(url);
		dataSource.setUser(userName);
		dataSource.setPassword(userPassword);
		
		return dataSource;
	}
	
	public static void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
