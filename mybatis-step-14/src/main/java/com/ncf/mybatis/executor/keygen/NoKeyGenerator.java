package com.ncf.mybatis.executor.keygen;

import com.ncf.mybatis.executor.Executor;
import com.ncf.mybatis.mapping.MappedStatement;

import java.sql.Statement;

/**
 * 不用键值生成器
 *
 * @Author: fyz
 * @Date: 2023/7/23 12:40
 **/
public class NoKeyGenerator implements KeyGenerator {

    @Override
    public void processBefore(Executor executor, MappedStatement ms, Statement stmt, Object parameter) {

    }

    @Override
    public void processAfter(Executor executor, MappedStatement ms, Statement stmt, Object parameter) {

    }
}
