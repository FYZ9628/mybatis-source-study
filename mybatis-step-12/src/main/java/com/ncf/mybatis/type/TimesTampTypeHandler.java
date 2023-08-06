package com.ncf.mybatis.type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Date类型处理器
 *
 * @Author: fyz
 * @Date: 2023/7/16 17:59
 **/
public class TimesTampTypeHandler extends BaseTypeHandler<Date> {

    @Override
    protected void setNonNullParameter(PreparedStatement ps, int i, Date parameter, JdbcType jdbcType) throws SQLException {
        Timestamp timestamp = new Timestamp(parameter.getTime());
        ps.setTimestamp(i, timestamp);
    }

    @Override
    protected Date getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return rs.getTimestamp(columnName);
    }
}
