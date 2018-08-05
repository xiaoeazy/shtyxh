
package com.huan.HTed.mybatis.common;

import com.huan.HTed.mybatis.common.base.BaseDeleteMapper;
import com.huan.HTed.mybatis.common.base.BaseInsertMapper;
import com.huan.HTed.mybatis.common.base.BaseSelectMapper;
import com.huan.HTed.mybatis.common.base.BaseUpdateMapper;

/**
 * 通用Mapper接口,其他接口继承该接口即可
 * @param <T> 不能为空
 */
public interface BaseMapper<T> extends
        BaseSelectMapper<T>,
        BaseInsertMapper<T>,
        BaseUpdateMapper<T>,
        BaseDeleteMapper<T> {

}