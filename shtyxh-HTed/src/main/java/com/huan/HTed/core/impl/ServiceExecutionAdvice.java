
package com.huan.HTed.core.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Collection;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.system.dto.BaseDTO;
import com.huan.HTed.system.dto.DTOClassInfo;

/**
 * service 方法执行通知.
 * <p>
 * 拦截所有 Service 方法执行,处理与 IRequest 相关的参数.
 * <p>
 * service 方法执行时间统计.
 * <p>
 * 从 request 中 copy MDC property 到 真正的 MDC
 * 
 */
public class ServiceExecutionAdvice implements MethodInterceptor {

    private Logger logger = LoggerFactory.getLogger(ServiceExecutionAdvice.class);



    public void before(Method method, Object[] args, Object target) throws Throwable {

        Class<?>[] types = method.getParameterTypes();
        int idx = -1;

        for (int i = 0; i < types.length; ++i) {
            if (IRequest.class.isAssignableFrom(types[i])) {
                idx = i;
                break;
            }
        }
        if (idx == -1) {
            // method has no argument of type IRequest
            return;
        }
        IRequest requestContext = (IRequest) args[idx];
        if (requestContext != null) {
            RequestHelper.setCurrentRequest(requestContext);
            // logger.info("before execute " + method + ", set IRequest to
            // ThreadLocal.");
        } else {
            if (logger.isWarnEnabled()) {
                logger.warn("{}'s IRequest argument is null.", method);
            }
            return;
        }
        Parameter[] parameters = method.getParameters();

        for (int i = 0; i < types.length; ++i) {
           
            if (args[i] instanceof BaseDTO) {
                BaseDTO baseDTO = (BaseDTO) args[i];
                autoAssignStdProperty(logger, requestContext, baseDTO);
            } else if (args[i] instanceof Collection) {
                for (Object o : (Collection) args[i]) {
                    if (o instanceof BaseDTO) {
                        autoAssignStdProperty(logger, requestContext, (BaseDTO) o);
                    }
                }
            }
        }
    }

    private void autoAssignStdProperty(Logger logger, IRequest request, BaseDTO dto) {
        // logger.info("auto assign request property for " + dto);
        for (Field field : DTOClassInfo.getChildrenFields(dto.getClass())) {
            if (!checkChildrenType(field.getType())) {
                if (logger.isWarnEnabled()) {
                    logger.warn("property '{}' is annotated by @Children, incorrect usage.", field.getName());
                }
                return;
            }
            try {
                Object p = field.get(dto);
                if (p instanceof BaseDTO) {
                    autoAssignStdProperty(logger, request, (BaseDTO) p);
                } else if (p instanceof Collection) {
                    for (Object o : (Collection) p) {
                        if (o instanceof BaseDTO) {
                            autoAssignStdProperty(logger, request, (BaseDTO) o);
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                if (logger.isErrorEnabled()) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }

    /**
     * 检查一个被@Children 标注的属性的类型,是否被支持.
     * 
     * @param type
     *            class type
     * @return the type is supported or not
     */
    protected boolean checkChildrenType(Class<?> type) {
        if (BaseDTO.class.isAssignableFrom(type)) {
            return true;
        }
        if (Collection.class.isAssignableFrom(type)) {
            return true;
        }
        return false;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object target = invocation.getThis();
        Method method = invocation.getMethod();
        Logger serviceLogger = LoggerFactory.getLogger(target.getClass());
        String methodStamp = target + "#" + method.getName();
        if (logger.isTraceEnabled()) {
            serviceLogger.trace("{}, parameters: {}", methodStamp, Arrays.toString(invocation.getArguments()));
        }
        long ts = System.currentTimeMillis();
        try {
            before(method, invocation.getArguments(), target);
            Object ret = invocation.proceed();
            if (logger.isTraceEnabled()) {
                serviceLogger.trace("{}, return value: {}", methodStamp, ret);
            }
            proceedAutoCacheOperation(invocation, ret);
            return ret;
        } finally {
            long timeUsed = System.currentTimeMillis() - ts;
            if (logger.isTraceEnabled()) {
                logger.trace("{}, execution time: {}ms.", methodStamp, timeUsed);
            }
        }
    }

    private void proceedAutoCacheOperation(MethodInvocation invocation, Object ret)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object target = invocation.getThis();
        Method method = invocation.getMethod();
        method = target.getClass().getMethod(method.getName(), method.getParameterTypes());
       
    }

}
