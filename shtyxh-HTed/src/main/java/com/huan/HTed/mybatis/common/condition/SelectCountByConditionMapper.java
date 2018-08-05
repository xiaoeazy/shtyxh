

package com.huan.HTed.mybatis.common.condition;

import org.apache.ibatis.annotations.SelectProvider;

import com.huan.HTed.mybatis.provider.ConditionProvider;

/**
 * 通用Mapper接口,Condition查询
 *
 * @param <T> 不能为空
 */
public interface SelectCountByConditionMapper<T> {

    /**
     * 根据Condition条件进行查询总数
     *
     * @param condition
     * @return
     */
    @SelectProvider(type = ConditionProvider.class, method = "dynamicSQL")
    int selectCountByCondition(Object condition);

}