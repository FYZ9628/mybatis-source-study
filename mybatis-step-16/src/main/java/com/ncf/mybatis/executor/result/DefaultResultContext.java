package com.ncf.mybatis.executor.result;

import com.ncf.mybatis.session.ResultContext;

/**
 * 默认结果上下文
 *
 * @Author: fyz
 * @Date: 2023/7/22 10:26
 **/
public class DefaultResultContext implements ResultContext {

    private Object resultObject;
    private int resultCount;

    public DefaultResultContext() {
        this.resultObject = null;
        this.resultCount = 0;
    }

    @Override
    public Object getResultObject() {
        return resultObject;
    }

    @Override
    public int getResultCount() {
        return resultCount;
    }

    public void nextResultObject(Object resultObject) {
        resultCount++;
        this.resultObject = resultObject;
    }

}
