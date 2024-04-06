<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Account</title>
    <link rel="stylesheet" href="./css/CreateAccountPage.css">
</head>
<body>
    <div class="container">
        
        <%!String action = "";%>
        <%
            action = (String) request.getAttribute("action");
            if(action.equals("editaccount")){
                
        %>
        <h2>Update Account</h2>
        
        <form action="customer" method="post">
            
            <input type="hidden" name="action" value="updateaccount" />
            
            <label for="username">Username:</label>
            <input type="text" name="username" required>

            <label for="password">Password:</label>
            <input type="password" name="password" required>

            <label for="gmail">Gmail:</label>
            <input type="text" name="gmail" value="" required/>
            
            <label for="address">Adress:</label>
            <input type="text" name="address" value="" required/>
            
            <label for="phoneNumber">Phone Number:</label>
            <input type="text" name="phonenumber" value="" required/>
            
            <input type="submit" value="Update" name="Update" />
            <button type="submit">Save</button>
            
        </form>
        
        <%
            }else{
        %>
        <h2>Create Account</h2>
        
        <form action="create" method="post">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>

            <label for="confirmPassword">Confirm Password:</label>
            <input type="password" id="confirmPassword" name="confirmPassword" required>
            
            <label for="gmail">Gmail:</label>
            <input type="text" name="gmail" value="" required/>
            
            <label for="address">Adress:</label>
            <input type="text" name="address" value="" required/>
            
            <label for="phoneNumber">Phone Number:</label>
            <input type="text" name="phonenumber" value="" required/>
            
            <button type="submit">Save</button>
            
        </form>
        
        <%}%>
    </div>
</body>
</html>