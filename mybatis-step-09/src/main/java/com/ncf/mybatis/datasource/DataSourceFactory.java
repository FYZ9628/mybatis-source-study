package com.ncf.mybatis.datasource;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 数据源工厂
 *
 * @Author: fyz
 * @Date: 2023/7/9 15:22
 **/
public interface DataSourceFactory {

    void setProperties(Properties props);

    DataSource getDataSource();
}
