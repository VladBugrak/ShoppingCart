<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="com.connection.DBConnection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.model.entity.User" %>
<%@ page import="com.model.dao.ProductDao" %>
<%@ page import="com.model.entity.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="com.model.entity.Cart" %>
<%@ page import="java.util.ArrayList" %>
<%
    User auth = (User) request.getSession().getAttribute("auth");
    if(auth!=null){
        request.setAttribute("auth",auth);
    }

    ProductDao productDao = new ProductDao(DBConnection.getConnection());
    List<Product> productList = productDao.getAllProducts();

    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    if(cart_list != null){
        request.setAttribute("cart_list", cart_list);
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

<%--&lt;%&ndash;<% out.print(DBConnection.getConnection()); %>&ndash;%&gt;--%>
<%--<% try {--%>
<%--    response.getWriter().print(DBConnection.getConnection().toString());--%>
<%--} catch (ClassNotFoundException e) {--%>
<%--    response.getWriter().print(e.toString());--%>
<%--} catch (SQLException e) {--%>
<%--    response.getWriter().print(e.toString());--%>
<%--} %>--%>


<div class="container">
    <div class="card-header my-3">All Products</div>

    <div class="row">
        <%
            if(!productList.isEmpty()) {
                for (Product product : productList) {
        %>
        <div class="col-md-3 my-3">
            <div class="card w-100" style="width: 18rem;">
                <img class="card-img-top" src="product-image/<%= product.getImage()%>" alt="Card image cap">
                <div class="card-body">
                    <h5 class="card-title"><%= product.getName()%></h5>
                    <h6 class="price">Price: $<%= product.getPrice()%></h6>
                    <h6 class="category"> Category: <%= product.getCategory()%></h6>
                    <div class="mt-3 d-flex justify-content-between">
                        <a href="add-to-cart?id=<%= product.getId() %>" class="btn btn-dark">Add to Cart</a>
                        <a href="#" class="btn btn-primary">Buy Now</a>
                    </div>
                </div>
            </div>
        </div>

        <%
                }
            }
        %>


    </div>
</div>


<%@ include file="footer.jsp"%>


</body>
</html>