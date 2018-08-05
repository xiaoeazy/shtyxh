
package com.huan.HTed.mybatis.common;

import com.huan.HTed.mybatis.common.special.InsertListMapper;
import com.huan.HTed.mybatis.common.special.InsertUseGeneratedKeysMapper;

/**
 * 通用Mapper接口,MySql独有的通用方法
 *
 * @param <T> 不能为空
 */
public interface MySqlMapper<T> extends
        InsertListMapper<T>,
        InsertUseGeneratedKeysMapper<T> {

}