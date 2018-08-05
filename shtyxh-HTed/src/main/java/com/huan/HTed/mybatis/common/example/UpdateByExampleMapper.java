
package com.huan.HTed.mybatis.common.example;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.UpdateProvider;

import com.huan.HTed.mybatis.provider.ExampleProvider;

/**
 * 通用Mapper接口,Example查询
 *
 */
public interface UpdateByExampleMapper<T> {

	/**
     * 根据Example条件更新实体`record`包含的全部属性，null值会被更新
     *
     * @param record
     * @param example
     * @return
     */
    @UpdateProvider(type = ExampleProvider.class, method = "dynamicSQL")
    int updateByExample(@Param("record") T record, @Param("example") Object example);

}