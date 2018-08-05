
package com.huan.HTed.mybatis.common;

/**
 * 通用Mapper接口,其他接口继承该接口即可
 * @param <T> 不能为空
 */
public interface Mapper<T> extends
        BaseMapper<T>,
        ExampleMapper<T>,
        RowBoundsMapper<T>,
        Marker {

}