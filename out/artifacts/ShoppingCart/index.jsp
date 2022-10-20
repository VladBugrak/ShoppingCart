<%@ page import="com.connection.DBConnection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    out.print(DBConnection.getConnection().toString());
} catch (ClassNotFoundException e) {
    out.print(e.toString());
} catch (SQLException e) {
    out.print(e.toString());
} %>

<%@ include file="footer.jsp"%>


</body>
</html>