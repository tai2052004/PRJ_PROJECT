<%-- 
    Document   : book_seat
    Created on : Jun 16, 2024, 11:12:12 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.*,java.util.*,dao.*" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="model.Theater, model.Movie, model.Showtime" %>
<% 
    Movie movie = (Movie) session.getAttribute("selectedMovie");
    Showtime showtime = (Showtime) session.getAttribute("selectedShowtime");
    List<Seat> bookedSeats = SeatDB.getBookedSeatsByShowtime(showtime.getId());
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Booking seat</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link href="boostrap/css/bootstrap.min_1.css" rel="stylesheet">
        <link rel="stylesheet" href="./css/style1.css?v=1.0">
        <script src='https://cdn.jsdelivr.net/npm/sweetalert2@11'></script>
        
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
        <div class="book">
            <div class="left">
                <div class="left-content"> 
                    <%
                    out.println("<img src='" + movie.getImageUrl() + "' alt='' class='left-img'>");
                    out.println("<div class='cont'>");
                    
                    out.println("<h6>Directed by</h6>");
                    out.println("<p>" + movie.getDirector() + "</p>");
                    
                    out.println("<h6>Starring</h6>");
                    out.println("<p>" + movie.getStarring() + "</p>");
                    
                    out.println("<h6>Description</h6>");
                    out.println("<p>" + movie.getDescription() + "</p>");
                    out.println("</div>");
                    %>
                </div>
                <div class="left-backhome">
                    <button type="button" class="back-home" id="back-home-button" onclick="window.location.href = 'newjsp.jsp';">
                    <span class="circle" aria-hidden="true">
                        <span class="icon arrow"></span>
                    </span>
                    <span class="button-text">Back Now</span>
                    </button>
                </div>
            </div>	
            <div class="right">
                <div class="video-background">
                    <video autoplay muted loop id="bgVideo">
                        <source src="img/background-book.mp4" type="video/mp4">
                    </video>    
                </div>
                <div class="head_time">
                    <%
                        out.println("<h1>" + movie.getTitle() + "</h1>");
                    %>
                    
                    <div class="time">
                        <h6><i class="bi bi-clock"></i> 149 minutes</h6>
                    </div>
                </div>
    <!-- Date Booking -->
                <div class="date_type">
                    <div class="date_type_left">
                        <h5>Date</h5>
                        <div class="card_month">
                        <%
                            List<Date> dates = ShowtimeDB.getShowDates();
                            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM");
                            
                            for (int i = 0; i < dates.size(); i++) {
                                String formattedDate = sdf.format(dates.get(i));
                                if(showtime.getShowDate().equals(dates.get(i))) {
                                    out.println("<li><h4 class='h4_active'>" + formattedDate + "</h4><li>");
                            }else{
                                    out.println("<li><h4>" + formattedDate +  "</h4><li>");
                                }     
                            }
                        %>
                        </div> 
                    </div>
                    <div class="date_type_right">
                        <h5>Show Time</h5>
                        <div class="showtime">
                        <%
                            String selectedDate = (String) session.getAttribute("selectedDate");
                            int movieName = movie.getId();
                            int selectedTheater = (int) request.getAttribute("selectedTheater");
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                            List<Showtime> showtimes = ShowtimeDB.getShowtimesByMovieAndDay(movieName, selectedDate, selectedTheater);
                            for (Showtime s : showtimes) {
                                LocalDateTime start = s.getStartTime();
                                if(showtime.getStartTime().isEqual(start)) {
                                    out.println("<li><h4 class='h4_active'>" + start.format(formatter) + "</h4></li>");
                                }else {
                                    out.println("<li><h4>" + start.format(formatter) + "</h4></li>");
                                }
                            }    
                        %>
                            
                        </div>
                    </div>
                </div>
            <!-- Screen -->
                <div class="screen">
                    Screen
                </div>
                <div class="chair">
                    <%
                        for (char row = 'A'; row <= 'F'; row++) {
                            out.println("<div class='row'>");
                            out.println("<div>" + row + "</div>");
                            for (int number = 1; number <= 9; number++) {
                                if (!SeatDB.isBooked(row, number, bookedSeats)) {
                                    out.println("<div class='seat'></div>");
                                } else {
                                    out.println("<div class='seat seat-selected'></div>");
                                }
                            }
                            out.println("</div>");
                        }
                    %>
                    <div class="row mt-3">
                        <span class="seat seat-selected"></span> had been order
                        <span class="seat seat-available-form"></span> Not yet
                        <span class="seat seat-chosen-form"></span> Chosen
                    </div>
                </div>
                <form action="ticketServlet" method="post" class="info">
                    <div class="chosen-info">
                            <h5>Ghế đã chọn: <span id="chosen-seats">None</span></h5>
                            <input type="hidden" name="chosenSeatsNumbers" id="chosenSeatNumber">
                            <input type="hidden" name="chosenSeats" id="chosenSeatsInput">
                            <h5>Tổng số tiền: <span id="total-price">0</span> VND</h5>
                            <input type="hidden" name="totalPrice" id="totalPriceInput">
                            <p class="booking-refresh"><a><i class="fa fa-refresh fa-lg"></i> Làm lại</a></p>
                    </div>
                    <button type="submit" class="book-now" id="book-now-button">
                        <span class="circle" aria-hidden="true">
                            <span class="icon arrow"></span>
                        </span>
                        <span class="button-text">Book now</span>
                    </button>
                </form>
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

            <script src="javascp/myScript.js?v=1.0"></script>
            
        </body>
    </html>
