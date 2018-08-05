package com.huan.HTed.bean;

public class UploadImgAjax {
	private boolean success =true;
	private boolean pass;
	private String message;
	private String file;
	
	
	public UploadImgAjax(boolean pass, String message, String file) {
		super();
		this.pass = pass;
		this.message = message;
		this.file = file;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	
	public boolean isPass() {
		return pass;
	}
	public void setPass(boolean pass) {
		this.pass = pass;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	
	
}
