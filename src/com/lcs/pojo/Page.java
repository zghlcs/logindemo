package com.lcs.pojo;

import java.util.List;

public class Page {
	private int pageNum;
	private int totalCount;
	private int totalPageCount;
	private int numPerPage;
	private List<User> list;
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPageCount() {
		return totalPageCount;
	}
	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	public int getNumPerPage() {
		return numPerPage;
	}
	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}
	public List<User> getList() {
		return list;
	}
	public void setList(List<User> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "Page [pageNum=" + pageNum + ", totalCount=" + totalCount + ", totalPageCount=" + totalPageCount
				+ ", numPerPage=" + numPerPage + ", list=" + list + "]";
	}
	
}
