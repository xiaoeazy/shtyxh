
package com.huan.HTed.mybatis.common.sqlserver;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;

import com.huan.HTed.mybatis.provider.SqlServerProvider;

/**
 * 通用Mapper接口,插入
 *
 * @param <T> 不能为空
 */
public interface InsertSelectiveMapper<T> {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @InsertProvider(type = SqlServerProvider.class, method = "dynamicSQL")
    int insertSelective(T record);

}