
package com.huan.HTed.mybatis.common.base;

import com.huan.HTed.mybatis.common.base.select.SelectAllMapper;
import com.huan.HTed.mybatis.common.base.select.SelectByPrimaryKeyMapper;
import com.huan.HTed.mybatis.common.base.select.SelectCountMapper;
import com.huan.HTed.mybatis.common.base.select.SelectMapper;
import com.huan.HTed.mybatis.common.base.select.SelectOneMapper;

/**
 * 通用Mapper接口,基础查询
 *
 * @param <T> 不能为空
 */
public interface BaseSelectMapper<T> extends
        SelectOneMapper<T>,
        SelectMapper<T>,
        SelectAllMapper<T>,
        SelectCountMapper<T>,
        SelectByPrimaryKeyMapper<T> {

}