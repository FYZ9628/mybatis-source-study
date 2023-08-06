package com.ncf.mybatis.executor.parameter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 参数处理器
 *
 * @Author: fyz
 * @Date: 2023/7/16 17:41
 **/
public interface ParameterHandler {

    /**
     * 获取参数
     *
     * @return
     */
    Object getParameterObject();

    /**
     * 设置参数
     *
     * @param ps
     * @throws SQLException
     */
    void setParameters(PreparedStatement ps) throws SQLException;
}
