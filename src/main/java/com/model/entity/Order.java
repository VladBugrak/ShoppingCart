package com.model.entity;

public class Order extends Product{

    private  int orderId;
    private int userID;
    private int quantity;
    private String date;

    public Order(){
    }

    public Order(int orderId, int userID, int quantity, String date) {
        super();
        this.orderId = orderId;
        this.userID = userID;
        this.quantity = quantity;
        this.date = date;
    }


    public Order(int userID, int quantity, String date) {
        super();
        this.userID = userID;
        this.quantity = quantity;
        this.date = date;
    }


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userID=" + userID +
                ", quantity=" + quantity +
                ", date='" + date + '\'' +
                '}';
    }
}
