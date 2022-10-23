<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>


<%--<%@ page import="com.connection.DBConnection" %>--%>
<%--<%@ page import="java.sql.SQLException" %>--%>
<%@ page import="com.model.entity.User" %>
<%@ page import="com.model.entity.Cart" %>
<%@ page import="java.util.ArrayList" %>
<%
    User auth = (User) request.getSession().getAttribute("auth");
    if(auth!=null){
        request.setAttribute("auth",auth);
    }
    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    if(cart_list != null){
        request.setAttribute("cart_list", cart_list);
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>orders.jsp</title>
    <%@ include file="head.jsp" %>

</head>
<body>

<%@ include file="navbar.jsp"%>
<%@ include file="footer.jsp"%>

</body>
</html>