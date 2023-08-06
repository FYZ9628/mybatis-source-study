package com.ncf.mybatis.builder;

import com.ncf.mybatis.session.Configuration;

/**
 * 构建器的基类，建造者模式
 *
 * @Author: fyz
 * @Date: 2023/7/8 18:33
 **/
public abstract class BaseBuilder {

    protected final Configuration configuration;

    public BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
