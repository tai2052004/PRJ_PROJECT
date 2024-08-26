<%-- 
    Document   : login
    Created on : Jul 5, 2024, 12:12:23 PM
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
                    <p>Invalid username or password.</p>
                </div>
                <a class="close">
                    <i class="fa fa-times"></i>
                </a>
            </div>
        </div>
        <% }
        %>

        <div class=" header__logo" >
            <p>CINEMA</p>
        </div>
        <div class="header__button__Login__form wrapper" id="id01">
            <div class="title">
                Login Form
            </div>
            <form action="LoginServlet" value="login" method="post" class="animate">
                <div class="field">
                    <input type="text" name="username" value="${u}" required>
                    <label>Email Address</label>
                </div>
                <div class="field">
                    <input type="password" name="password" value="${p}" required>
                    <label>Password</label>
                </div>
                <div class="content">
                    <div class="checkbox">
                        <input type="checkbox"  id="remember-me">
                        <label for="remember-me">Remember me</label>
                    </div>
                    <div class="pass-link">
                        <a href="#">Forgot password?</a>
                    </div>
                </div>
                <div class="field">
                    <input type="submit" value="Login" onlick="checkLoginError()">
                    <input type="hidden" name="action" value="login">
                </div>
                <div class="signup-link"  >
                    Not a member? <a href="signup.jsp">Sign up now</a>
                </div>
                
            </form>

        </div>



    </body>
    <script>
        var formLog = document.getElementById('id01');
        var formRe = document.getElementById('id02');
        function showRegisterForm() {
            formLog.style.display = "none";
            formRe.style.display = "block";
        }


        $('.close').on('click', function () {
            var alertBox = $(this).parent();
            alertBox.removeClass('bounceInRight');
            alertBox.addClass('bounceOutRight');
            alertBox.one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
                alertBox.hide();
            });
        });
    </script>
</html>
