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
    <title>index.jsp</title>
    <%@ include file="head.jsp" %>

</head>
<body>

<%@ include file="navbar.jsp"%>

<%--<% out.print(DBConnection.getConnection()); %>--%>
<% try {
    response.getWriter().print(DBConnection.getConnection().toString());
} catch (ClassNotFoundException e) {
    response.getWriter().print(e.toString());
} catch (SQLException e) {
    response.getWriter().print(e.toString());
} %>

<%@ include file="footer.jsp"%>


</body>
</html>