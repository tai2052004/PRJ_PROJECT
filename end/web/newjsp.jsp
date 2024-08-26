<%@page contentType="text/html" import="model.*,java.util.*,dao.*" pageEncoding="UTF-8"%>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
    
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đặt Vé</title>
        <link rel="stylesheet" href="css/newjsp3.css?v=1.0"/>
        <link rel="stylesheet" href="boostrap/css/bootstrap.min.css"/>
    </head>
    <body>
        <div class="overlay__login" id="overlay__login"></div>
        <div class="header">
            <div class=" header__logo">
                <a href="index.jsp"><p>CINEMA</p></a>
            </div>
            <a  id="buttonavatar" class="avatar_detail"  href="#" onclick="showNavbarContent()">
                <div class="login-avatar">
                    <div class="login-avatar-img">
                        <img src="img/avatar.jpg">
                    </div>
                    <div class="login-username">
                        <% 
                        String username = null;
                        Users us = (Users) session.getAttribute("user");
                        if ( us != null)
                        {
                            username = us.getUser_name();
                        }
                    
                        %>
                        <p style="color: #7abaff; font-size: 15px;" name="userName" value=""><%= username %></p>
                    </div>
                </div>
            </a>
            <div id="navbar" class="header__navbar">
                <ul>
                    <li>
                        <a href="userDetail.jsp">User info</a>

                    </li>
                    <form action="logoutServlet" method="post" id="formA2">
                        <li>
                            <a id="a2" href="#" onclick="logout()">Log out</a>    
                        </li>
                    </form>

                </ul>
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
                    <img src="img/mongvuot.PNG">
                </div>
                <div class="advertise-img">
                    <img src="img/ndkq.PNG">
                </div>
            </div>

            <div class="second-body">
                <div class="header-1">
                    <p>CHOOSE THEATER</p>
                </div>
                <div>
                    <form id="selectionForm" class="theater-position" action="selectionServlet" method="post">
                        <%
                            String selectedTheaterStr = (String) request.getAttribute("selectedTheater");
                            String selectedDate = (String) request.getAttribute("selectedDate");
                            int selectedTheater = selectedTheaterStr != null ? Integer.parseInt(selectedTheaterStr) : 1;
                            if (selectedDate == null) {
                                selectedDate = "2024-07-11";
                            }
                        %>
                        <input type="hidden" id="selectedTheater" name="selectedTheater" value="<%= selectedTheater %>">
                        <input type="hidden" id="selectedDate" name="selectedDate" value="<%= selectedDate %>">
                        <%
                            List<Theater> theaters = TheaterDB.getAllTheaters();
                            for (Theater theater : theaters) {
                                out.println("<div class='theater'>");
                              if(theater.getId() == selectedTheater) {
                                out.println("<button type='button' class='btn btn-primary' style='background-color: #fca103;' onclick=\"chooseTheater('" + theater.getId() + "')\">" + theater.getName() + "</button>");
                            }else {
                                out.println("<button type='button' class='btn btn-primary' onclick=\"chooseTheater('" + theater.getId() + "')\">" + theater.getName() + "</button>");
                            }
                                out.println("</div>");
                            }
                        %>
                    </form>
                </div>
                <div class="header-1">
                    <p>CHOOSE DATE</p>
                </div>
                <div class="day">
                    <%
                        List<Date> dates = ShowtimeDB.getShowDates();
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date parseDate = dateFormat.parse(selectedDate);
                        for (int i = 0; i < dates.size(); i++) {
                            String formattedDate = sdf.format(dates.get(i));
                            String idAttribute = "";
                            if (i == 0) {
                                idAttribute = " id='mark-day-1'";
                            } else if (i == dates.size() - 1) {
                                idAttribute = " id='mark-day-2'";
                            }
                            if(parseDate.equals(dates.get(i))) {
                                out.println("<button type='button'  style='background-color: #e0a800;color: #3498db;font-size: 1.2em;'" + idAttribute + " onclick=\"chooseDay('" + dates.get(i) + "')\">" + formattedDate + "</button>");
                        } else {
out.println("<button type='button'" + idAttribute + " onclick=\"chooseDay('" + dates.get(i) + "')\">" + formattedDate + "</button>");
                            }
                        }
                    %>
                </div>

                <%
                   List<Movie> allMovies = MovieDB.getAllMovies();
                   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                   for (Movie o : allMovies) {
                       List<Showtime> showtimes = ShowtimeDB.getShowtimesByMovieAndDay(o.getId(), selectedDate, selectedTheater);
                       if (!showtimes.isEmpty()) {
                           out.write("<div class='timeline-chosen'>");
                           out.write("<div class='film-img'>");
                           out.write("<img src='"+o.getImageUrl()+"'>");
                           out.write("</div>");
                           out.write("<div class='name-timeline'>");
                           out.write("<p class='title-film'>" + o.getTitle() + "</p><br>");
                           out.write("<p class='film-duration'>Duration: " + o.getDurationMinutes() + " minutes </p>");
                           out.write("<div class='form-time'>");
                           
                           for (Showtime s : showtimes) {
                               out.write("<form action='selectionServlet' method='get'>");
                               LocalDateTime start = s.getStartTime();
                               out.write("<input type='hidden' name='selectedShowtime' value='" + s.getId() + "'>");
                               out.write("<input type='hidden' name='selectedMovie' value='" + o.getId() + "'>");
                               out.write("<input type='hidden' name='selectedTheater' value='" + selectedTheater + "'>");
                               out.write("<input type='hidden' name='selectedDate' value='" + selectedDate + "'>");
                               out.write("<button type='submit' class='time'>" + start.format(formatter) + "</button>");
                                out.write("</form>"); 
                           }
                          
                           out.write("</div>");
                           out.write("</div>");
                           out.write("</div>");
                       }
                   }
                %>


            </div>

            <div class="third-body">
                <div class="advertise-img">
                    <img src="img/GTCN.PNG">
                </div>
            </div>
        </div>
        <div class="footer">
            <div class="footer__aboutus">
                <h2>ABOUT US</h2>
                <p>CÔNG TY TNHH Hehe <br> Đăng ký lần đầu : vào ngày đăng ký <br> Địa chỉ: Đố biết ở đâu <br> Copyright : @WriteKhoa</p>
            </div>
            <div class="footer__partner">
                <h2>PARTNER</h2>
                <img src="img/logomomo.png">
                <img src="img/logocine.png">
                <img src="img/logoshopee.jpg">               
            </div>
        </div>
        <script src='https://cdn.jsdelivr.net/npm/sweetalert2@11'></script>
        <script src="javascp/script2.js"></script>
        <script>
                                function chooseTheater(theater) {
                                    document.getElementById('selectedTheater').value = theater;
                                    document.getElementById('selectionForm').submit();
                                }

                                function chooseDay(date) {
                                    document.getElementById('selectedDate').value = date;
                                    document.getElementById('selectionForm').submit();
                                }

                                function chooseShowtime(showtime_id)
                                {
                                    document.getElementById('selectedShowtime').value = showtime_id;
                                    document.getElementById('form').submit();
                                }
        </script>
        <script>
            setTimeout(function () {
                sessionStorage.removeItem('welcome_shown_' + username);
            }, 300000);
            // Lấy tên người dùng từ attribute được đặt trong Servlet
            var username = '<%= username %>';
            // Hiển thị thông báo SweetAlert
            if (!sessionStorage.getItem('welcome_shown_' + username))
            {
                Swal.fire({
                    icon: 'success',
                    title: 'Welcome, ' + username + '!',
                    text: 'You have successfully logged in.',
                    showConfirmButton: true,
                    timer: 5000
                });
            }
            sessionStorage.setItem('welcome_shown_' + username, 'true');



            function showNavbarContent() {
                var navbar = document.getElementById('navbar');
                navbar.style.display = "block";
                navbar.classList.add('navbar-visible');
            }

            // Sự kiện click vào window để ẩn navbar
            window.onclick = function (event) {
                var navbar = document.getElementById('navbar');
                var buttonavatar = document.getElementById('buttonavatar');
                if (navbar.style.display === "block") {
                    if (!navbar.contains(event.target) && !buttonavatar.contains(event.target)) {
                        navbar.style.display = "none";
                    }
                }
            };
            function logout() {
                document.getElementById('formA2').submit();
            }
        </script>        
    </body>
</html>