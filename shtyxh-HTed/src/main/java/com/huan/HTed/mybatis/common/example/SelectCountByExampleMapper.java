

package com.huan.HTed.mybatis.common.example;

import org.apache.ibatis.annotations.SelectProvider;

import com.huan.HTed.mybatis.provider.ExampleProvider;

/**
 * 通用Mapper接口,Example查询
 *
 */
public interface SelectCountByExampleMapper<T> {

    /**
     * 根据Example条件进行查询总数
     *
     * @param example
     * @return
     */
    @SelectProvider(type = ExampleProvider.class, method = "dynamicSQL")
    int selectCountByExample(Object example);

}