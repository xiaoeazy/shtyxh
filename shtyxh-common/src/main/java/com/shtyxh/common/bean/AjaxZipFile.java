package com.shtyxh.common.bean;

import java.util.ArrayList;
import java.util.List;

public class AjaxZipFile {
	private String message ;
	private Boolean success;
	private List<ZipFile> list = new ArrayList<ZipFile>();
	
	
	public AjaxZipFile() {
		super();
	}
	

	public AjaxZipFile(Boolean success) {
		super();
		this.success = success;
	}


	public AjaxZipFile(Boolean success,String message,  List<ZipFile> list) {
		super();
		this.message = message;
		this.success = success;
		this.list = list;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public List<ZipFile> getList() {
		return list;
	}
	public void setList(List<ZipFile> list) {
		this.list = list;
	}
	
	
}
