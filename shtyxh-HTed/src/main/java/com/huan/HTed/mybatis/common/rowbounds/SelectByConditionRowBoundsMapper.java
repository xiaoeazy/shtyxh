

package com.huan.HTed.mybatis.common.rowbounds;

import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.session.RowBounds;

import com.huan.HTed.mybatis.provider.ConditionProvider;

import java.util.List;

/**
 * 通用Mapper接口,Condition查询
 *
 * @param <T> 不能为空
 */
public interface SelectByConditionRowBoundsMapper<T> {

	 /**
     * 根据example条件和RowBounds进行分页查询，该方法和selectByExampleAndRowBounds完全一样，只是名字改成了Condition
     *
     * @param condition
     * @param rowBounds
     * @return
     */
    @SelectProvider(type = ConditionProvider.class, method = "dynamicSQL")
    List<T> selectByConditionAndRowBounds(Object condition, RowBounds rowBounds);

}