package com.servlet;

import com.model.dao.OrderDao;
import com.model.entity.Cart;
import com.model.entity.Order;
import com.model.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.connection.DBConnection;

@WebServlet(name = "cart-check-out", value = "/cart-check-out")
public class CheckOutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try(PrintWriter printWriter = response.getWriter()){

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();

            //retrieve all cart products
            ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
            //user authentication
            User user = (User) request.getSession().getAttribute("auth");

            //check user and cart list
            if(cart_list != null && user != null){
                for(Cart c:cart_list){
                    //prepare the order object
                    Order order = new Order();
                    order.setId(c.getId());
                    order.setUserID(user.getId());
                    order.setQuantity(c.getQuantity());
                    order.setDate(formatter.format(date));

                    //instantiate te dao class
                    OrderDao orderDao = new OrderDao(DBConnection.getConnection());
                    //calling the insert method
                    boolean result = orderDao.insertOrder(order);
                    if(!result) break;

                }

                cart_list.clear();
                response.sendRedirect("orders.jsp");

            } else {
                if(user == null){
                    response.sendRedirect("login.jsp");
                }
                response.sendRedirect("cart.jsp");


            }

        } catch(Exception e){
            e.printStackTrace();
        }



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
