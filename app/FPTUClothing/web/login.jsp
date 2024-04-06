
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login Page</title>
        <link rel="stylesheet" href="./css/LoginPage.css">
    </head>
    <body>
        <div class="container">
            <h2>Login</h2>
            <form action="login" method="post">

                <label for = "username">Username:</label>
                <input name = "username" id = "username" type = "text">
                <label for = "password">Password:</label>
                <input name = "password" id = "username" type = "password">
                <p>Don't have an account? <a href="./createaccount.jsp">Create account</a></p>
                <input type="submit" value="Login" name="Login" />
            </form>
            <%!
                String err;
                String mess;
            %>
            <%
                err = (String) request.getAttribute("error"); 
                mess = (String) request.getAttribute("message"); 
            if (err != null) {
                out.print("<h3>"+err+"</h3>"); 
            } else if (mess != null) {
                    out.print("<h3>"+mess+"</h3>");
                }
            %>
        </div>
    </body>
</html>