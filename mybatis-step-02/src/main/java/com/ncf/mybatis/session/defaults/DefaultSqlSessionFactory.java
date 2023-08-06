package com.ncf.mybatis.session.defaults;

import com.ncf.mybatis.binding.MapperRegistry;
import com.ncf.mybatis.session.SqlSession;
import com.ncf.mybatis.session.SqlSessionFactory;

/**
 * 默认的 DefaultSqlSessionFactory
 *
 * @Author: fyz
 * @Date: 2023/7/8 14:32
 **/
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final MapperRegistry mapperRegistry;

    public DefaultSqlSessionFactory(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(mapperRegistry);
    }
}
