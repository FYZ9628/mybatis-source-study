package com.ncf.mybatis.reflection.invoker;

/**
 * 调用者
 *
 * @Author: fyz
 * @Date: 2023/7/15 15:27
 **/
public interface Invoker {

    Object invoke(Object target, Object[] args) throws Exception;

    Class<?> getType();
}
