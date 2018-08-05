

package com.huan.HTed.mybatis.provider.base;

import org.apache.ibatis.mapping.MappedStatement;

import com.huan.HTed.mybatis.mapperhelper.MapperHelper;
import com.huan.HTed.mybatis.mapperhelper.MapperTemplate;
import com.huan.HTed.mybatis.mapperhelper.SqlHelper;

/**
 * BaseDeleteMapper实现类，基础方法实现类
 */
public class BaseDeleteProvider extends MapperTemplate {

    public BaseDeleteProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    /**
     * 通过条件删除
     *
     * @param ms
     * @return
     */
    public String delete(MappedStatement ms) {
        Class<?> entityClass = getEntityClass(ms);
        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.deleteFromTable(entityClass, tableName(entityClass)));
        sql.append(SqlHelper.whereAllIfColumns(entityClass, isNotEmpty()));
        return sql.toString();
    }

    /**
     * 通过主键删除
     *
     * @param ms
     */
    public String deleteByPrimaryKey(MappedStatement ms) {
        final Class<?> entityClass = getEntityClass(ms);
        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.deleteFromTable(entityClass, tableName(entityClass)));
        sql.append(SqlHelper.wherePKColumns(entityClass));
        BaseUpdateProvider.appendObjectVersionNumber(sql, entityClass);
        return sql.toString();
    }
}
