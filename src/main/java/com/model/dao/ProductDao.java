package com.model.dao;

import com.model.entity.Product;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    private Connection connection;
    private String query;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;


    public ProductDao(Connection connection) {
        this.connection = connection;
    }

    public List<Product> getAllProducts(){
        List<Product> productList = new ArrayList<Product>();

        try{
            query = "select * from products";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Product product = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("category"),
                        resultSet.getString("price"),
                        resultSet.getString("image")
                );
                productList.add(product);

                System.out.println(product.toString());
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return productList;
    }
}
