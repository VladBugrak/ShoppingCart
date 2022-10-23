package com.servlet;

import com.connection.DBConnection;
import com.model.dao.OrderDao;
import com.model.entity.Cart;
import com.model.entity.Order;
import com.model.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(name = "order-now", value = "/order-now")
public class OrderNow extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try(PrintWriter printWriter = response.getWriter()){


            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();

            User user = (User) request.getSession().getAttribute("auth");
            if(user!= null){
                String productId = request.getParameter("id");
                int productQuantity  = Integer.parseInt(request.getParameter("quantity"));
                if(productQuantity < 1){
                    productQuantity = 1;
                }

                Order order = new Order();
                order.setId(Integer.parseInt(productId));
                order.setUserID(user.getId());
                order.setQuantity(productQuantity);
                order.setDate(formatter.format(date));

                OrderDao orderDao = new OrderDao(DBConnection.getConnection());
                boolean result = orderDao.insertOrder(order);

                if(result){
                    ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
                    if(cart_list != null){
                        for(Cart c:cart_list){
                            if(c.getId()==Integer.parseInt(productId)){
                                cart_list.remove(cart_list.indexOf(c));
                                break;
                            }
                        }
                    }
                    response.sendRedirect("orders.jsp");
                } else {
                    printWriter.print("order failed");
                }


            } else {
                response.sendRedirect("login.jsp");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doGet(request,response);
    }
}
