
package com.huan.HTed.mybatis.common.condition;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.UpdateProvider;

import com.huan.HTed.mybatis.provider.ConditionProvider;

/**
 * 通用Mapper接口,Condition查询
 *
 * @param <T> 不能为空
 */
public interface UpdateByConditionSelectiveMapper<T> {

	  /**
     * 根据Condition条件更新实体`record`包含的不是null的属性值
     *
     * @param record
     * @param condition
     * @return
     */
    @UpdateProvider(type = ConditionProvider.class, method = "dynamicSQL")
    int updateByConditionSelective(@Param("record") T record, @Param("example") Object condition);

}