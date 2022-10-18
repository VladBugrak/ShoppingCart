package com.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

//  public static final String URL = "jdbc:mysql://localhost:3306/ecommerce_cart";
    public static final String URL = "jdbc:mysql://localhost:3306/ecommerce_cart";
    public static final String USER = "root";
    public static final String PASSWORD = "root";
    private static Connection connection = null;

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if(connection==null){
           Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(URL, USER, PASSWORD);
//            System.out.println("");
        }
        return connection;
    }


}
