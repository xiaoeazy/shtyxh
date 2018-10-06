package com.shtyxh.common.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ExtAjax {
	 @JsonInclude(Include.NON_NULL)
	    private Boolean success;
	 
	 @JsonInclude(Include.NON_NULL)
	    private String message;
	 
	public ExtAjax(Boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	 
	 
}
