package com.ncf.mybatis.session;

import com.ncf.mybatis.binding.MapperRegistry;
import com.ncf.mybatis.datasource.druid.DruidDataSourceFactory;
import com.ncf.mybatis.datasource.pooled.PooledDataSourceFactory;
import com.ncf.mybatis.datasource.unpooled.UnpooledDataSourceFactory;
import com.ncf.mybatis.executor.Executor;
import com.ncf.mybatis.executor.SimpleExecutor;
import com.ncf.mybatis.executor.resultset.DefaultResultSetHandler;
import com.ncf.mybatis.executor.resultset.ResultSetHandler;
import com.ncf.mybatis.executor.statement.PreparedStatementHandler;
import com.ncf.mybatis.executor.statement.StatementHandler;
import com.ncf.mybatis.mapping.BoundSql;
import com.ncf.mybatis.mapping.Environment;
import com.ncf.mybatis.mapping.MappedStatement;
import com.ncf.mybatis.transaction.Transaction;
import com.ncf.mybatis.transaction.jdbc.JdbcTransactionFactory;
import com.ncf.mybatis.type.TypeAliasRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * 配置项（除了存放 mapper 接口类的信息外，还要存放 mapper 与其对应 xml 配置信息）
 *
 * @Author: fyz
 * @Date: 2023/7/8 18:34
 **/
public class Configuration {

    /**
     * 环境
     */
    protected Environment environment;

    /**
     * 映射注册机
     */
    protected MapperRegistry mapperRegistry = new MapperRegistry(this);

    /**
     * 映射的语句，存在Map里
     */
    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();

    /**
     * 类型别名注册机
     */
    protected final TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();

    public Configuration() {
        typeAliasRegistry.registerAlias("JDBC", JdbcTransactionFactory.class);

        typeAliasRegistry.registerAlias("DRUID", DruidDataSourceFactory.class);
        typeAliasRegistry.registerAlias("UNPOOLED", UnpooledDataSourceFactory.class);
        typeAliasRegistry.registerAlias("POOLED", PooledDataSourceFactory.class);
    }

    public void addMappers(String packageName) {
        mapperRegistry.addMappers(packageName);
    }

    public <T> void addMapper(Class<T> type) {
        mapperRegistry.addMapper(type);
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return mapperRegistry.getMapper(type, sqlSession);
    }

    public boolean hasMapper(Class<?> type) {
        return mapperRegistry.hasMapper(type);
    }

    public void addMappedStatement(MappedStatement ms) {
        mappedStatements.put(ms.getId(), ms);
    }

    public MappedStatement getMappedStatement(String id) {
        return mappedStatements.get(id);
    }

    public TypeAliasRegistry getTypeAliasRegistry() {
        return typeAliasRegistry;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    /**
     * 创建结果集处理器
     *
     * @param executor
     * @param mappedStatement
     * @param boundSql
     * @return
     */
    public ResultSetHandler newResultSetHandler(Executor executor, MappedStatement mappedStatement, BoundSql boundSql) {
        return new DefaultResultSetHandler(executor, mappedStatement, boundSql);
    }

    /**
     * 生产执行器
     *
     * @param transaction
     * @return
     */
    public Executor newExecutor(Transaction transaction) {
        return new SimpleExecutor(this, transaction);
    }

    /**
     * 创建语句处理器
     *
     * @param executor
     * @param mappedStatement
     * @param parameter
     * @param resultHandler
     * @param boundSql
     * @return
     */
    public StatementHandler newStatementHandler(Executor executor, MappedStatement mappedStatement, Object parameter, ResultHandler resultHandler, BoundSql boundSql) {
        return new PreparedStatementHandler(executor, mappedStatement, parameter, resultHandler, boundSql);
    }
}

