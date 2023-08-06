package com.ncf.mybatis.parsing;

/**
 * 记号处理器
 *
 * @Author: fyz
 * @Date: 2023/7/16 15:24
 **/
public interface TokenHandler {

    String handleToken(String content);

}