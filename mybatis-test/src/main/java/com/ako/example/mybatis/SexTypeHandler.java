package com.ako.example.mybatis;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by yanghuanqing@wdai.com on 2018/7/30.
 */
public class SexTypeHandler extends BaseTypeHandler<SexEnum> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, SexEnum parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i,parameter.getSexDec());
    }

    @Override
    public SexEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        for(SexEnum sex:SexEnum.values()){
            if(sex.getSexDec().equals(rs.getString(columnName))){
                return sex;
            }
        }
        return SexEnum.values()[0];
    }

    @Override
    public SexEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        for(SexEnum sex:SexEnum.values()){
            if(sex.getSexDec().equals(rs.getString(columnIndex))){
                return sex;
            }
        }
        return SexEnum.values()[0];
    }

    @Override
    public SexEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        for(SexEnum sex:SexEnum.values()){
            if(sex.getSexDec().equals(cs.getString(columnIndex))){
                return sex;
            }
        }
        return SexEnum.values()[0];
    }
}
