
package com.huan.HTed.mybatis.common.base.select;

import org.apache.ibatis.annotations.SelectProvider;

import com.huan.HTed.mybatis.provider.base.BaseSelectProvider;

import java.util.List;

public interface SelectAllMapper<T> {

    /**
     * 查询全部结果
     *
     * @return
     */
    @SelectProvider(type = BaseSelectProvider.class, method = "dynamicSQL")
    List<T> selectAll();
}
