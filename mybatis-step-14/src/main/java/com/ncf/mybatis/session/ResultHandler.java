package com.ncf.mybatis.session;

/**
 * 结果处理器
 *
 * @Author: fyz
 * @Date: 2023/7/15 12:36
 **/
public interface ResultHandler {

    /**
     * 处理结果
     */
    void handleResult(ResultContext context);

}
