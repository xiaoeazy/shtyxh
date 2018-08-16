package cn.huan.kindergarten.exception;

import com.huan.HTed.core.exception.Base404Exception;

public class E404Excetion extends Base404Exception {

	protected E404Excetion(String code, String descriptionKey, Object[] parameters) {
		super(code, descriptionKey, parameters);
		// TODO Auto-generated constructor stub
	}
	
	public E404Excetion( String descriptionKey) {
		super(null, descriptionKey, null);
		// TODO Auto-generated constructor stub
	}

}
