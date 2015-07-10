package com.kad.db;

import java.sql.ResultSet;

public class DataPacket {
	private int curPage;
	private int totalPage;
	private ResultSet data;
	private int totalCount;
	
	
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public ResultSet getData() {
		return data;
	}
	public void setData(ResultSet data) {
		this.data = data;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
}
