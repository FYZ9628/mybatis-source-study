package com.ncf.mybatis.executor.statement;

import com.ncf.mybatis.executor.Executor;
import com.ncf.mybatis.mapping.BoundSql;
import com.ncf.mybatis.mapping.MappedStatement;
import com.ncf.mybatis.session.ResultHandler;
import com.ncf.mybatis.session.RowBounds;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * 简单语句处理器（STATEMENT）
 *
 * @Author: fyz
 * @Date: 2023/7/15 12:27
 **/
public class SimpleStatementHandler extends BaseStatementHandler {


    public SimpleStatementHandler(Executor executor, MappedStatement mappedStatement, Object parameterObject, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) {
        super(executor, mappedStatement, parameterObject,rowBounds, resultHandler, boundSql);
    }

    @Override
    protected Statement instantiateStatement(Connection connection) throws SQLException {
        return connection.createStatement();
    }

    @Override
    public void parameterize(Statement statement) throws SQLException {

    }

    @Override
    public <E> List<E> query(Statement statement, ResultHandler resultHandler) throws SQLException {
        String sql = boundSql.getSql();
        statement.execute(sql);
        return resultSetHandler.handleResultSets(statement);
    }
}
