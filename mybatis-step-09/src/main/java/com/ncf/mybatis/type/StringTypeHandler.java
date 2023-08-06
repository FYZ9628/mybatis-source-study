package com.ncf.mybatis.type;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * StringTypeHandler
 *
 * @Author: fyz
 * @Date: 2023/7/16 18:01
 **/
public class StringTypeHandler extends BaseTypeHandler<String> {

    @Override
    protected void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter);
    }
}
