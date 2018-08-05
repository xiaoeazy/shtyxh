
package com.huan.HTed.mybatis.common.base;

import com.huan.HTed.mybatis.common.base.update.UpdateByPrimaryKeyMapper;
import com.huan.HTed.mybatis.common.base.update.UpdateByPrimaryKeySelectiveMapper;

/**
 * 通用Mapper接口,基础查询
 *
 * @param <T> 不能为空
 */
public interface BaseUpdateMapper<T> extends
        UpdateByPrimaryKeyMapper<T>,
        UpdateByPrimaryKeySelectiveMapper<T> {

}