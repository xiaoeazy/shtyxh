

package com.huan.HTed.mybatis.common.base.update;

import org.apache.ibatis.annotations.UpdateProvider;

import com.huan.HTed.mybatis.provider.base.BaseUpdateProvider;

/**
 * 通用Mapper接口,更新
 */
public interface UpdateByPrimaryKeySelectiveMapper<T> {

	/**
     * 根据主键更新属性不为null的值
     *
     * @param record
     * @return
     */
    @UpdateProvider(type = BaseUpdateProvider.class, method = "dynamicSQL")
    int updateByPrimaryKeySelective(T record);

}