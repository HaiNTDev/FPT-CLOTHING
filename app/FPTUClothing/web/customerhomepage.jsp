<%@page import="Product.ProductDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="./css/customer.css">
        <title>Customer Home Page</title>
    </head>
    <body>


        <header>
            <h1>Shopping Clothes</h1>
            <nav>
                <ul>
                    <li>              
                        <a class="link-to-view-cart" href="customer?action=viewcart">View Cart</a>  
                    </li>
                    <li><a class="link-to-account-info" href="customer?action=accountdetail&id=${sessionScope.usersession.accountID}">${sessionScope.usersession.username}</a></li>
                    <li><a class="link-to-cus-homePage" href="login?action=logout">Logout</a></li>
                </ul>
            </nav>
        </header>
        <main>
            <%! List<ProductDTO> list = new ArrayList<>();%>
            <%
                list = (List<ProductDTO>) request.getAttribute("productlist");
                for (ProductDTO p : list) {
                    out.print("<div class=cart-item>"
                            + "<img src=" + p.getUrlimage()+ "alt=Item 1>"
                            + "<h3><a href=customer?action=productdetail&productid=" + p.getProductID() + ">" + p.getNameProduct() + "</a></h3>"
                            + "<p>Price: " + p.getPrice() + "</p>"
                            + "<p>Quantity: " + p.getQuantity() + "</p>"
                            + "</div>");
                    
                }
            %>
        </main>

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