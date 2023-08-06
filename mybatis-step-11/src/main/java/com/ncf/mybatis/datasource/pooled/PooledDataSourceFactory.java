package com.ncf.mybatis.datasource.pooled;

import com.ncf.mybatis.datasource.unpooled.UnpooledDataSourceFactory;

/**
 * 有连接池的数据源工厂
 *
 * @Author: fyz
 * @Date: 2023/7/9 19:55
 **/
public class PooledDataSourceFactory extends UnpooledDataSourceFactory {

    public PooledDataSourceFactory() {
        this.dataSource = new PooledDataSource();
    }
}
