<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="com.connection.DBConnection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.model.entity.User" %>
<%
    User auth = (User) request.getSession().getAttribute("auth");
    if(auth!=null){
        request.setAttribute("auth",auth);
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>cart.jsp</title>
    <%@ include file="head.jsp" %>

</head>
<body>

<%@ include file="navbar.jsp"%>
<%@ include file="footer.jsp"%>
</body>
</html>