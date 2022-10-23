package com.model.dao;

import com.connection.DBConnection;
import com.model.entity.Order;
import com.model.entity.Product;
import com.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

    public List<Order> userOrders(int userID){
        List<Order> orderList = new ArrayList<Order>();
        try{
            query = """
                    select * 
                    from orders 
                    where u_id = ? 
                    order by o_id desc
                    """;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,userID);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Order order = new Order();
                ProductDao productDao = new ProductDao(connection);
                int productID = resultSet.getInt("p_id");

                Product product = productDao.getSingleProduct(productID);
                order.setOrderId(resultSet.getInt("o_id"));
                order.setId(productID);
                order.setName(product.getName());
                order.setCategory(product.getCategory());
                order.setPrice(product.getPrice() * resultSet.getInt("o_quantity"));
                order.setQuantity(resultSet.getInt("o_quantity"));
                order.setDate(resultSet.getString("o_date"));
                orderList.add(order);
            }


        }catch (Exception e){
            e.printStackTrace();
        }

        return orderList;
    }

    public void cancelOrder(int orderID){

        try{
            query = """
                    delete from orders
                    where o_id = ?               
                    """;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,orderID);
            preparedStatement.execute();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}


