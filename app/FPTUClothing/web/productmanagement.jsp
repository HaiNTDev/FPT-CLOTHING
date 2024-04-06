<%@page import="Product.ProductDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./css/admin.css">
    <title>Admin Homepage</title>
</head>
<body>

    <header>
        <h1>Admin Dashboard</h1>
        <button class="username-btn" onclick="openUserProfile()">${sessionScope.usersession.username}</button>
        <h2><a class="logout-link" href="login?action=logout">Logout</a></h2>
    </header>

    <nav>
        <a href="admin?action=customerlist">Customer Management</a>
        <a href="admin?action=orderlist">Order Management</a>
    </nav>

    <main>
        <%! List<ProductDTO> list = new ArrayList<>();%>
            <%
                list = (List<ProductDTO>) request.getAttribute("productlist");
                
                for (ProductDTO p : list) {
                    out.print("<div class=cart-item>"
                            + "<img src=" + p.getUrlimage()+ "alt=Item 1>"
                            + "<h3><a href=admin?action=productdetail&productid=" + p.getProductID() + ">" + p.getNameProduct() + "</a></h3>"
                            + "<p>Price: " + p.getPrice() + "</p>"
                            + "<p>Quantity: " + p.getQuantity() + "</p>"
                            + "</div>");
                    out.print("<form action=admin>"
                                    + "<input type=hidden name=action value=productdelete>"
                                    + "<input type=hidden name=productid value=" + p.getProductID() + ">"
                                            +"<input type=submit value=Delete>"
                                    + "</form>");
                }
            %>
            <form action="admin">
                <input type="hidden" name="action" value="productcreate" />
                <input type="submit" value="Create" />
            </form>
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