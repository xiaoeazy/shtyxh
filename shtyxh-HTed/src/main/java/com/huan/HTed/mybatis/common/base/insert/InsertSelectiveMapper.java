

package com.huan.HTed.mybatis.common.base.insert;

import org.apache.ibatis.annotations.InsertProvider;

import com.huan.HTed.mybatis.provider.base.BaseInsertProvider;

/**
 * 通用Mapper接口,插入
 *
 * @param <T> 不能为空
 */
public interface InsertSelectiveMapper<T> {

	/**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     *
     * @param record
     * @return
     */
    @InsertProvider(type = BaseInsertProvider.class, method = "dynamicSQL")
    int insertSelective(T record);

}