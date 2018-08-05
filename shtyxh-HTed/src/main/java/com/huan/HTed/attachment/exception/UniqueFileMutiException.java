
package com.huan.HTed.attachment.exception;

import com.huan.HTed.core.exception.BaseException;


public class UniqueFileMutiException extends BaseException {

    private static final long serialVersionUID = 9046687211507280533L;
    
    /**
     * 此分类下有多个文件异常.
     */
    private static final String FILE_MUTI_ERROR = "此分类下有多个文件异常";

    /**
     * 构造函数.
     */
    public UniqueFileMutiException() {
        super(FILE_MUTI_ERROR, FILE_MUTI_ERROR, new Object[0]);
    }
}
