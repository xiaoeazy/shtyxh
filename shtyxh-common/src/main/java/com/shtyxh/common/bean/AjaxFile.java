package com.shtyxh.common.bean;

import java.util.ArrayList;
import java.util.List;

public class AjaxFile {
	private String message ;
	private Boolean success;
	private List<UploadFileInfo> list = new ArrayList<UploadFileInfo>();
	
	
	public AjaxFile() {
		super();
	}
	

	public AjaxFile(Boolean success) {
		super();
		this.success = success;
	}


	public AjaxFile(Boolean success,String message,  List<UploadFileInfo> list) {
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
	public List<UploadFileInfo> getList() {
		return list;
	}
	public void setList(List<UploadFileInfo> list) {
		this.list = list;
	}
	
	
}
