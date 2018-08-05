
package com.huan.HTed.mybatis.common.base.delete;

import org.apache.ibatis.annotations.DeleteProvider;

import com.huan.HTed.mybatis.provider.base.BaseDeleteProvider;

/**
 * 通用Mapper接口,删除
 *
 * @param <T> 不能为空
 */
public interface DeleteByPrimaryKeyMapper<T> {

	   /**
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     *
     * @param key
     * @return
     */
    @DeleteProvider(type = BaseDeleteProvider.class, method = "dynamicSQL")
    int deleteByPrimaryKey(Object key);

}