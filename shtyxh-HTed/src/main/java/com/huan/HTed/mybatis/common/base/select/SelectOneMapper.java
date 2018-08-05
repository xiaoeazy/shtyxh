
package com.huan.HTed.mybatis.common.base.select;

import org.apache.ibatis.annotations.SelectProvider;

import com.huan.HTed.mybatis.provider.base.BaseSelectProvider;

/**
 * 通用Mapper接口,查询
 */
public interface SelectOneMapper<T> {

	/**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常，查询条件使用等号
     *
     * @param record
     * @return
     */
    @SelectProvider(type = BaseSelectProvider.class, method = "dynamicSQL")
    T selectOne(T record);

}