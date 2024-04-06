<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Cart</title>
    <link rel="stylesheet" href="./css/View.css">
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
</head>
<body>

<div class="container">
    <h2>View Cart</h2>
    
    
    <table>
        <thead>
            <tr>
                <th>Product</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Total</th>
                <th>Action</th>
            </tr>
        </thead>
         <tbody>
            
             
             <c:forEach var="item" items="${requestScope.productlist}">
                 
                <tr>
                    <td>
                        <img src="<c:out value="${item.urlimage}" />" alt="<c:out value="${item.nameProduct}" />">
                        <c:out value="${item.nameProduct}" />
                    </td>
                    <td><c:out value="${item.price}" /></td>
                    <td><c:out value="${item.orderQuantity}" /></td>
                    <td><c:out value="${item.category}" /></td>
                    <td>
                        <form action="customer">
                            <input type="hidden" name="productid" value=${item.productID} />
                            <input type="hidden" name="action" value="removefromcart" />
                            <input type="submit" value="Remove" name="Remove" />
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <form action="customer">
        <input type="hidden" name="action" value="vieworderdetail" />
        <input type="submit" value="Buy" name="Buy" />
    </form>
</div>

    <jsp:include page="customermenu.html" flush="true"/>
</body>
</html>