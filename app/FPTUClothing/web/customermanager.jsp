<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="Account.AccountDTO"%>
<%@page import="java.beans.Customizer"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="./css/CustomerManager.css">
        <title>Customer Manager</title>
    </head>
    <body>

    <header>
        <h1>Customer Manager</h1>
       <button class="username-btn" onclick="openUserProfile()">Username</button>
           <h2> <a class="link-to-another-admin-page" href="#another-admin-page"> Admin Page</a>
        </h2>
        


    </header>


        <main>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Password</th>
                        <th>Email</th>
                        <th>Address</th>
                        <th>Phone</th>
                    </tr>
                </thead>
                <tbody>
                    <%! List<AccountDTO> list = new ArrayList<>();%>
                    <%
                        list = (List<AccountDTO>) request.getAttribute("list");
                        for (AccountDTO acc : list) {
                            out.print("<tr>"
                                    + "<td>" + acc.getAccountID() + "</td>"
                                    + "<td>" + acc.getUsername() + "</td>"
                                    + "<td>" + acc.getPassword()+ "</td>"
                                    + "<td>" + acc.getGmail() + "</td>"
                                    + "<td>" + acc.getAddress() + "</td>"
                                    + "<td>" + acc.getPhoneNum() + "</td>"
                                    + "</tr>");
                            out.print("<form action=admin>"
                                    + "<input type=hidden name=action value=customerdelete>"
                                    + "<input type=hidden name=id value=" + acc.getAccountID() + ">"
                                            +"<input type=submit value=Delete>"
                                    + "</form>");
                        }
                    %>
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