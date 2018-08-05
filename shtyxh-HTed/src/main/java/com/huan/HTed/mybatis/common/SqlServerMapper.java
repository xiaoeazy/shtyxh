
package com.huan.HTed.mybatis.common;


import com.huan.HTed.mybatis.common.sqlserver.InsertMapper;
import com.huan.HTed.mybatis.common.sqlserver.InsertSelectiveMapper;

/**
 * 通用Mapper接口,SqlServerMapper独有的通用方法
 *
 * @param <T> 不能为空
 */
public interface SqlServerMapper<T> extends
        InsertMapper<T>,
        InsertSelectiveMapper<T> {

}