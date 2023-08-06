package com.ncf.mybatis.scripting.xmltags;

/**
 * SQL 节点
 *
 * @Author: fyz
 * @Date: 2023/7/16 13:04
 **/
public interface SqlNode {

    boolean apply(DynamicContext context);

}