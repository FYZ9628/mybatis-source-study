package com.ncf.mybatis.transaction;

import com.ncf.mybatis.session.TransactionIsolationLevel;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * 事务工厂
 *
 * @Author: fyz
 * @Date: 2023/7/9 14:57
 **/
public interface TransactionFactory {

    /**
     * 根据 Connection 创建 Transaction
     *
     * @param conn
     * @return
     */
    Transaction newTransaction(Connection conn);

    /**
     * 根据数据源和事务隔离级别创建 Transaction
     *
     * @param dataSource
     * @param level
     * @param autoCommit
     * @return
     */
    Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit);
}
