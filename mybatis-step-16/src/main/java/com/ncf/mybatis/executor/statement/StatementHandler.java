package com.ncf.mybatis.executor.statement;

import com.ncf.mybatis.mapping.BoundSql;
import com.ncf.mybatis.session.ResultHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * 语句处理器
 *
 * @Author: fyz
 * @Date: 2023/7/15 12:26
 **/
public interface StatementHandler {

    /**
     * 准备语句
     *
     * @param connection
     * @return
     * @throws SQLException
     */
    Statement prepare(Connection connection) throws SQLException;

    /**
     * 参数化
     *
     * @param statement
     * @throws SQLException
     */
    void parameterize(Statement statement) throws SQLException;

    int update(Statement statement) throws SQLException;

    /**
     * 执行查询
     *
     * @param statement
     * @param resultHandler
     * @return
     * @param <E>
     * @throws SQLException
     */
    <E> List<E> query(Statement statement, ResultHandler resultHandler) throws SQLException;

    /**
     * 获取绑定SQL
     *
     * @return
     */
    BoundSql getBoundSql();
}
