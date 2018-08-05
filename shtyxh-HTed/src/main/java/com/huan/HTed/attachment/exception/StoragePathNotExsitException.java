
package com.huan.HTed.attachment.exception;

import com.huan.HTed.core.exception.BaseException;


public class StoragePathNotExsitException extends BaseException {

    private static final long serialVersionUID = 9046687211507280533L;
    
    /**
     * 文件存储路径不存在.
     */
    private static final String STORAGE_PATH_NOT_EXSIT = "文件存储路径不存在";

    public StoragePathNotExsitException() {
        super(STORAGE_PATH_NOT_EXSIT, STORAGE_PATH_NOT_EXSIT, new Object[0]);
    }
}
