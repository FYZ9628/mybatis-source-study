package com.ncf.mybatis.session;

/**
 * 结果上下文
 *
 * @Author: fyz
 * @Date: 2023/7/22 10:30
 **/
public interface ResultContext {

    /**
     * 获取结果
     * @return
     */
    Object getResultObject();

    /**
     * 获取记录数
     * @return
     */
    int getResultCount();
}
