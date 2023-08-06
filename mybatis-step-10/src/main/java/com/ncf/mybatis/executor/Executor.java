package com.ncf.mybatis.executor;

import com.ncf.mybatis.mapping.BoundSql;
import com.ncf.mybatis.mapping.MappedStatement;
import com.ncf.mybatis.session.ResultHandler;
import com.ncf.mybatis.session.RowBounds;
import com.ncf.mybatis.transaction.Transaction;

import java.sql.SQLException;
import java.util.List;

/**
 * 执行器
 *
 * @Author: fyz
 * @Date: 2023/7/15 12:23
 **/
public interface Executor {

    ResultHandler NO_RESULT_HANDLER = null;

    <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql);

    Transaction getTransaction();

    void commit(boolean required) throws SQLException;

    void rollback(boolean required) throws SQLException;

    void close(boolean forceRollback);

}
