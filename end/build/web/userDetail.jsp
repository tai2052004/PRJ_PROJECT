<%-- 
    Document   : userDetail
    Created on : Jul 13, 2024, 10:05:11 AM
    Author     : ngoct
--%>
<%@page import="model.Users ,model.UserDetail , dao.UserDetailDB" %> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Users user = (Users) session.getAttribute("user");
    int id = user.getUser_id();
    String username = user.getUser_name();
    String password = user.getUser_password();
    String tel = user.getUser_tel();
    if (tel != null && !tel.startsWith("0")) {
        tel = "0" + tel;
    }
    UserDetail usd = UserDetailDB.getUserDetailById(id);
    String fulln = usd.getFullname();
    String dayofbirth =usd.getDOB();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thông tin các nhân </title>
        <link rel="stylesheet" href="css/userDetail1.css?v=1.0">
        <script>
            function enableEditing() {
                document.getElementById("fullname").disabled = false;
                document.getElementById("dob").disabled = false;
                document.getElementById("editButton").style.display = "none";
                document.getElementById("saveButton").style.display = "block";
            }

        </script>
    </head>
    
    <body>
        
        <div class="left-backhome">
                    <button type="button" class="back-home" id="back-home-button" onclick="window.location.href = 'newjsp.jsp';">
                    <span class="circle" aria-hidden="true">
                        <span class="icon arrow"></span>
                    </span>
                    <span class="button-text">Back Now</span>
                    </button>
        </div>
        <div class="body">
        <div class="video-background">
                <video autoplay muted loop id="bgVideo">
                    <source src="img/bg_UserDetail.mp4" type="video/mp4">
                </video>
            </div>
        </div>   
        <div class="container">
            <h2>Thông Tin Cá Nhân</h2>
            <label for="fullname">Username</label>
            <input type="text" value=" <%= username %>" disabled>
            <label for="fullname">Password</label>
            <input type="password" value=" <%= password %>" disabled>
            <label for="fullname">Tel Number</label>
            <input type="text" value=" <%= tel %>" disabled >
            <form action="UserDetailServlet" method="post">
                <label for="fullname">Full Name:</label>
                <input type="text" id="fullname" name="fullname" value="<%= fulln %> " disabled>

                <label for="dob">Date of Birth:</label>
                <input type="date" id="dob" name="dob" value="<%= dayofbirth %>" disabled>
                <input type="hidden" name="id" value="<%= id %>">
                <input type="hidden" name="username" value="<%= request.getParameter("username") %>">
                <input type="hidden" name="password" value="<%= request.getParameter("password") %>">
                <input type="hidden" name="tel" value="<%= request.getParameter("tel") %>">

                <input type="button" id="editButton" value="Chỉnh sửa thông tin" onclick="enableEditing()">
                <input type="submit" id="saveButton" value="Lưu" style="display:none; ">
            </form>
        </div>
        
    </body>
</html>
