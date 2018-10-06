package com.shtyxh.common.exception;

public class ESessionExcetion extends GlobalException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String type ;
	
	public ESessionExcetion( String descriptionKey,String type) {
		super(descriptionKey);
		this.type = type;
		// TODO Auto-generated constructor stub
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
