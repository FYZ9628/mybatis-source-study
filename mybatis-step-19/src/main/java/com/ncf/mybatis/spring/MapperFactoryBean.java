package com.ncf.mybatis.spring;

import com.ncf.mybatis.session.SqlSessionFactory;
import org.springframework.beans.factory.FactoryBean;

/**
 * FactoryBean 使用案例
 *
 * https://blog.csdn.net/yx444535180/article/details/124605575
 *
 * Mapper 工厂对象
 *
 * @Author: fyz
 * @Date: 2023/8/6 14:52
 **/
public class MapperFactoryBean<T> implements FactoryBean<T> {

    private Class<T> mapperInterface;
    private SqlSessionFactory sqlSessionFactory;

    public MapperFactoryBean(Class<T> mapperInterface, SqlSessionFactory sqlSessionFactory) {
        this.mapperInterface = mapperInterface;
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public T getObject() throws Exception {
        return sqlSessionFactory.openSession().getMapper(mapperInterface);
    }

    @Override
    public Class<?> getObjectType() {
        return mapperInterface;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
