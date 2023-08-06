package com.ncf.mybatis.session.defaults;

import com.ncf.mybatis.binding.MapperRegistry;
import com.ncf.mybatis.mapping.MappedStatement;
import com.ncf.mybatis.session.Configuration;
import com.ncf.mybatis.session.SqlSession;

/**
 * 默认SqlSession实现类
 *
 * @Author: fyz
 * @Date: 2023/7/8 14:32
 **/
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T selectOne(String statement) {
        return (T) ("你被代理了！" + statement);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        MappedStatement mappedStatement = configuration.getMappedStatement(statement);
        return (T) ("你被代理了！" + "\n方法：" + statement + " \n入参：" + parameter + "\n待执行SQL：" + mappedStatement.getSql());
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type, this);
    }
}
