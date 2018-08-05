
package com.huan.HTed.mybatis.util;

import java.lang.reflect.Field;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.huan.HTed.mybatis.entity.Example;
import com.huan.HTed.mybatis.entity.IDynamicTableName;
import com.huan.HTed.system.dto.BaseDTO;
import com.huan.HTed.system.dto.DTOClassInfo;

/**
 * OGNL静态方法
 */
public abstract class OGNL {

    /**
     * 是否包含自定义查询列
     *
     * @param parameter
     * @return
     */
    public static boolean hasSelectColumns(Object parameter) {
        if (parameter != null && parameter instanceof Example) {
            Example example = (Example) parameter;
            if (example.getSelectColumns() != null && example.getSelectColumns().size() > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 不包含自定义查询列
     *
     * @param parameter
     * @return
     */
    public static boolean hasNoSelectColumns(Object parameter) {
        return !hasSelectColumns(parameter);
    }

    /**
     * 判断参数是否支持动态表名
     *
     * @param parameter
     * @return true支持，false不支持
     */
    public static boolean isDynamicParameter(Object parameter) {
        if (parameter != null && parameter instanceof IDynamicTableName) {
            return true;
        }
        return false;
    }

    /**
     * 判断参数是否b支持动态表名
     *
     * @param parameter
     * @return true不支持，false支持
     */
    public static boolean isNotDynamicParameter(Object parameter) {
        return !isDynamicParameter(parameter);
    }

    private static final Pattern COL_PATTERN = Pattern.compile("[\\d\\w_]+");

    /**
     * FOR INTERNAL USE ONLY
     * 
     * @param parameter
     * @return
     */
    public static String getOrderByClause(Object parameter) {
        if (parameter == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(64);
        if (parameter instanceof BaseDTO) {
            String sortName = ((BaseDTO) parameter).getSortname();
            Field[] ids = DTOClassInfo.getIdFields(parameter.getClass());
            if (StringUtil.isNotEmpty(sortName)) {
                if (!COL_PATTERN.matcher(sortName).matches()) {
                    throw new RuntimeException("Invalid sortname:" + sortName);
                }
                String order = ((BaseDTO) parameter).getSortorder();
                if (!("ASC".equalsIgnoreCase(order) || "DESC".equalsIgnoreCase(order) || order == null)) {
                    throw new RuntimeException("Invalid sortorder:" + order);
                }
                String columnName = unCamel(sortName);

                sb.append(columnName).append(" ");
                sb.append(StringUtils.defaultIfEmpty(order, "ASC"));

                if (ids.length > 0 && !ids[0].getName().equals(sortName)) {
                    sb.append(",").append(DTOClassInfo.getColumnName(ids[0])).append(" ASC");
                }
            } else {
                if (ids.length > 0) {
                    sb.append(DTOClassInfo.getColumnName(ids[0])).append(" ASC");
                }
            }
        }
        return StringUtils.trimToNull(sb.toString());
    }

    /**
     * FOR INTERNAL USE ONLY
     * 
     * @param parameter
     * @return
     */
//    public static String getOrderByClause_TL(Object parameter) {
//        if (parameter == null) {
//            return null;
//        }
//        StringBuilder sb = new StringBuilder(64);
//        if (parameter instanceof BaseDTO) {
//            String sortName = ((BaseDTO) parameter).getSortname();
//            Field[] ids = DTOClassInfo.getIdFields(parameter.getClass());
//            if (StringUtil.isNotEmpty(sortName)) {
//                if (!COL_PATTERN.matcher(sortName).matches()) {
//                    throw new RuntimeException("Invalid sortname:" + sortName);
//                }
//                String order = ((BaseDTO) parameter).getSortorder();
//                if (!("ASC".equalsIgnoreCase(order) || "DESC".equalsIgnoreCase(order) || order == null)) {
//                    throw new RuntimeException("Invalid sortorder:" + order);
//                }
//                String columnName = unCamel(sortName);
//                Field[] mlfs = DTOClassInfo.getMultiLanguageFields(parameter.getClass());
//                for (Field f : mlfs) {
//                    if (f.getName().equals(columnName)) {
//                        if (f.getAnnotation(MultiLanguageField.class) == null) {
//                            sb.append("b.");
//                        } else {
//                            sb.append("t.");
//                        }
//                        break;
//                    }
//                }
//
//                sb.append(columnName).append(" ");
//                sb.append(StringUtils.defaultIfEmpty(order, "ASC"));
//
//                if (ids.length > 0 && !ids[0].getName().equals(sortName)) {
//                    sb.append(",b.").append(DTOClassInfo.getColumnName(ids[0])).append(" ASC");
//                }
//            } else {
//                if (ids.length > 0) {
//                    sb.append("b.").append(DTOClassInfo.getColumnName(ids[0])).append(" ASC");
//                }
//            }
//        }
//        return StringUtils.trimToNull(sb.toString());
//    }

    public static String unCamel(String str) {
        return DTOClassInfo.camelToUnderLine(str);
    }
}
