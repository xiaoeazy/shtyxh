
package cn.huan.kindergarten.exception;

import com.huan.HTed.core.exception.BaseException;

public class FileReadIOException extends BaseException {

    private static final long serialVersionUID = 9046687211507280533L;
    
    /**
     * 发生了IO异常.
     */
    private static final String ATTACH_FILE_IO_ERROR = "文件异常";

    public FileReadIOException() {
        super(ATTACH_FILE_IO_ERROR, ATTACH_FILE_IO_ERROR, new Object[0]);
    }
}
