package com.ako.example.jdk.sqlite;

import java.sql.*;

/**
 * Created by yanghuanqing@wdai.com on 21/09/2017.
 */
public class SQLiteSampleTest {

    public static void main(String[] args)
    {
        Connection connection = null;
        try
        {

            connection = DriverManager.getConnection("jdbc:sqlite::memory:");
            // create a database connection
//            connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            statement.executeUpdate("drop table if exists person");
            statement.executeUpdate("create table person (id integer, name string)");
            statement.executeUpdate("insert into person values(1, 'leo')");
            statement.executeUpdate("insert into person values(2, 'yui')");


            statement.executeUpdate("drop table if exists person_age");
            statement.executeUpdate("create table person_age (id integer, age integer)");
            statement.executeUpdate("insert into person_age values(1, 23)");
            statement.executeUpdate("insert into person_age values(2, 36)");



            ResultSet rs = statement.executeQuery("select * from person,person_age where person.id=person_age.id");
            while(rs.next())
            {
                // read the result set
                System.out.println("name = " + rs.getString("name"));
                System.out.println("id = " + rs.getInt("id"));
                System.out.println("age = "+rs.getString("age"));
            }
        }
        catch(SQLException e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
        finally
        {
            try
            {
                if(connection != null)
                    connection.close();
            }
            catch(SQLException e)
            {
                // connection close failed.
                System.err.println(e);
            }
        }
    }
}
