package com.ncf.mybatis.mapping;

import com.ncf.mybatis.session.Configuration;
import com.ncf.mybatis.type.JdbcType;

/**
 * 参数映射 #{property,javaType=int,jdbcType=NUMERIC}
 *
 * @Author: fyz
 * @Date: 2023/7/9 15:29
 **/
public class ParameterMapping {

    private Configuration configuration;
    private String property;
    private Class<?> javaType = Object.class;
    private JdbcType jdbcType;

    public ParameterMapping() {
    }

    public static class Builder {

        private ParameterMapping parameterMapping = new ParameterMapping();

        public Builder(Configuration configuration, String property) {
            parameterMapping.configuration = configuration;
            parameterMapping.property = property;
        }

        public Builder javaType(Class<?> javaType) {
            parameterMapping.javaType = javaType;
            return this;
        }

        public Builder jdbcType(JdbcType jdbcType) {
            parameterMapping.jdbcType = jdbcType;
            return this;
        }
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public String getProperty() {
        return property;
    }

    public Class<?> getJavaType() {
        return javaType;
    }

    public JdbcType getJdbcType() {
        return jdbcType;
    }
}
