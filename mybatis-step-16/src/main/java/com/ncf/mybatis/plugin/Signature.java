package com.ncf.mybatis.plugin;

/**
 * 方法签名
 *
 * @Author: fyz
 * @Date: 2023/8/3 20:42
 **/
public @interface Signature {

    /**
     * 被拦截类
     *
     * @return
     */
    Class<?> type();

    /**
     * 被拦截类的方法
     *
     * @return
     */
    String method();

    /**
     * 被拦截类的方法的参数
     *
     * @return
     */
    Class<?>[] args();
}
