<%@page import="Account.AccountDTO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Account Details</title>
    <link rel="stylesheet" href="./css/CreateAccountPage.css">
    <!-- You can include additional CSS files for specific styling if needed -->
</head>
<body>
    <div class="container">
        <h2>Account Details</h2>
        
        <form action="customer">
            <input type="hidden" name="action" value="editaccount" />
            
            <label for="username">Username:${sessionScope.usersession.username}</label>
            
            <label for="password">Password:${sessionScope.usersession.password}</label>

            <label for="email">Email:${sessionScope.usersession.gmail}</label>

            <label for="phoneNumber">Phone Number:${sessionScope.usersession.phoneNum}</label>

             <label for="address">Address:${sessionScope.usersession.address}</label>
             
            <button type="submit" >Edit</button>
        </form>
    </div>
</body>
</html>
