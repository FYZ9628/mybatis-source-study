package com.ncf.mybatis.mapping;

/**
 * SQL 指令类型
 *
 * @Author: fyz
 * @Date: 2023/7/8 18:34
 **/
public enum SqlCommandType {

    /**
     * 未知
     */
    UNKNOWN,
    /**
     * 插入
     */
    INSERT,
    /**
     * 更新
     */
    UPDATE,
    /**
     * 删除
     */
    DELETE,
    /**
     * 查找
     */
    SELECT;
}
