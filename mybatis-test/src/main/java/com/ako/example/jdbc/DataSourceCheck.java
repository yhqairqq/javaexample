package com.ako.example.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by yanghuanqing@wdai.com on 2018/7/30.
 */
public class DataSourceCheck {

    private static Connection connection ;

    private static PreparedStatement ps ;

    private static ResultSet rs  ;

    static
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) throws Exception
    {
        connection = DriverManager.getConnection("jdbc:mysql://192.168.21.147:3306/test1","canal","canal");

        ps = connection.prepareStatement("select * from tb_user_info");

        rs = ps.executeQuery();

        while (rs.next())
        {
            System.out.println(rs.getString(1));
        }
    }
}
