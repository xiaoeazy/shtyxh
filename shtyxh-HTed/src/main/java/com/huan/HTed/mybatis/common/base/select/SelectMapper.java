
package com.huan.HTed.mybatis.common.base.select;

import org.apache.ibatis.annotations.SelectProvider;

import com.huan.HTed.mybatis.provider.base.BaseSelectProvider;

import java.util.List;

/**
 * 通用Mapper接口,查询
 */
public interface SelectMapper<T> {

	/**
     * 根据实体中的属性值进行查询，查询条件使用等号
     *
     * @param record
     * @return
     */
    @SelectProvider(type = BaseSelectProvider.class, method = "dynamicSQL")
    List<T> select(T record);

}