
package com.huan.HTed.mybatis.common;

import com.huan.HTed.mybatis.common.example.DeleteByExampleMapper;
import com.huan.HTed.mybatis.common.example.SelectByExampleMapper;
import com.huan.HTed.mybatis.common.example.SelectCountByExampleMapper;
import com.huan.HTed.mybatis.common.example.UpdateByExampleMapper;
import com.huan.HTed.mybatis.common.example.UpdateByExampleSelectiveMapper;

/**
 * 通用Mapper接口,Example查询
 *
 * @param <T> 不能为空
 */
public interface ExampleMapper<T> extends
        SelectByExampleMapper<T>,
        SelectCountByExampleMapper<T>,
        DeleteByExampleMapper<T>,
        UpdateByExampleMapper<T>,
        UpdateByExampleSelectiveMapper<T> {

}