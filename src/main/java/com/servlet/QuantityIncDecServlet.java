package com.servlet;

import com.model.entity.Cart;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "quantity-inc-dec", value = "/quantity-inc-dec")
public class QuantityIncDecServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter printWriter = response.getWriter();
        response.setContentType("text/html; charset=UTF-8");
        try (PrintWriter out = response.getWriter();) {
            String action = request.getParameter("action");
            int id = Integer.parseInt(request.getParameter("id"));
            ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");

            if (action != null && id >= 1) {

                for (Cart c : cart_list) {
                    if(c.getId() == id) {

                        int quantity = c.getQuantity();
                        if (action.equals("inc")) {
                            quantity++;
                        } else if (quantity > 0 && action.equals("dec")) {
                            quantity--;
                        }

                        c.setQuantity(quantity);
                        response.sendRedirect("cart.jsp");
                    }

                }

            }
        }
    }
}
