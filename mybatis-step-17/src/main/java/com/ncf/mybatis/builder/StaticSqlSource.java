package com.ncf.mybatis.builder;

import com.ncf.mybatis.mapping.BoundSql;
import com.ncf.mybatis.mapping.ParameterMapping;
import com.ncf.mybatis.mapping.SqlSource;
import com.ncf.mybatis.session.Configuration;

import java.util.List;

/**
 * 静态SQL源码
 *
 * @Author: fyz
 * @Date: 2023/7/16 13:06
 **/
public class StaticSqlSource implements SqlSource {

    private String sql;
    private List<ParameterMapping> parameterMappings;
    private Configuration configuration;

    public StaticSqlSource(Configuration configuration, String sql) {
        this(configuration, sql, null);
    }

    public StaticSqlSource(Configuration configuration, String sql, List<ParameterMapping> parameterMappings) {
        this.sql = sql;
        this.parameterMappings = parameterMappings;
        this.configuration = configuration;
    }

    @Override
    public BoundSql getBoundSql(Object parameterObject) {
        return new BoundSql(configuration, sql, parameterMappings, parameterObject);
    }
}
