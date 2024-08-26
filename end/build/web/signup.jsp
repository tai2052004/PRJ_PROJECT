<%-- 
    Document   : singup.jsp
    Created on : Jul 5, 2024, 3:18:41 PM
    Author     : ngoct
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/stylehome6.css"/>
        <link rel="stylesheet" href="css/loginstyle.css"/>
        <link rel="stylesheet" href="boostrap/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    </head>
    <body>
        <% 
    if (request.getParameter("error") != null && request.getParameter("error").equals("invalid")) { %>
        <div class="alerts">
            <div class="alert alert-danger animated bounceInRight">
                <div class="icon pull-left">
                    <i class='fa fa-exclamation-triangle fa-2x'></i>
                </div>
                <div class="copy">
                    <h4>ERROR</h4>
                    <p>Username has exist!.</p>
                </div>
                <a class="close">
                    <i class="fa fa-times" ></i>
                </a>
            </div>
        </div>
        <% }
        %>
        <div class=" header__logo" >
            <p>CINEMA</p>
        </div>
        <div class="header__button__Register__form wrapper" id="id02">
            <div class="title">
                Register Form
            </div>
            <form action="LoginServlet" method="post" class="animate">
                <div class="field">
                    <input type="text" name="username" value="${u}" required>
                    <label>Email Address</label>
                </div>
                <div class="field">
                    <input type="password" name="password" value="${p}" required>
                    <label>Password</label>
                </div>
                <div class="field">
                    <input type="number" name="tel" value="${t}" required>
                    <label>Number phone</label>
                </div>

                <div class="field">
                    <input type="submit" value="Register">
                    <input type="hidden" name="action" value="sign">
                </div>
            </form>
        </div>
    </body>
</html>
