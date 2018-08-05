
package com.huan.HTed.mybatis.provider;

import com.huan.HTed.mybatis.mapperhelper.MapperHelper;
import com.huan.HTed.mybatis.mapperhelper.MapperTemplate;

/**
 * 空方法Mapper接口默认MapperTemplate
 */
public class EmptyProvider extends MapperTemplate {

    public EmptyProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    @Override
    public boolean supportMethod(String msId) {
        return false;
    }
}
