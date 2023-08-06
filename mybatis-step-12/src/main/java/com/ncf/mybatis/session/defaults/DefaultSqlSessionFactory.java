package com.ncf.mybatis.session.defaults;

import com.ncf.mybatis.executor.Executor;
import com.ncf.mybatis.mapping.Environment;
import com.ncf.mybatis.session.Configuration;
import com.ncf.mybatis.session.SqlSession;
import com.ncf.mybatis.session.SqlSessionFactory;
import com.ncf.mybatis.session.TransactionIsolationLevel;
import com.ncf.mybatis.transaction.Transaction;
import com.ncf.mybatis.transaction.TransactionFactory;

import java.sql.SQLException;

/**
 * 默认的 DefaultSqlSessionFactory
 *
 * @Author: fyz
 * @Date: 2023/7/8 14:32
 **/
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        Transaction tx = null;
        try {
            final Environment environment = configuration.getEnvironment();
            TransactionFactory transactionFactory = environment.getTransactionFactory();
            tx = transactionFactory.newTransaction(configuration.getEnvironment().getDataSource(), TransactionIsolationLevel.READ_COMMITTED, false);
            // 创建执行器
            final Executor executor = configuration.newExecutor(tx);
            // 创建DefaultSqlSession
            return new DefaultSqlSession(configuration, executor);
        } catch (Exception e) {
            try {
                assert tx != null;
                tx.close();
            } catch (SQLException ignore) {
            }
            throw new RuntimeException("Error opening session. Cause: " + e);
        }
    }
}
