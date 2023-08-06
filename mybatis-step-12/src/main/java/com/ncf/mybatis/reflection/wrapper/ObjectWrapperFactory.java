package com.ncf.mybatis.reflection.wrapper;

import com.ncf.mybatis.reflection.MetaObject;

/**
 * 对象包装工厂
 *
 * @Author: fyz
 * @Date: 2023/7/15 15:30
 **/
public interface ObjectWrapperFactory {

    /**
     * 判断有没有包装器
     *
     * @param object
     * @return
     */
    boolean hasWrapperFor(Object object);

    /**
     * 得到包装器
     *
     * @param metaObject
     * @param object
     * @return
     */
    ObjectWrapper getWrapperFor(MetaObject metaObject, Object object);
}
