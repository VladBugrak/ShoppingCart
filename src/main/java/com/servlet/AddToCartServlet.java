package com.servlet;

import com.model.entity.Cart;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "add-to-cart", value = "/add-to-cart")
public class AddToCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try(PrintWriter out = response.getWriter()){
            ArrayList<Cart> cartList = new ArrayList<Cart>();

            int id = Integer.parseInt(request.getParameter("id"));
            Cart cart = new Cart();
            cart.setId(id);
            cart.setQuantity(1);

            HttpSession session = request.getSession();
            ArrayList<Cart> cartListFromSession = (ArrayList<Cart>) session.getAttribute("cart-list");


            if(cartListFromSession==null){
                cartList.add(cart);
                session.setAttribute("cart-list",cartList);
               // out.println("session created and added to the list");
               response.sendRedirect("index.jsp");
            } else {
                cartList = cartListFromSession;
                boolean exist = false;

                for(Cart c:cartListFromSession){
                   if(c.getId()== id){
                       exist = true;
                      out.println("""
                   <h3 style='color:crimson; text-align:center'>Item already exist in Cart.
                   <a href = 'cart.jsp'>Go to Cart Page</a>
                   </h3>
                   """);

                   }
                }
                if(!exist){
                    cartList.add(cart);
//                    out.println("product added");
                    response.sendRedirect("index.jsp");
                }

                for(Cart c:cartList){
                    out.println(c.getId());
                }


            }



        } catch (Exception e){
            e.printStackTrace();
        }

    }


}
