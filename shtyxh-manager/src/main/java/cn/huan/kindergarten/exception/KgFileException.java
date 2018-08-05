package cn.huan.kindergarten.exception;

import com.huan.HTed.core.exception.BaseException;

public class KgFileException extends BaseException {

	public KgFileException(String code, String descriptionKey, Object[] parameters) {
		super(code, descriptionKey, parameters);
	}

}
