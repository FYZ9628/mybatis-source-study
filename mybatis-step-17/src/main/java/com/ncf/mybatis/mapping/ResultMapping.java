package com.ncf.mybatis.mapping;

import com.ncf.mybatis.session.Configuration;
import com.ncf.mybatis.type.JdbcType;
import com.ncf.mybatis.type.TypeHandler;
import com.ncf.mybatis.type.TypeHandlerRegistry;

import java.util.ArrayList;
import java.util.List;

/**
 * 结果映射
 *
 * @Author: fyz
 * @Date: 2023/7/22 09:47
 **/
public class ResultMapping {

    private Configuration configuration;
    private String property;// java属性字段（驼峰命名）
    private String column;// jdbc属性字段（下划线命名）
    private Class<?> javaType;// java属性字段所属类型
    private JdbcType jdbcType;//　jdbc属性字段所属类型
    private TypeHandler<?> typeHandler;// 类型转换处理器（jdbc字段类型和java字段类型之间的转换）
    private List<ResultFlag> flags;// 结果标志

    ResultMapping() {
    }

    public static class Builder {
        private ResultMapping resultMapping = new ResultMapping();

        public Builder(Configuration configuration, String property, String column, Class<?> javaType) {
            resultMapping.configuration = configuration;
            resultMapping.property = property;
            resultMapping.column = column;
            resultMapping.javaType = javaType;
            resultMapping.flags = new ArrayList<>();
        }

        public Builder typeHandler(TypeHandler<?> typeHandler) {
            resultMapping.typeHandler = typeHandler;
            return this;
        }

        public Builder flags(List<ResultFlag> flags) {
            resultMapping.flags = flags;
            return this;
        }

        public ResultMapping build() {
            resolveTypeHandler();
            return resultMapping;
        }

        private void resolveTypeHandler() {
            if (resultMapping.typeHandler == null && resultMapping.javaType != null) {
                Configuration configuration = resultMapping.configuration;
                TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
                resultMapping.typeHandler = typeHandlerRegistry.getTypeHandler(resultMapping.javaType, null);
            }
        }
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public String getProperty() {
        return property;
    }

    public String getColumn() {
        return column;
    }

    public Class<?> getJavaType() {
        return javaType;
    }

    public TypeHandler<?> getTypeHandler() {
        return typeHandler;
    }

    public List<ResultFlag> getFlags() {
        return flags;
    }

}
