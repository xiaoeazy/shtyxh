

package com.huan.HTed.mybatis.common.base.update;

import org.apache.ibatis.annotations.UpdateProvider;

import com.huan.HTed.mybatis.provider.base.BaseUpdateProvider;

/**
 * 通用Mapper接口,更新
 *
 */
public interface UpdateByPrimaryKeyMapper<T> {

	/**
     * 根据主键更新实体全部字段，null值会被更新
     *
     * @param record
     * @return
     */
    @UpdateProvider(type = BaseUpdateProvider.class, method = "dynamicSQL")
    int updateByPrimaryKey(T record);

}