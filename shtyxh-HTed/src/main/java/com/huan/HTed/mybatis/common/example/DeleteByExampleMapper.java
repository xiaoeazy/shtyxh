

package com.huan.HTed.mybatis.common.example;

import org.apache.ibatis.annotations.DeleteProvider;

import com.huan.HTed.mybatis.provider.ExampleProvider;

/**
 * 通用Mapper接口,Example查询
 *
 * @param <T> 不能为空
 */
public interface DeleteByExampleMapper<T> {

    /**
     * 根据Example条件删除数据
     *
     * @param example
     * @return
     */
    @DeleteProvider(type = ExampleProvider.class, method = "dynamicSQL")
    int deleteByExample(Object example);

}