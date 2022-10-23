package com.servlet;

import com.connection.DBConnection;
import com.model.dao.OrderDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "cancel-order", value = "/cancel-order")
public class CancelOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (PrintWriter printWriter = response.getWriter()) {
            String orderID = request.getParameter("id");
            if(orderID!=null){
                OrderDao orderDao = new OrderDao(DBConnection.getConnection());
                orderDao.cancelOrder(Integer.parseInt(orderID));

            }
            response.sendRedirect("orders.jsp");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
