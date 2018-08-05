

package com.huan.HTed.mybatis.common.special;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;

import com.huan.HTed.mybatis.provider.SpecialProvider;

/**
 * 通用Mapper接口,特殊方法，批量插入，支持批量插入的数据库都可以使用，例如mysql,h2等
 *
 * @param <T> 不能为空
 */
public interface InsertUseGeneratedKeysMapper<T> {

	/**
     * 插入数据，限制为实体包含`id`属性并且必须为自增列，实体配置的主键策略无效
     *
     * @param record
     * @return
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @InsertProvider(type = SpecialProvider.class, method = "dynamicSQL")
    int insertUseGeneratedKeys(T record);

    /**
     * ======如果主键不是id怎么用？==========
     * 假设主键的属性名是uid,那么新建�?个Mapper接口如下
     * <pre>
        public interface InsertUidMapper<T> {
            @Options(useGeneratedKeys = true, keyProperty = "id")
            @InsertProvider(type = SpecialProvider.class, method = "dynamicSQL")
            int insertUseGeneratedKeys(T record);
        }
     * 只要修改keyProperty = "uid"就可�?
     *
     * 然后让你自己的Mapper继承InsertUidListMapper<T>即可
     *
     * </pre>
     */
}