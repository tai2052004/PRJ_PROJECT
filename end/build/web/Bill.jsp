<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.Theater, model.Movie, model.Showtime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="model.Bookings" %>
<% 
    Bookings b = (Bookings) session.getAttribute("newBooking");
    String seat = (String) session.getAttribute("seats");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Payment</title>
        <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link href="boostrap/css/bootstrap.min_1.css" rel="stylesheet">
        <link rel="stylesheet" href="./css/payment.css?v=1.0">
    </head>
    <body>
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

        <div class="info">
            <div class="info-title">
                <h2>Your Bill</h2>
            </div>


            <div class="info-content">
                <div class="row-1">
                    <h2>                Booking information</h2>
                    <p><label>Your ID: </label><span id="ticketPhoneNumber">     <%= b.getUser_id() %></span></p>
                    <p><label>Show Time ID: </label><span id="ticketEmail">     <%= b.getShowtime_id() %></span></p>
                    <p><label>Seat ID: </label><span id="filmName">     <%= b.getSeat_id() %></span></p>
                    <p><label>Status: </label><span id="theater">       <%= b.getStatus() %></span></p>
                    <p><label>Payment Method: </label><span id="showTime">      <%= b.getPayment_method() %></span></p>
                    <p><label>Booking Date: </label><span id="ticketSitPosition">       <%= b.getBooking_date() %></span></p>
                    <p><label>Price: </label><span id="totalPrice">     <%= b.getPrice() %></span></p>                   
                </div>


            </div>
            <button id="confirmOrder" onclick="window.location.href = 'newjsp.jsp'">Back Home</button>   


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

    </body>
</html>
