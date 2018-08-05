
package com.huan.HTed.system.dto;

/**
 * @author huanghuan
 */
public final class DTOStatus {
    
    private DTOStatus() {
    }
    
    /**
     * Liger UI 记录状态 - 新增.
     */
    public static final String ADD = "add";
    
    /**
     * Liger UI 记录状态 - 更新.
     */
    public static final String UPDATE = "update";
    
    /**
     * Liger UI 记录状态 - 删除.
     */
    public static final String DELETE = "delete";
    /**
     * Liger UI 记录状态 - 不做操作.
     */
    public static final String NOTHING = "nothing";
}
