package com.ncf.mybatis.scripting.xmltags;

/**
 * 静态文本SQL节点
 *
 * @Author: fyz
 * @Date: 2023/7/16 13:05
 **/
public class StaticTextSqlNode implements SqlNode {

    private String text;

    public StaticTextSqlNode(String text) {
        this.text = text;
    }

    @Override
    public boolean apply(DynamicContext context) {
        //将文本加入context
        context.appendSql(text);
        return true;
    }

}
