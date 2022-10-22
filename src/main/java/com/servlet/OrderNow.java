package com.servlet;

import com.model.entity.Order;
import com.model.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "order-now", value = "/order-now")
public class OrderNow extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try(PrintWriter writer = response.getWriter()){


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


            } else {
                response.sendRedirect("login.jsp");

            }

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doGet(request,response);
    }
}
