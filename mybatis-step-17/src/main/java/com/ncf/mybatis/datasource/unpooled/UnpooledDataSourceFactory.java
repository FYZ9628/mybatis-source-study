package com.ncf.mybatis.datasource.unpooled;

import com.ncf.mybatis.datasource.DataSourceFactory;
import com.ncf.mybatis.reflection.MetaObject;
import com.ncf.mybatis.reflection.SystemMetaObject;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 无池化数据源工厂
 *
 * @Author: fyz
 * @Date: 2023/7/9 19:18
 **/
public class UnpooledDataSourceFactory implements DataSourceFactory {

    protected DataSource dataSource;

    public UnpooledDataSourceFactory() {
        this.dataSource = new UnpooledDataSource();
    }

    @Override
    public void setProperties(Properties props) {
        MetaObject metaObject = SystemMetaObject.forObject(dataSource);
        for (Object key : props.keySet()) {
            String propertyName = (String) key;
            if (metaObject.hasSetter(propertyName)) {
                String value = (String) props.get(propertyName);
                Object convertedValue = convertValue(metaObject, propertyName, value);
                metaObject.setValue(propertyName, convertedValue);
            }
        }
    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }

    /**
     * 根据 setter 的类型，将配置文件中的值强转成相应的类型
     *
     * @param metaObject
     * @param propertyName
     * @param value
     * @return
     */
    private Object convertValue(MetaObject metaObject, String propertyName, String value) {
        Object convertedValue = value;
        Class<?> targetType = metaObject.getSetterType(propertyName);
        if (targetType == Integer.class || targetType == int.class) {
            convertedValue = Integer.valueOf(value);
        } else if (targetType == Long.class || targetType == long.class) {
            convertedValue = Long.valueOf(value);
        } else if (targetType == Boolean.class || targetType == boolean.class) {
            convertedValue = Boolean.valueOf(value);
        }
        return convertedValue;
    }
}
