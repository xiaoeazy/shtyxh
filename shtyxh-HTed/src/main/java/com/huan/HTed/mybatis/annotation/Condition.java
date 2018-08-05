

package com.huan.HTed.mybatis.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.UnknownTypeHandler;

/**
 * where condition configuration.
 *
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Condition {

    /**
     * compare operation.
     * <p>
     * =<br>
     * &gt;<br>
     * &lt;<br>
     * &gt;=<br>
     * &lt;=<br>
     * LIKE<br>
     * &lt;&gt;<br>
     * != <p>
     * default =
     */
    String operator() default "=";

    /**
     * only work when operator=LIKE.
     * <p>
     * true : generate expression : xx LIKE concat('%',concat(#{yy},'%'))<br>
     * false : generate expression : xx LIKE #{yy}<br>
     * default true
     */
    boolean autoWrap() default true;

    /**
     * don't use this field as condition.
     * 
     * default false
     */
    boolean exclude() default false;
}
