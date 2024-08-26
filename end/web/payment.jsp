<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.Theater, model.Movie, model.Showtime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.text.SimpleDateFormat" %>
<% 
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    String seat = request.getParameter("seat");
    session.setAttribute("seats", seat);
    
    String name = request.getParameter("name");
    String phone = request.getParameter("phone");
    String email = request.getParameter("email");
    String totalPrice = request.getParameter("totalPrice");
    session.setAttribute("totalprice", totalPrice);
    Theater theater = (Theater) session.getAttribute("selectedTheater");
    String theaterName = theater.getName();
    Movie movie = (Movie) session.getAttribute("selectedMovie");
    String movieName = movie.getTitle();
    Showtime showtime = (Showtime) session.getAttribute("selectedShowtime");
    String showtimeName = showtime.getStartTime().format(formatter);
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
                <h2>Pay for tickets</h2>
            </div>

            <form id="paymentForm" action="ConfirmOrder" class="info-form" method="post">
                <div class="info-content">
                    <div class="row-1">
                        <h2>Booking information</h2>
                        <p><label>Full name: </label><span id="ticketFullName">${name}</span></p>
                        <p><label>Phone number: </label><span id="ticketPhoneNumber">${phone}</span></p>
                        <p><label>Email: </label><span id="ticketEmail"></span>${email}</p>
                        <p><label>Film: </label><label></label><span id="filmName"> <%= movieName %> </span></p>
                        <p><label>Theater: </label><label></label><span id="theater"><%= theaterName %></span></p>
                        <p><label>Show time: </label><label></label><span id="showTime"><%= showtimeName %> - ${sessionScope.selectedDate}</span></p>
                        <p><label>Sit Position: </label><span id="ticketSitPosition">${seat}</span></p>
                        <p><label>Total payment: </label><span id="totalPrice">${totalPrice} VND</span></p>                   
                    </div>
                    <div class="row-2">
                        <div class="payment-title">
                            <h2>Payment</h2>
                            <div class="payment-method">
                                <h3> Select a <span class="payment-method-weight">Payment </span> method </h3>
                            </div>
                            <div class="input-box">
                                <label class="radio-inline">
                                    <input type="radio" name="payment-gate" value="Payment_Card" checked="checked">
                                    Pay by Internet Banking/VISA
                                </label>
                                <div class="imgName">
                                    <img src="img/qrCode.jpg" alt="">
                                </div>
                            </div>
                            <div class="input-box">
                                <label class="radio-inline">
                                    <input type="radio" name="payment-gate" value="e-wallet">
                                    Pay by e-wallet
                                </label>
                                <div class="imgName">
                                    <img src="img/momo.png" alt="">
                                </div>
                            </div>
                        </div>  
                    </div>
                </div>
                <button id="confirmOrder">CONFIRM ORDER</button>   
            </form> 

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
            <script>
                document.getElementById('paymentForm').onsubmit = function (event) {
                    var selectedPaymentMethod = document.querySelector('input[name="payment-gate"]:checked').value;
                    if (!selectedPaymentMethod) {
                        Swal.fire({
                            icon: 'warning',
                            title: 'Chưa chọn phương thức thanh toán',
                            text: 'Vui lòng chọn một phương thức thanh toán.',
                            confirmButtonText: 'OK'
                        });
                        return false; // Prevent form submission
                    }
                };
            </script>
    </body>
</html>
