package com.ncf.mybatis.mapping;

import com.ncf.mybatis.session.Configuration;
import com.ncf.mybatis.type.JdbcType;
import com.ncf.mybatis.type.TypeHandler;

/**
 * 结果映射
 *
 * @Author: fyz
 * @Date: 2023/7/22 09:47
 **/
public class ResultMapping {

    private Configuration configuration;
    private String property;
    private String column;
    private Class<?> javaType;
    private JdbcType jdbcType;
    private TypeHandler<?> typeHandler;

    ResultMapping() {
    }

    public static class Builder {
        private ResultMapping resultMapping = new ResultMapping();


    }

}
