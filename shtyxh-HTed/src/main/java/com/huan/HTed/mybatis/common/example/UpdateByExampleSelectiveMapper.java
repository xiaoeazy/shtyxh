
package com.huan.HTed.mybatis.common.example;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.UpdateProvider;

import com.huan.HTed.mybatis.provider.ExampleProvider;

/**
 * 通用Mapper接口,Example查询
 *
 * @param <T> 不能为空
 */
public interface UpdateByExampleSelectiveMapper<T> {

	/**
     * 根据Example条件更新实体`record`包含的不是null的属性值
     *
     * @param record
     * @param example
     * @return
     */
    @UpdateProvider(type = ExampleProvider.class, method = "dynamicSQL")
    int updateByExampleSelective(@Param("record") T record, @Param("example") Object example);

}