

package com.huan.HTed.mybatis.spring;

import com.huan.HTed.mybatis.mapperhelper.MapperHelper;

/**
 * 增加mapperHelper
 *
 * @param <T>
 */
public class MapperFactoryBean<T> extends org.mybatis.spring.mapper.MapperFactoryBean<T> {

    private MapperHelper mapperHelper;

    public MapperFactoryBean() {
    }

    public MapperFactoryBean(Class<T> mapperInterface) {
        super(mapperInterface);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void checkDaoConfig() {
        super.checkDaoConfig();
        //通用Mapper
        if (mapperHelper.isExtendCommonMapper(getObjectType())) {
            mapperHelper.processConfiguration(getSqlSession().getConfiguration(), getObjectType());
        }
    }

    public void setMapperHelper(MapperHelper mapperHelper) {
        this.mapperHelper = mapperHelper;
    }
}
