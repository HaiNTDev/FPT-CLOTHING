<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Product Detail</title>
        <link rel="stylesheet" href="./css/CreateAccountPage.css">
        <!-- You can include additional CSS files for specific styling if needed -->
    </head>
    <body>
        <div class="container">
            <h2>Product Detail</h2>
            <!-- Your product details can go here -->
            
            <%!
                String action;
            %>
            
            <%
                action = (String) request.getParameter("action");
                
                if (action.equals("productdetailadmin")) {
            %>
            
            <div class="product-details">
                <img src="${requestScope.product.urlimage}" alt="Item 1">
                <p>Product Name: ${requestScope.product.nameProduct}</p>
                <p>Price: ${requestScope.product.price}</p>
                <p>Description: ${requestScope.product.description}</p>
                <form action="admin">
                    <input type="hidden" name="action" value="productedit" />
                    <input type="hidden" name="productid" value="${requestScope.product.productID}">
                    <input type="submit" value="Edit" />
                </form>
            </div>
                    
            <%
                
            } else if (action.equals("create")) {
                
                out.print("<form action=admin>"
                        + "Name:            <input type=text name=productname>"
                        + "Descrription:    <input type=text name=description>"
                        + "Category:        <input type=text name=category>"
                        + "Price:           <input type=text name=productprice>"
                        + "Quantity:        <input type=text name=productquantity>"
                        + "<input type=hidden name=action value=productinsert>"
                        + "<input type=subbmit value=Insert>"
                        + "</form>");

            } else if (action.equals("productdetailcustomer")) {
            
            %>
            
            <div class="product-details">
                <img src="${requestScope.product.urlimage}" alt="Item 1">
                <p>Product Name: ${requestScope.product.nameProduct}</p>
                <p>Price: ${requestScope.product.price}</p>
                <p>Description: ${requestScope.product.description}</p>
                <p>Category: ${requestScope.product.category}</p>
                <p>Quantity available: ${requestScope.product.quantity}</p>
            </div>
            
            <%
                }
            %>

            <form action="customer">
                <input type="hidden" name="action" value="addtocart" />
                <input type="hidden" name="productid" value="${requestScope.product.getProductID()}" />
                order quantity:<input type="text" name="orderquantity" value="" />
                <input type="submit" value="Add" />
            </form>

            
        </div>
    </body>
</html>
