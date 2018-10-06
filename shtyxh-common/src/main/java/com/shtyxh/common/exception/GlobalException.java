package com.shtyxh.common.exception;

public class GlobalException extends RuntimeException {

	public GlobalException(){
		super();
	}
	
	public GlobalException(String descriptionKey){
		super(descriptionKey);
	}
}
