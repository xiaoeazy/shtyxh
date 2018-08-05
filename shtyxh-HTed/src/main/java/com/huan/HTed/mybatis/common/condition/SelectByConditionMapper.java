

package com.huan.HTed.mybatis.common.condition;

import org.apache.ibatis.annotations.SelectProvider;

import com.huan.HTed.mybatis.provider.ConditionProvider;

import java.util.List;

/**
 * 通用Mapper接口,Condition查询
 *
 */
public interface SelectByConditionMapper<T> {

    /**
     * 根据Condition条件进行查询
     *
     * @param condition
     * @return
     */
    @SelectProvider(type = ConditionProvider.class, method = "dynamicSQL")
    List<T> selectByCondition(Object condition);

}