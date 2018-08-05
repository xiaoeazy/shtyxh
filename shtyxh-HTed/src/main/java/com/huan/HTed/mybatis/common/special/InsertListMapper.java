
package com.huan.HTed.mybatis.common.special;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;

import com.huan.HTed.mybatis.provider.SpecialProvider;

import java.util.List;

/**
 * 通用Mapper接口,特殊方法，批量插入，支持批量插入的数据库都可以使用，例如mysql,h2�?
 *
 * @param <T> 不能为空
 */
public interface InsertListMapper<T> {

	 /**
     * 批量插入，支持批量插入的数据库可以使用，例如MySQL,H2等，另外该接口限制实体包含`id`属性并且必须为自增列
     *
     * @param recordList
     * @return
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @InsertProvider(type = SpecialProvider.class, method = "dynamicSQL")
    int insertList(List<T> recordList);

    /**
     * ======如果主键不是id怎么用？==========
     * 假设主键的属性名是uid,那么新建�?个Mapper接口如下
     * <pre>
        public interface InsertUidListMapper<T> {
            @Options(useGeneratedKeys = true, keyProperty = "uid")
            @InsertProvider(type = SpecialProvider.class, method = "dynamicSQL")
            int insertList(List<T> recordList);
        }
     * 只要修改keyProperty = "uid"就可�?
     *
     * 然后让你自己的Mapper继承InsertUidListMapper<T>即可
     *
     * </pre>
     */
}