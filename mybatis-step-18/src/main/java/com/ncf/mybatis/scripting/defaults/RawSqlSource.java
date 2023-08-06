package com.ncf.mybatis.scripting.defaults;

import com.ncf.mybatis.builder.SqlSourceBuilder;
import com.ncf.mybatis.mapping.BoundSql;
import com.ncf.mybatis.mapping.SqlSource;
import com.ncf.mybatis.scripting.xmltags.DynamicContext;
import com.ncf.mybatis.scripting.xmltags.SqlNode;
import com.ncf.mybatis.session.Configuration;

import java.util.HashMap;

/**
 * 原始SQL源码，比 DynamicSqlSource 动态SQL处理快
 *
 * @Author: fyz
 * @Date: 2023/7/16 13:03
 **/
public class RawSqlSource implements SqlSource {

    private final SqlSource sqlSource;

    public RawSqlSource(Configuration configuration, SqlNode rootSqlNode, Class<?> parameterType) {
        this(configuration, getSql(configuration, rootSqlNode), parameterType);
    }

    public RawSqlSource(Configuration configuration, String sql, Class<?> parameterType) {
        SqlSourceBuilder sqlSourceParser = new SqlSourceBuilder(configuration);
        Class<?> clazz = parameterType == null ? Object.class : parameterType;
        sqlSource = sqlSourceParser.parse(sql, clazz, new HashMap<>());
    }

    @Override
    public BoundSql getBoundSql(Object parameterObject) {
        return sqlSource.getBoundSql(parameterObject);
    }

    private static String getSql(Configuration configuration, SqlNode rootSqlNode) {
        DynamicContext context = new DynamicContext(configuration, null);
        rootSqlNode.apply(context);
        return context.getSql();
    }

}