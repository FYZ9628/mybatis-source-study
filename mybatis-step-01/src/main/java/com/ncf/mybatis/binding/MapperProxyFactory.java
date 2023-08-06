package com.ncf.mybatis.binding;

import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * 映射器代理工厂
 *
 * @Author: fyz
 * @Date: 2023/7/8 12:12
 **/
public class MapperProxyFactory<T> {

    private final Class<T> mapperInterface;

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public T newInstance(Map<String, String> sqlSession) {
        MapperProxy<T> mapperProxy = new MapperProxy<>(sqlSession, mapperInterface);
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[] {mapperInterface}, mapperProxy);
    }
}
