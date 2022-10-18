package com.model.dao;

import com.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {
    private Connection connection;
    private String query;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private User user;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public User userLogin(String email, String password){

        try {
            query = "select * from users where email=? and password=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
            }

        } catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return user;

    }


}
