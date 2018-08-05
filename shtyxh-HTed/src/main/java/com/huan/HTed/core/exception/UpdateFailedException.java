package com.huan.HTed.core.exception;

import com.huan.HTed.system.dto.BaseDTO;

/**
 * 通用更新操作,更新失败:记录不存在,OBJECT_VERSION_NUMBER 不匹配。
 *
 * @author huanghuan
 */
public class UpdateFailedException extends BaseException {

    public static final String MESSAGE_KEY = "记录不存在或者版本不匹配";

    protected UpdateFailedException(String code, String descriptionKey, Object[] parameters) {
        super(code, descriptionKey, parameters);
    }

    public UpdateFailedException() {
        this("SYS", MESSAGE_KEY, null);
    }

    public UpdateFailedException(BaseDTO record) {
        this("SYS", MESSAGE_KEY, new Object[] { record.get__id() });
    }
}
