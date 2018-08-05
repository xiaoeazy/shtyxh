
package com.huan.HTed.mybatis.common.base.select;

import org.apache.ibatis.annotations.SelectProvider;

import com.huan.HTed.mybatis.provider.base.BaseSelectProvider;

/**
 * 通用Mapper接口,查询
 */
public interface SelectCountMapper<T> {

	/**
     * 根据实体中的属性查询总数，查询条件使用等号
     *
     * @param record
     * @return
     */
    @SelectProvider(type = BaseSelectProvider.class, method = "dynamicSQL")
    int selectCount(T record);

}