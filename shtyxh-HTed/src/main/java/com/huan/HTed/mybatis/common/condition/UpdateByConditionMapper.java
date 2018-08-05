
package com.huan.HTed.mybatis.common.condition;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.UpdateProvider;

import com.huan.HTed.mybatis.provider.ConditionProvider;

/**
 * 通用Mapper接口,Condition查询
 *
 * @param <T> 不能为空
 */
public interface UpdateByConditionMapper<T> {

	 /**
     * 根据Condition条件更新实体`record`包含的全部属性，null值会被更新
     *
     * @param record
     * @param condition
     * @return
     */
    @UpdateProvider(type = ConditionProvider.class, method = "dynamicSQL")
    int updateByCondition(@Param("record") T record, @Param("example") Object condition);

}