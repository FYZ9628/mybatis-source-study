package com.ncf.mybatis.plugin;

import java.util.Properties;

/**
 * 拦截器接口
 *
 * @Author: fyz
 * @Date: 2023/8/3 20:40
 **/
public interface Interceptor {

    /**
     * 拦截，使用方实现
     *
     * @param invocation
     * @return
     * @throws Throwable
     */
    Object intercept(Invocation invocation) throws Throwable;

    /**
     * 代理
     *
     * @param target
     * @return
     */
    default Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    /**
     * 设置属性
     *
     * @param properties
     */
    default void setProperties(Properties properties) {

    }
}
