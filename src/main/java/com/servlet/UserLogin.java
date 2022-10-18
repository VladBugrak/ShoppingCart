package com.servlet;

import com.connection.DBConnection;
import com.model.dao.UserDao;
import com.model.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet(name = "user_login", value = "/user-login")
//@WebServlet("/user-login")
public class UserLogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter printWriter = response.getWriter()) {

            String email = request.getParameter("login-email");
            String password = request.getParameter("login-password");

            try {
                UserDao userDao = new UserDao(DBConnection.getConnection());
                User user = userDao.userLogin(email, password);

                if(user != null){
                    //printWriter.print("user login");
                    request.getSession().setAttribute("auth",user); //зберігаємо атрибут в сесію
                    response.sendRedirect("index.jsp");
                } else {
                    printWriter.print("user login failed");
                }

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                printWriter.print("something went wrong");
            }


        }
    }
}
