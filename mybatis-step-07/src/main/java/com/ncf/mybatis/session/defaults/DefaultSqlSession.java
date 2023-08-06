package com.ncf.mybatis.session.defaults;

import com.ncf.mybatis.executor.Executor;
import com.ncf.mybatis.mapping.MappedStatement;
import com.ncf.mybatis.session.Configuration;
import com.ncf.mybatis.session.SqlSession;

import java.util.List;

/**
 * 默认SqlSession实现类
 *
 * @Author: fyz
 * @Date: 2023/7/8 14:32
 **/
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;
    private Executor executor;

    public DefaultSqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    @Override
    public <T> T selectOne(String statement) {
        return this.selectOne(statement, null);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        MappedStatement ms = configuration.getMappedStatement(statement);
        List<T> list = executor.query(ms, parameter, Executor.NO_RESULT_HANDLER, ms.getBoundSql());
        return list.get(0);
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
