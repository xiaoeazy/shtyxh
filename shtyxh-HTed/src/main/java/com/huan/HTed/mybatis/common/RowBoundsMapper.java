

package com.huan.HTed.mybatis.common;

import com.huan.HTed.mybatis.common.rowbounds.SelectByExampleRowBoundsMapper;
import com.huan.HTed.mybatis.common.rowbounds.SelectRowBoundsMapper;

/**
 * 通用Mapper接口,带RowBounds参数的查询
 * @param <T> 不能为空
 */
public interface RowBoundsMapper<T> extends
        SelectByExampleRowBoundsMapper<T>,
        SelectRowBoundsMapper<T> {

}