package com.ncf.mybatis.reflection.wrapper;

import com.ncf.mybatis.reflection.MetaObject;

/**
 * 默认对象包装工厂
 *
 * @Author: fyz
 * @Date: 2023/7/15 15:30
 **/
public class DefaultObjectWrapperFactory implements ObjectWrapperFactory {
    @Override
    public boolean hasWrapperFor(Object object) {
        return false;
    }

    @Override
    public ObjectWrapper getWrapperFor(MetaObject metaObject, Object object) {
        throw new RuntimeException("The DefaultObjectWrapperFactory should never be called to provide an ObjectWrapper.");
    }
}
