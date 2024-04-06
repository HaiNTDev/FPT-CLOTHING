<%@page import="java.util.ArrayList"%>
<%@page import="Order.OrderDTO"%>
<%@page import="OrderDetail.OrderDetailDTO"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Confirm Order</title>
    <link rel="stylesheet" href="./css/confirm.css">
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
    <%! 
        String action = "";
        OrderDTO order = new OrderDTO();
        List<OrderDetailDTO> list = new ArrayList<OrderDetailDTO>();
    %>
    
    
    
    <%
        action = (String) request.getAttribute("action");
        
        
        if (action.equals("showorderdetail")) {
            
        out.println("orderID" + order.getAccountID() );
        out.println("orderDate" + order.getOrderDate());
        out.println("ordertotal" + order.getTotalPrice());
        
    list = (List<OrderDetailDTO>)request.getAttribute("orderdetaillist");
        
    
    for (OrderDetailDTO  orderdetaillist: list) {
             out.print("<td>"
                             +"<td>" + orderdetaillist.getOrderId()+ "</td> " 
                            + "<td>" + orderdetaillist.getProductId()+ "</td> "
                            +"<td>" + orderdetaillist.getOrderedQuatity()+ "</td> "
                            +"<td>" + orderdetaillist.getUnitPrice()+ "</td> "
                            +"<td>" + orderdetaillist.getTotalPrice()+ "</td> "
                            + " </td>"     );
           
       
        }
    }else{
    %>
    <div class="confirmation-container">
        <h2>Order Confirmation</h2>
        <div id="order-details">
            <p><strong>Address:</strong>${sessionScope.usersession.address}</p>
            <p><strong>Total Price:</strong>${requestScope.total}</p>
            
            <p><strong>Products:</strong></p>
            
            <ul>
                <c:forEach var="item" items="${requestScope.productincart}">
                    
                <li>
                    <img src="<c:out value="${product.urlimage}"/>" 
                         alt="<c:out value="${product.nameProduct}"/>" style="max-width: 50px; max-height: 50px;">
                    <c:out value="${product.name}"/>
                </li>
                    
                </c:forEach>
                
            
            </ul>
        <p><strong>Payment Method:</strong></p>
<label>
    <input type="radio" name="paymentMethod" value="banking" checked> Banking
</label>
<label>
    <input type="radio" name="paymentMethod" value="cash"> Cash
</label>

        </div>
            <form action="customer">
                <input type="hidden" name="action" value="viewcart" />
                <input type="submit" value="Go Back" name="Go Back" />
            </form>
        
        <form action="customer">
            <input type="hidden" name="action" value="confirmorder" />
            <button type="submit">Confirm Order</button>
        </form>
    </div>
        <%}%>
    <footer>
        
        <div class="contact-info">
            <div class="contact-item">
                <p>Phone: +1234567890</p>
            </div>
            <div class="contact-item">
                <p>Email: info@example.com</p>
            </div>
            <div class="contact-item">
                <p>Address: 123 Main Street, City, Country</p>
            </div>
        </div>
        <div class="copyright">
            <p>&copy; 2023 Guest's Homepage. All rights reserved.</p>
        </div>
    </footer>
</body>
</html>