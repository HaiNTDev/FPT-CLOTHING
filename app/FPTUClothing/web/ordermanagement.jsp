<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./css/CustomerManager.css">
    <title>Order Management</title>
</head>
<body>

    <header>
        <h1>Order Management</h1>
        <button class="username-btn" onclick="openUserProfile()">${sessionScope.usersession.username}</button>
        <h2><a class="link-to-admin-home-page" href="admin?action=productlist"> Admin home page</a></h2>
    </header>

    <main>
        <table>
            <thead>
                <tr>
                    <th>Order ID</th>
                    <th>Total cash</th>
                    <th>Order Date</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="order" items="${requestScope.list}">
                    <tr>
                        <td><c:out value="${order.orderID}" /></td>
                        <td><c:out value="${order.totalPrice}" /></td>
                        <td><c:out value="${order.orderDate}" /></td>
                        <td><c:out value="${order.status}" /></td>
                        <td>
                            <form action="admin">
                                <input type="hidden" name="action" value="orderdelete" />
                                <button class="remove-btn">Remove</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
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