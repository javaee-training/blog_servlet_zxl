package com.doufuding.java.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.doufuding.java.model.TagInfo;
import com.doufuding.java.util.DbUtil;
import com.doufuding.java.util.TimeWithZoneUtil;
import com.doufuding.javaee.dao.TagDao;

public class JdbcTagDao implements TagDao{

	@Override
	public TagInfo getTagInfo(int tagId) {
		DataSource dataSource = DbUtil.getDataSource();

		Connection connection = null;
		PreparedStatement psTagInfo = null;
		ResultSet rSet = null;
		TagInfo tagInfo = null;

		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql = "select * from bg_tag where tag_id = ?";
		try {
			psTagInfo = connection.prepareStatement(sql);
			psTagInfo.setInt(1, tagId);
			rSet = psTagInfo.executeQuery();
			if (rSet.next()) {
				tagInfo = new TagInfo(tagId, rSet.getString("tag_name"));
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
			}
			if (psTagInfo != null) {
				try {
					psTagInfo.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			DbUtil.closeConnection(connection);
		}

		return tagInfo;
	}

	@Override
	public boolean updateTagName(int tagId, String tagName, int updateUserId) {
		DataSource dataSource = DbUtil.getDataSource();

		Connection connection = null;
		PreparedStatement psUpdateTName = null;
		int rows = 0;

		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql = "update bg_tag set tag_name = ?, update_user_id = ?, update_time = ? where tag_id = ?";
		try {
			psUpdateTName = connection.prepareStatement(sql);
			psUpdateTName.setString(1, tagName);
			psUpdateTName.setInt(2, updateUserId);
			psUpdateTName.setString(3, TimeWithZoneUtil.getTimeWithZone());
			psUpdateTName.setInt(4, tagId);
			rows = psUpdateTName.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (psUpdateTName != null) {
				try {
					psUpdateTName.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
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
	public List<TagInfo> geTagInfos() {
		DataSource dataSource = DbUtil.getDataSource();
		
		Connection connection = null;
		PreparedStatement psTagInfos = null;
		ResultSet rSet = null;
		List<TagInfo> tagInfos = new ArrayList<TagInfo>();
		
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql = "select * from bg_tag";
		try {
			psTagInfos = connection.prepareStatement(sql);
			rSet = psTagInfos.executeQuery();
			while (rSet.next()) {
				tagInfos.add(new TagInfo(rSet.getInt("tag_id"), rSet.getString("tag_name")));	
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
			}
			if (psTagInfos != null) {
				try {
					psTagInfos.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			DbUtil.closeConnection(connection);
		}
		return tagInfos;
	}

	@Override
	public boolean addTagInfo(String tagName, int createUserId, String createTime) {
		// TODO Auto-generated method stub
		return false;
	}

}
