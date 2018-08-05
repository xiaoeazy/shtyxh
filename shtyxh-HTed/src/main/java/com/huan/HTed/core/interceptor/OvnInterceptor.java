
package com.huan.HTed.core.interceptor;

import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

import com.huan.HTed.system.dto.BaseDTO;

/**
 * 插入、更新以后,自动设置 objectversionnumber。<br>
 * 更新、删除 失败以后，抛出异常的动作，要在具体执行操作的地方自己检测。<br>
 * BaseServiceImpl 已经支持
 *
 * @author huanghuan
 */
@Intercepts({ @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }) })
public class OvnInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object result = invocation.proceed();

        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object domain = invocation.getArgs()[1];
        if (!(domain instanceof BaseDTO)) {
            return result;
        }
        BaseDTO baseDTO = (BaseDTO) domain;
        Long ovn = baseDTO.getObjectVersionNumber();
        if (mappedStatement.getSqlCommandType() == SqlCommandType.INSERT) {
            baseDTO.setObjectVersionNumber(1L);
            return result;
        }
        if (ovn == null) {
            return result;
        }
        if (mappedStatement.getSqlCommandType() == SqlCommandType.UPDATE) {
            baseDTO.setObjectVersionNumber(ovn + 1L);
        }
        return result;
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {

    }

}
