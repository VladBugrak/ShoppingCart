package com.model.dao;

import com.model.entity.Order;
import com.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OrderDao {
    private Connection connection;
    private String query;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private User user;

    public OrderDao(Connection connection) {
        this.connection = connection;
    }

    public boolean insertOrder(Order order){
        boolean result = false;

        try {
            query = """
                   insert into orders
                   (
                    p_id,
                    u_id,
                    o_quantity,
                    o_date
                    ) values (
                    ?,
                    ?,
                    ?,
                    ?
                    )
                    """;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,order.getId());
            preparedStatement.setInt(2,order.getUserID());
            preparedStatement.setInt(3,order.getQuantity());
            preparedStatement.setString(4,order.getDate());
            preparedStatement.executeUpdate();
            result = true;


        } catch (Exception e){
            e.printStackTrace();
        }


        return result;
    }




}


