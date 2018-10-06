package com.shtyxh.manager.bean;

public class NewsSearchVo {
	private String searchparam;
	private String dateissuestart;
	private String dateissueend;
	private Long newstype;
	private Long newssource;
	public String getSearchparam() {
		return searchparam;
	}
	public void setSearchparam(String searchparam) {
		this.searchparam = searchparam;
	}
	public String getDateissuestart() {
		return dateissuestart;
	}
	public void setDateissuestart(String dateissuestart) {
		this.dateissuestart = dateissuestart;
	}
	public String getDateissueend() {
		return dateissueend;
	}
	public void setDateissueend(String dateissueend) {
		this.dateissueend = dateissueend;
	}
	public Long getNewstype() {
		return newstype;
	}
	public void setNewstype(Long newstype) {
		this.newstype = newstype;
	}
	public Long getNewssource() {
		return newssource;
	}
	public void setNewssource(Long newssource) {
		this.newssource = newssource;
	}
	
}
