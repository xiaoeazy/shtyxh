
package com.huan.HTed.mybatis.provider.base;

import org.apache.ibatis.mapping.MappedStatement;

import com.huan.HTed.mybatis.mapperhelper.MapperHelper;
import com.huan.HTed.mybatis.mapperhelper.MapperTemplate;
import com.huan.HTed.mybatis.mapperhelper.SqlHelper;
import com.huan.HTed.system.dto.BaseDTO;

/**
 * BaseUpdateProvider实现类，基础方法实现类
 */
public class BaseUpdateProvider extends MapperTemplate {

    public BaseUpdateProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    /**
     * 通过主键更新全部字段
     *
     * @param ms
     */
    public String updateByPrimaryKey(MappedStatement ms) {
        Class<?> entityClass = getEntityClass(ms);
        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.updateTable(entityClass, tableName(entityClass)));
        sql.append(SqlHelper.updateSetColumns(entityClass, null, false, false));
        sql.append(SqlHelper.wherePKColumns(entityClass));
        appendObjectVersionNumber(sql, entityClass);
        return sql.toString();
    }

    /**
     * 通过主键更新不为null的字段
     *
     * @param ms
     * @return
     */
    public String updateByPrimaryKeySelective(MappedStatement ms) {
        Class<?> entityClass = getEntityClass(ms);
        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.updateTable(entityClass, tableName(entityClass)));
        sql.append(SqlHelper.updateSetColumns(entityClass, null, true, isNotEmpty()));
        sql.append(SqlHelper.wherePKColumns(entityClass));
        appendObjectVersionNumber(sql, entityClass);
        return sql.toString();
    }

    public static void appendObjectVersionNumber(StringBuilder sb, Class<?> entityClass) {
        if (!(BaseDTO.class.isAssignableFrom(entityClass))) {
            return;
        }
        sb.append("<if test=\"objectVersionNumber!=null\">");
        sb.append(" AND OBJECT_VERSION_NUMBER=#{objectVersionNumber,jdbcType=DECIMAL}");
        sb.append("</if>");
    }
}
