package com.ncf.mybatis.mapping;

/**
 * SQL源码
 *
 * @Author: fyz
 * @Date: 2023/7/16 13:00
 **/
public interface SqlSource {

    BoundSql getBoundSql(Object parameterObject);
}
