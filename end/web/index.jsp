<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/stylehome6.css"/>
        <link rel="stylesheet" href="boostrap/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

    </head>

    <body>

//abvdc
        <div class="header">
            <div class=" header__logo">
                <p>CINEMA</p>
            </div>
            <div class="header__button" >
                <form class="header__button__Home" action="index.jsp">
                    <button type="submit" >HOME</button>
                </form>
                <div class="header__button__Login"  >
                    <button type="submit" class="button__login__register" onclick="window.location.href = 'login.jsp'" id="id03" >LOGIN | REGISTER</button>
                </div>

                <div class="header__datve">
                    <button class="header__datve__button" type="submit" onclick="showLoginForm()" id="button__drop" >Đặt vé xem phim ngay
                        <img src="img/down-arrow.png" alt="alt"/></button>
                    <div class="dropdown-content" id="content" >
                        <a href="newjsp.jsp">Link 1</a>
                        <a href="newjsp.jsp">Link 2</a>
                        <a href="newjsp.jsp">Link 3</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="banner">
            <div class="mySlides fade">
                <div class="numbertext">1 / 3</div>
                <img src="img/film1.jpg" class="leftSlide">
                <img src="img/film2.jpg" class="rightSlide">
            </div>

            <div class="mySlides fade">
                <div class="numbertext">2 / 3</div>
                <img src="img/film3.jpg" class="leftSlide">
                <img src="img/film4.jpg" class="rightSlide">
            </div>

            <div class="mySlides fade">
                <div class="numbertext">3 / 3</div>
                <img src="img/film5.jpg" class="leftSlide">
                <img src="img/film6.jpg" class="rightSlide">
            </div>
        </div>


        <div class="banner_dot" style="text-align:center">
            <span class="dot"></span> 
            <span class="dot"></span> 
            <span class="dot"></span> 
        </div>
        <div class="slogan">
            <p>LET’s GET THE FILM YOU LIKE</p>
        </div>


        <div class="listfilm">
            <div class="listfilm__header">
                <div class="listfilm__tab"> 
                    <p>TOP PICK</p>
                </div>
            </div>
            <div class="listfilm__icontop">
                <img src="img/top2.png" alt="alt"/>
                <img src="img/top1.png" alt="alt"/>
                <img src="img/top3.png" alt="alt"/>
            </div>

            <div class="listfilm__slide">


                <div class="imgContainer">

                    <div class="listfilm__slide__item" >
                        <img src="img/film1.jpg" alt="alt" width="220px" height="340px" id="listfilm__slide__item1"/>
                        <p>TÊN PHIM Ở ĐÂY <br> timeline | mã phim</p>
                    </div>



                    <div class="listfilm__slide__item" >
                        <img src="img/film2.jpg" alt="alt" width="220px" height="340px" id="listfilm__slide__item2"/>
                        <p>TÊN PHIM Ở ĐÂY <br> timeline | mã phim</p>
                    </div>



                    <div class="listfilm__slide__item" >
                        <img src="img/film3.jpg" alt="alt" width="220px" height="340px" id="listfilm__slide__item3"/>
                        <p>TÊN PHIM Ở ĐÂY <br> timeline | mã phim</p>
                    </div>



                </div>

            </div>

        </div>
        <div class="event">
            <h2>KHUYẾN MÃI & ƯU ĐÃI</h2>
            <div class="event__div">
                <div class="event__firstline">
                    <div class="event__firstline__title" >
                        <span>KHUYẾN MÃI HẤP DẪN</span>
                        <p id="contentevent">
                            Khám phá ngay hàng trăm lợi ích dành cho bạn trong chuyên mục Khuyến mãi & Ưu đãi hấp dẫn của Metiz Cinema.</p></div>
                </div>
                <div class="event__secondline">
                    <div class="event__1 listevent">
                        <img src="img/event1.jpg" alt="alt" width="260px" height="260px"/>

                        <div class="even1__overlay overlay"></div>
                        <div class="eventtext">
                            <p>MORE</p>
                        </div>
                        <a href="#" onclick="view(1)"></a>

                    </div>

                    <div class="event__2 listevent">
                        <img src="img/event2.png" alt="alt" width="260px" height="260px"/>

                        <div class="even2__overlay overlay"></div>
                        <div class="eventtext">
                            <p>MORE</p>
                        </div>
                        <a href="#" onclick="view(2)"></a>

                    </div>
                    <div class="event__3 listevent">
                        <img src="img/event3.jpg" alt="alt" width="260px" height="260px"/>

                        <div class="even3__overlay overlay"></div>
                        <div class="eventtext">
                            <p>MORE</p>
                        </div>
                        <a href="#" onclick="view(3)"></a>
                    </div>
                    <div class="event__4 listevent">
                        <img src="img/event4.jpg" alt="alt" width="260px" height="260px"/>

                        <div class="even4__overlay overlay"></div>
                        <div class="eventtext">
                            <p>MORE</p>
                        </div>
                        <a href="#" onclick="view(4)"></a>
                    </div>
                    <div class="event__5 listevent">
                        <img src="img/event5.jpg" alt="alt" width="260px" height="260px"/>

                        <div class="even5__overlay overlay"></div>
                        <div class="eventtext">
                            <p>MORE</p>
                        </div>
                        <a href="#" onclick="view(5)"></a>
                    </div>         
                </div>
            </div>

        </div>
        <div class="footer">
            <div class="footer__aboutus">
                <h2>ABOUT US</h2>
                <p>CÔNG TY TNHH Hehe  <br> Đăng ký lần đầu : vào ngày đăng ký  <br> Địa chỉ: Đố biết ở đâu <br>
                    Copyright : @WriteKhoa</p>
            </div>
            <div class="footer__partner">
                <h2>PARTNER</h2>
                <img src="img/logomomo.png">
                <img src="img/logocine.png">
                <img src="img/logoshopee.jpg">               
            </div>

        </div>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="javascp/script1.js"></script>

    </body>
</html>
