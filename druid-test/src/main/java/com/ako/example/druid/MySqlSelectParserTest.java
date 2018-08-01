package com.ako.example.druid;

import com.alibaba.druid.sql.ast.statement.SQLSelectQuery;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlSelectParser;

/**
 * Created by yanghuanqing@wdai.com on 2018/8/1.
 */
public class MySqlSelectParserTest {
    public static void main(String[] args) {
        String sql = "select t1.a,t1.b from t1 where t1.a in (select t2.a from t2 where t2.b='b' and t2.c = ifnull('',t2.c))";
        MySqlSelectParser preparedSqlParser = new MySqlSelectParser(sql);
        SQLSelectQuery sqlSelectQuery =  preparedSqlParser.query();
        System.out.println(sqlSelectQuery.toString());
    }
}
