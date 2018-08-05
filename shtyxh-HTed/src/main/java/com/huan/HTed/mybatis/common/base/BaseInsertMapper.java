
package com.huan.HTed.mybatis.common.base;

import com.huan.HTed.mybatis.common.base.insert.InsertMapper;
import com.huan.HTed.mybatis.common.base.insert.InsertSelectiveMapper;

/**
 * 通用Mapper接口,基础查询
 *
 * @param <T> 不能为空
 */
public interface BaseInsertMapper<T> extends
        InsertMapper<T>,
        InsertSelectiveMapper<T> {

}