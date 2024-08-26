<%-- 
    Document   : info
    Created on : Jun 16, 2024, 11:13:06 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    String seat = request.getParameter("chosenSeats");
    String totalPrice = request.getParameter("totalPrice");
//    String movie = request.getParameter("movie");
//    String date = request.getParameter("date");
//    String time = request.getParameter("time");
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Confirm information</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link href="boostrap/css/bootstrap.min_1.css" rel="stylesheet">
        <link rel="stylesheet" href="./css/info_style.css?v=1.0">
    </head>
    <body>
    <!-- Header -->
        <div class="row header">
            <div class="col-md-4">
            </div>
            <div class="col-md-4 header__logo">
                <p>CINEMA</p>
            </div>
            <div class="col-md-4 header-search">
                <form action="newjsp.jsp" class="search">
                    <input type="text" placeholder="Nhập từ khóa tìm kiếm" name="search">
                    <button type="submit"><i class="fa fa-search"></i></button>
                </form>
            </div>
        </div>
        <div class="body">
            <div class="video-background">
                <video autoplay muted loop id="bgVideo">
                    <source src="img/background.mp4" type="video/mp4">
                </video>
            </div>
            <div class="first-body">
                <div class="advertise-img">
                    <img src="img/GTCN.PNG">
                </div>
            </div>
            
            <div class="second-body info">
                <h1>Xác nhận thông tin khách hàng</h1>
                <form action="ticketServlet" method="get" class="ticketForm">
                    <div class="input-box">
                    <label for="name">FULL NAME</label>
                    <input type="text" id="name" name="name" placeholder="Enter your full name" required>
                </div>
                <div class="input-box">
                    <label for="phone">PHONE NUMBER</label>
                    <input type="text" id="phone" name="phone" placeholder="Enter your phone" required>
                </div>
                <div class="input-box">
                    <label for="email">EMAIL</label>
                    <input type="email" id="email" name="email" placeholder="Enter your email" required>
                </div>
                <div class="input-box">
                    <label for="cccd">CCCD</label>
                    <input type="text" id="cccd" name="cccd" placeholder="Enter your cccd" required>
                </div>
                <input type="hidden" name="seat" value="<%= seat %>">
                <input type="hidden" name="totalPrice" value="<%= totalPrice %>">
                <button type="submit" class="button">Submit</button>
                </form>
            </div>
            
            <div class="third-body">
                <div class="advertise-img">
                    <img src="img/mongvuot.PNG">
                </div>
            </div>
        </div>    
        <!-- Footer -->
        <div class="footer">
            <div class="footer__aboutus">
                <h2>ABOUT US</h2>
                <p>CÔNG TY TNHH Hehe <br> Đăng ký lần đầu : vào ngày đăng ký <br> Địa chỉ: Đố biết ở đâu <br>
                    Copyright : @WriteKhoa</p>
            </div>
            <div class="footer__partner">
                <h2>PARTNER</h2>
                <img src="img/logomomo.png">
                <img src="img/logoshopee.jpg">
                <img src="img/logocine.png">               
            </div>
        </div>
        <script src="javascript/myScript.js?v=1.0"></script>
    </body>
</html>