<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>


<%--<%@ page import="com.connection.DBConnection" %>--%>
<%--<%@ page import="java.sql.SQLException" %>--%>
<%@ page import="com.model.entity.User" %>
<%@ page import="com.model.entity.Cart" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="com.model.dao.OrderDao" %>
<%@ page import="com.connection.DBConnection" %>
<%@ page import="com.model.entity.Order" %>
<%@ page import="java.util.List" %>
<%
    DecimalFormat decimalFormat = new DecimalFormat("#.##");
    request.setAttribute("decimalFormat",decimalFormat);

    List<Order> ordersList = null;

    User auth = (User) request.getSession().getAttribute("auth");
    if(auth!=null){
        request.setAttribute("auth",auth);
        OrderDao orderDao = new OrderDao(DBConnection.getConnection());
       ordersList= orderDao.userOrders(auth.getId());

    } else {
//        response.sendRedirect("login.jsp");
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

<div class="container">
    <div class = "card-header my-3"> All Orders</div>
    <table class = "table table-light">
        <thead>
        <tr>
            <th scope="col">Date</th>
            <th scope="col">Name</th>
            <th scope="col">Category</th>
            <th scope="col">Quantity</th>
            <th scope="col">Price</th>
            <th scope="col">Cancel</th>

        </tr>
        </thead>
        <tbody>
        <%
            if(ordersList != null){
                for(Order order:ordersList){
                    %>
                        <tr>
                            <td><%=order.getDate()%></td>
                            <td><%=order.getName()%></td>
                            <td><%=order.getCategory()%></td>
                            <td><%=order.getQuantity()%></td>
                            <td><%=decimalFormat.format(order.getPrice())%></td>
                            <td>
                                <a class = "btn btn-sm badge-danger" href="cancel-order?id=<%=order.getOrderId()%>">
                                    Cancel
                                </a>
                            </td>

                        </tr>
                    <%
                }
            }
        %>

        </tbody>


    </table>
</div>



<%@ include file="footer.jsp"%>

</body>
</html>