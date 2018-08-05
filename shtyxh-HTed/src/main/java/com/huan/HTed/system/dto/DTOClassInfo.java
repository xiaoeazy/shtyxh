

package com.huan.HTed.system.dto;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Id;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.ReflectionUtils;

import com.huan.HTed.core.annotation.Children;

/**
 * 获取,并缓存dto类中的字段信息.
 */
public final class DTOClassInfo {

    private static final Comparator<Field> FIELD_COMPARATOR = new Comparator<Field>()  {
        @Override
        public int compare(Field a, Field b) {
        	return a.getName().compareTo(b.getName());
        }
    };

    @SuppressWarnings("unchecked")
    /**
     * the order of this array can not be change. <br>
     * if more Annotations should be concerned, please add it to last
     */
//    private static final Class<? extends Annotation>[] CONCERNED_ANNOTATION = new Class[] { Id.class, Children.class,
//            MultiLanguageField.class };
    private static final Class<? extends Annotation>[] CONCERNED_ANNOTATION = new Class[] { Id.class, Children.class
           };
    @SuppressWarnings("unchecked")
    private static final Map<Class<?>, Field[]>[] CLASS_ANNO_MAPPING = new HashMap[CONCERNED_ANNOTATION.length];

    static {
        for (int i = 0; i < CLASS_ANNO_MAPPING.length; i++) {
            CLASS_ANNO_MAPPING[i] = new HashMap<>();
        }
    }

    private static final Map<String, String> CAMEL_UL_MAP = new HashMap<>();

    private DTOClassInfo() {
    }

    /**
     * 取得所有带有@Id注解的Field.
     * <p>
     * Field 已经被设置setAccessible(true)
     *
     * @param clazz
     *            类型
     * @return 有多个字段时,按照字母表顺序排序.没有匹配类型时,返回空数组,不返回null
     */
    public static Field[] getIdFields(Class<?> clazz) {
        return getFields0(clazz, 0);
    }

    /**
     * 取得所有带有@Children注解的Field.
     * <p>
     * Field 已经被设置setAccessible(true)
     * 
     * @param clazz
     *            类型
     * @return 有多个字段时,按照字母表顺序排序.没有匹配类型时,返回空数组,不返回null
     */
    public static Field[] getChildrenFields(Class<?> clazz) {
        return getFields0(clazz, 1);
    }

    /**
     * 取得所有带有@MultiLanguageField注解的Field.
     * <p>
     * Field 已经被设置setAccessible(true)
     *
     * @param clazz
     *            类型
     * @return 有多个字段时,按照字母表顺序排序.没有匹配类型时,返回空数组,不返回null
     */
    public static Field[] getMultiLanguageFields(Class<?> clazz) {
        return getFields0(clazz, 2);
    }

    private static Field[] getFields0(Class<?> clazz, int idx) {
        Field[] fields = CLASS_ANNO_MAPPING[idx].get(clazz);
        if (fields == null) {
            analysis(clazz);
            fields = CLASS_ANNO_MAPPING[idx].get(clazz);
        }
        return fields;
    }

    /**
     * 按照Annotation类型获取字段集.
     * 
     * @param clazz
     *            类型
     * @param annoType
     *            Annotation Type
     * @return Field array, return empty array if annoType is not support.
     */
    public static Field[] getFieldsOfAnnotation(Class<?> clazz, Class<? extends Annotation> annoType) {
        int idx = Arrays.asList(CONCERNED_ANNOTATION).indexOf(annoType);
        if (idx != -1) {
            return getFields0(clazz, idx);
        }
        return new Field[0];
    }

    @SuppressWarnings("unchecked")
    private static void analysis(Class<?> clazz) {
        final List<Field> fields = new ArrayList<>();
       // ReflectionUtils.doWithFields(clazz, fields::add);
        ReflectionUtils.doWithFields(clazz, new ReflectionUtils.FieldCallback() {
			
			@Override
			public void doWith(Field arg0) throws IllegalArgumentException,
					IllegalAccessException {
				fields.add(arg0);
				
			}
		});
        
        Collections.sort(fields, FIELD_COMPARATOR);
        List<Field>[] lists = new List[CONCERNED_ANNOTATION.length];
        for (int i = 0; i < CONCERNED_ANNOTATION.length; i++) {
            lists[i] = new ArrayList<>();
        }

        for (Field f : fields) {
            for (int i = 0; i < CONCERNED_ANNOTATION.length; i++) {
                if (f.getAnnotation(CONCERNED_ANNOTATION[i]) != null) {
                    f.setAccessible(true);
                    lists[i].add(f);
                }
            }
        }
        for (int i = 0; i < CLASS_ANNO_MAPPING.length; i++) {
            Field[] fs = lists[i].toArray(new Field[lists[i].size()]);
            CLASS_ANNO_MAPPING[i].put(clazz, fs);
        }
    }

    /**
     * convert camel hump to under line case.
     * 
     * @param camel
     *            can not be null
     * @return under line case
     */
    public static String camelToUnderLine(String camel) {
        String ret = CAMEL_UL_MAP.get(camel);
        if (ret == null) {
            ArrayList<String> tmp = new ArrayList<>();
            int lastIdx = 0;
            for (int i = 0; i < camel.length(); i++) {
                if (Character.isUpperCase(camel.charAt(i))) {
                    tmp.add(camel.substring(lastIdx, i).toLowerCase());
                    lastIdx = i;
                }
            }
            tmp.add(camel.substring(lastIdx));
            ret = StringUtils.join(tmp, "_");
            CAMEL_UL_MAP.put(camel, ret);
        }
        return ret;
    }

    /**
     * get the column name for sql.
     * <p>
     * if there is a @Column on Field and @Column.name() is not empty, then use
     * the name.<br>
     * else , convert fieldName to under line case
     * 
     * @param field
     *            Field
     * @return column name of under line case
     */
    public static String getColumnName(Field field) {
        Column col = field.getAnnotation(Column.class);
        if (col == null || StringUtils.isEmpty(col.name())) {
            return camelToUnderLine(field.getName());
        }
        return col.name();
    }

}
