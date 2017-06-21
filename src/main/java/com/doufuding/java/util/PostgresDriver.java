package com.doufuding.java.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.doufuding.java.model.BlogInfo;
import com.doufuding.java.model.TagInfo;

public class PostgresDriver {
	public static final int COUNT_USER = 1;
	public static final int COUNT_ARTICLE = 2;
	public static final int COUNT_TAG = 3;
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
	
	
	/*
	 * 根据用户ID获得用户名
	 * 
	 */
	public String getUserName(int userId) throws SQLException {
		Connection connection = getConnection();
		Statement statement = connection.createStatement();
		String sql = "select user_name from bg_user where user_id='"+userId+"'";
		ResultSet resultSet = statement.executeQuery(sql);
		String userName = null;
		if (resultSet.next()) {
			userName = resultSet.getString("user_name");
		}
		return userName;
	}
	
	/*
	 * 根据标签ID获得标签值
	 * 
	 */
	public String getTagName(int tagId) throws SQLException {
		Connection connection = getConnection();
		Statement statement = connection.createStatement();
		String sql = "select tag_name from bg_tag where tag_id='"+tagId+"'";
		ResultSet rSet = statement.executeQuery(sql);
		String tagName = null;
		if (rSet.next()) {
			tagName = rSet.getString("tag_name");
		}
		return tagName;
	}
	
	/*
	 * 将bg_tag表中的标签取出来等待使用
	 * 
	 */
	public List<TagInfo> geTagInfos() {
		PostgresDriver postgresDriver = new PostgresDriver();
		String sql = "select tag_id, tag_name from bg_tag";
		Statement statement = null;
		try {
			statement = postgresDriver.getConnection().createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ResultSet tag = null;
		try {
			tag = statement.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<TagInfo> tags = new ArrayList<>();
		try {
			if (tag != null) {
				while (tag.next()) {
					int tagId = tag.getInt("tag_id");
					String tagName = tag.getString("tag_name");
					tags.add(new TagInfo(tagId, tagName));
				}
			} else {
				tags = null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tags;
	}
	
	/*
	 * 获得bg_article表中的数据传回index.jsp
	 * 此方法需在后期单独做成一个工具类，以减少不同页面返回index.jsp造成的冗余。
	 * 
	 */
	public List<BlogInfo> getBlogInfos() {
		PostgresDriver postgresDriver = new PostgresDriver();
		String sql = "select * from bg_article";
		Statement statement = null;
		try {
			statement = postgresDriver.getConnection().createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ResultSet resultSet2 = null;
		try {
			resultSet2 = statement.executeQuery(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		List<BlogInfo> blogInfos = new ArrayList<>();
		try {
			if (resultSet2 != null) {
				while (resultSet2.next()) {
					BlogInfo blogInfo = new BlogInfo();
					blogInfo.setId(resultSet2.getInt("article_id"));
					blogInfo.setTitle(resultSet2.getString("article_id"));
					blogInfo.setContent(resultSet2.getString("article_content"));
					blogInfo.setCreateTime(resultSet2.getTimestamp("create_time"));
					blogInfo.setCreateUserName(postgresDriver.getUserName(resultSet2.getInt("create_user_id")));
					blogInfo.setTags(postgresDriver.getTagName(resultSet2.getInt("tag_id")));
					blogInfo.setUpdateTime(resultSet2.getTimestamp("update_time"));
					blogInfos.add(blogInfo);
				}
			} else {
				blogInfos = null;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return blogInfos;
	}

	/*
	 * 根据传入的值获得表的行数。
	 * 
	 */
	public int getCount(int COUNT_SIGN) {
		PostgresDriver postgresDriver = new PostgresDriver();
		String sql = null;
		switch (COUNT_SIGN) {
		case COUNT_USER:
			sql = "select count(*) from bg_user";
			break;
		case COUNT_ARTICLE:
			sql = "select count(*) from bg_article";
			break;
		case COUNT_TAG:
			sql = "select count(*) from bg_tag";
			break;

		default:
			sql = "";
			break;
		}
		Statement statement = null;
		try {
			statement = postgresDriver.getConnection().createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int rows = 0;
		try {
			rows = statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rows;
	}
}
