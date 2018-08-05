
package com.huan.HTed.mybatis.common;


import com.huan.HTed.mybatis.common.condition.DeleteByConditionMapper;
import com.huan.HTed.mybatis.common.condition.SelectByConditionMapper;
import com.huan.HTed.mybatis.common.condition.SelectCountByConditionMapper;
import com.huan.HTed.mybatis.common.condition.UpdateByConditionMapper;
import com.huan.HTed.mybatis.common.condition.UpdateByConditionSelectiveMapper;

/**
 * 通用Mapper接口,Condition查询
 *
 * @author huanghuan
 */
public interface ConditionMapper<T> extends
        SelectByConditionMapper<T>,
        SelectCountByConditionMapper<T>,
        DeleteByConditionMapper<T>,
        UpdateByConditionMapper<T>,
        UpdateByConditionSelectiveMapper<T> {

}