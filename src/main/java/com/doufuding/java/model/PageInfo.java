package com.doufuding.java.model;

public class PageInfo {
	
	public PageInfo(Integer page) {
		// TODO Auto-generated constructor stub
	}
	private int pageNum = 1;
	private long rows;
	private int rowsPerPage = 10;
	
	public PageInfo(int pageNum) {
		if (pageNum < 1) {
			throw new RuntimeException("页数必须大于等于1.");
		}
		this.pageNum = pageNum;
	}
	
	public int getRowsPerPage() {
		return rowsPerPage;
	}
	
	public void setRowsPerPage(int rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}
	
	public int getPageNum() {
		return pageNum;
	}
	
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
	public long getRows() {
		return rows;
	}
	
	public void setRows(long rows) {
		this.rows = rows;
	}
	
	public int getStartPosition() {
		return rowsPerPage * (pageNum - 1);
	}
	
	public long getPageCount() {
		if (rows == 0) {
			return 0;
		}
		int mod = (int) (rows % rowsPerPage);
		return rows/rowsPerPage + (mod > 0 ? 1 : 0);
	}
	
	public boolean isInvalidRange() {
		long pageCount = getPageCount();
		
		if (pageNum == 1 && pageCount == 0) {
			return true;
		}
		return false;
	}
	
}
