package com.util;

import java.sql.*;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

public class AdminPageAccessTypeHandler implements TypeHandler<String[]> {

    @Override
    public void setParameter(PreparedStatement ps, int i, String[] parameter, JdbcType jdbcType) throws SQLException {
        // convert the String[] to a serialized string
        ps.setString(i, String.join(",", parameter));
    }

    @Override
    public String[] getResult(ResultSet rs, String columnName) throws SQLException {
        Array array = rs.getArray(columnName);
        if(array == null) return null;
        return (String[]) array.getArray();
    }

    @Override
    public String[] getResult(ResultSet rs, int columnIndex) throws SQLException {
        Array array = rs.getArray(columnIndex);
        if(array == null) return null;
        return (String[]) array.getArray();
    }

    @Override
    public String[] getResult(CallableStatement cs, int columnIndex) throws SQLException {
        Array array = cs.getArray(columnIndex);
        if(array == null) return null;
        return (String[]) array.getArray();
    }


}

