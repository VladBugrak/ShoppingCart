package com.model.dao;

import com.model.entity.Cart;
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
                        resultSet.getDouble("price") ,
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


    public List<Cart> getCartProducts(ArrayList<Cart> cartList){
        List<Cart> cartList1 = new ArrayList<Cart>();

        try{
            if(cartList.size() > 0){
                for(Cart item:cartList){
                   query = "select * from products where id=?";
                   preparedStatement = connection.prepareStatement(query);
                   preparedStatement.setInt(1,item.getId());
                   resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()){
                        Cart cart = new Cart(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getString("category"),
                                resultSet.getDouble("price") * item.getQuantity(),
                                resultSet.getString("image"),
                                item.getQuantity()
                        );
                        cartList1.add(cart);
                    }
                }
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return cartList1;
    }

    public double getTotalCartPrice(ArrayList<Cart> cartList){
        if(cartList == null || cartList.isEmpty())
            return 0;

        double sum = 0;
        try {
            for(Cart item: cartList) {
                query = "select price from products where id= ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1,item.getId());
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    sum += resultSet.getDouble("price") * item.getQuantity();
                }
            }
        } catch (Exception e){
                System.out.println(e.getMessage());
                e.printStackTrace();
        }

        return sum;
    }





}
