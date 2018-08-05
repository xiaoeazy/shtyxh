

package com.huan.HTed.mybatis.provider.base;

import org.apache.ibatis.mapping.MappedStatement;

import com.huan.HTed.mybatis.mapperhelper.EntityHelper;
import com.huan.HTed.mybatis.mapperhelper.MapperHelper;
import com.huan.HTed.mybatis.mapperhelper.MapperTemplate;
import com.huan.HTed.mybatis.mapperhelper.SqlHelper;

/**
 * BaseSelectProvider实现类，基础方法实现类
 */
public class BaseSelectProvider extends MapperTemplate {

    public BaseSelectProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    /**
     * 查询
     *
     * @param ms
     * @return
     */
    public String selectOne(MappedStatement ms) {
        Class<?> entityClass = getEntityClass(ms);
        boolean isMl = EntityHelper.getEntityTable(entityClass).isSupportMultiLanguage();

        //修改返回值类型为实体类型
        setResultType(ms, entityClass);
        StringBuilder sql = new StringBuilder();
        if (isMl) {
            sql.append(SqlHelper.selectAllColumns_TL(entityClass));
            sql.append(SqlHelper.fromTable_TL(entityClass, tableName(entityClass)));
            sql.append(SqlHelper.whereAllIfColumns_TL(entityClass, isNotEmpty()));
        } else {
            sql.append(SqlHelper.selectAllColumns(entityClass));
            sql.append(SqlHelper.fromTable(entityClass, tableName(entityClass)));
            sql.append(SqlHelper.whereAllIfColumns(entityClass, isNotEmpty()));
        }
        return sql.toString();
    }

    /**
     * 查询
     *
     * @param ms
     * @return
     */
    public String select(MappedStatement ms) {
        Class<?> entityClass = getEntityClass(ms);

        boolean isMl = EntityHelper.getEntityTable(entityClass).isSupportMultiLanguage();

        //修改返回值类型为实体类型
        setResultType(ms, entityClass);
        StringBuilder sql = new StringBuilder();
        if (isMl) {
            sql.append(SqlHelper.selectAllColumns_TL(entityClass));
            sql.append(SqlHelper.fromTable_TL(entityClass, tableName(entityClass)));
            sql.append(SqlHelper.whereAllIfColumns_TL(entityClass, isNotEmpty()));
            sql.append(SqlHelper.orderByDefault_TL(entityClass));
        } else {
            sql.append(SqlHelper.selectAllColumns(entityClass));
            sql.append(SqlHelper.fromTable(entityClass, tableName(entityClass)));
            sql.append(SqlHelper.whereAllIfColumns(entityClass, isNotEmpty()));
            sql.append(SqlHelper.orderByDefault(entityClass));
        }
        return sql.toString();
    }

    /**
     * 查询
     *
     * @param ms
     * @return
     */
    public String selectByRowBounds(MappedStatement ms) {
        return select(ms);
    }

    /**
     * 根据主键进行查询
     *
     * @param ms
     */
    public String selectByPrimaryKey(MappedStatement ms) {
        final Class<?> entityClass = getEntityClass(ms);

        boolean isMl = EntityHelper.getEntityTable(entityClass).isSupportMultiLanguage();

        //将返回值修改为实体类型
        setResultType(ms, entityClass);
        StringBuilder sql = new StringBuilder();
        if (isMl) {
            sql.append(SqlHelper.selectAllColumns_TL(entityClass));
            sql.append(SqlHelper.fromTable_TL(entityClass, tableName(entityClass)));
            sql.append(SqlHelper.wherePKColumns_TL(entityClass));
        } else {
            sql.append(SqlHelper.selectAllColumns(entityClass));
            sql.append(SqlHelper.fromTable(entityClass, tableName(entityClass)));
            sql.append(SqlHelper.wherePKColumns(entityClass));
        }
        return sql.toString();
    }

    /**
     * 查询总数
     *
     * @param ms
     * @return
     */
    public String selectCount(MappedStatement ms) {
        Class<?> entityClass = getEntityClass(ms);
        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.selectCount(entityClass));
        sql.append(SqlHelper.fromTable(entityClass, tableName(entityClass)));
        sql.append(SqlHelper.whereAllIfColumns(entityClass, isNotEmpty()));
        return sql.toString();
    }

    /**
     * 查询全部结果
     *
     * @param ms
     * @return
     */
    public String selectAll(MappedStatement ms) {
        final Class<?> entityClass = getEntityClass(ms);

        boolean isMl = EntityHelper.getEntityTable(entityClass).isSupportMultiLanguage();

        //修改返回值类型为实体类型
        setResultType(ms, entityClass);
        StringBuilder sql = new StringBuilder();
        if (isMl) {
            sql.append(SqlHelper.selectAllColumns_TL(entityClass));
            sql.append(SqlHelper.fromTable_TL(entityClass, tableName(entityClass)));
            sql.append(SqlHelper.orderByDefault_TL(entityClass));
        } else {
            sql.append(SqlHelper.selectAllColumns(entityClass));
            sql.append(SqlHelper.fromTable(entityClass, tableName(entityClass)));
            sql.append(SqlHelper.orderByDefault(entityClass));
        }
        return sql.toString();
    }
}
