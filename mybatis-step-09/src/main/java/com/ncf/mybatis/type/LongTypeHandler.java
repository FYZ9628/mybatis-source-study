package com.ncf.mybatis.type;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * LongTypeHandler
 *
 * @Author: fyz
 * @Date: 2023/7/16 17:59
 **/
public class LongTypeHandler extends BaseTypeHandler<Long> {

    @Override
    protected void setNonNullParameter(PreparedStatement ps, int i, Long parameter, JdbcType jdbcType) throws SQLException {
        ps.setLong(i, parameter);
    }
}
