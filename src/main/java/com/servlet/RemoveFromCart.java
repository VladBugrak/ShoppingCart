package com.servlet;

import com.model.entity.Cart;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "remove-from-cart", value = "/remove-from-cart")
public class RemoveFromCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

       try(PrintWriter printWriter = response.getWriter()){
           String id = request.getParameter("id");
//           printWriter.println("id = " + id);

           if(id!=null){
               ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
               if(cart_list != null){
                   for(Cart c:cart_list){
                       if(c.getId()==Integer.parseInt(id)){
                           cart_list.remove(cart_list.indexOf(c));
                           break;
                       }
                   }
                   response.sendRedirect("cart.jsp");
               }
           } else {
               response.sendRedirect("cart.jsp");
           }
       }
    }
}
