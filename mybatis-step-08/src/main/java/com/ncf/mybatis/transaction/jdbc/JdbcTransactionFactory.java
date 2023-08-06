package com.ncf.mybatis.transaction.jdbc;

import com.ncf.mybatis.session.TransactionIsolationLevel;
import com.ncf.mybatis.transaction.Transaction;
import com.ncf.mybatis.transaction.TransactionFactory;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * JdbcTransaction 工厂
 *
 * @Author: fyz
 * @Date: 2023/7/9 15:19
 **/
public class JdbcTransactionFactory implements TransactionFactory {
    @Override
    public Transaction newTransaction(Connection conn) {
        return new JdbcTransaction(conn);
    }

    @Override
    public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit) {
        return new JdbcTransaction(dataSource, level, autoCommit);
    }
}
