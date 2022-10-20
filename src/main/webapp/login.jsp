<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import="com.model.entity.User" %>
<%@ page import="com.model.entity.Cart" %>
<%@ page import="java.util.ArrayList" %>
<%
    User auth = (User) request.getSession().getAttribute("auth");
    if(auth!=null){
        request.setAttribute("auth",auth);
        response.sendRedirect("index.jsp");
    }
    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    if(cart_list != null){
        request.setAttribute("cart_list", cart_list);
    }
%>


<!DOCTYPE html>
<html>
<head>
    <title>login.jsp</title>
    <%@ include file="head.jsp" %>
</head>
<body>

<%@ include file="navbar.jsp"%>
<%@ include file="footer.jsp"%>


<div class="container">
    <div class="card w-50 mx-auto my-5">
        <div class="card-header text-center">User Login</div>
        <div class="card-body">
            <form action="user-login" method="post">
<%--                <form action="${pageContext.request.contextPath}/user-login" method="post">--%>

                <div class="form-group">
                    <label>Email Address</label>
                    <input type="email" class="form-control" name="login-email" placeholder="Enter Your Email" required>
                </div>

                <div class="form-group">
                    <label>Password</label>
                    <input type="password" class="form-control" name="login-password" placeholder="********" required>
                </div>

                <div class="text-center">
                    <button type="submit" class="btn-primary">Login</button>
                </div>


            </form>
        </div> <%--class="card-body"--%>
    </div> <%--class="card w-50 mx-auto my-5"--%>
</div>  <%--class="container"--%>




<%--<%@ include file="footer.jsp"%>--%>
<%--<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>--%>
<%--<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"></script>--%>
<%--<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"></script>--%>

</body>
</html>