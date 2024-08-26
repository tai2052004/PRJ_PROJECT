<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="model.Users, model.UserDB, java.util.ArrayList" %>

<%
    Users user = (Users) session.getAttribute("user");
    if (user == null || !"admin".equals(user.getUser_role())) {
        response.sendRedirect("login.jsp");
        return;
    }

    ArrayList<Users> userList = UserDB.listAllUsers();
%>

<!DOCTYPE html>
<html>
<head>
    <title>Manage Users</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .container {
            margin-top: 50px;
        }
        .form-group {
            margin-right: 20px;
        }
        .table th, .table td {
            vertical-align: middle;
        }
        .btn-danger {
            margin-left: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1 class="mb-4">Manage Users</h1>
        
        <h2 class="mb-3">Add New User</h2>
        <form action="AddAndDelete" method="post" class="form-inline mb-4">
            <div class="form-group">
                <label for="username" class="mr-2">Username:</label>
                <input type="text" id="username" name="username" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="password" class="mr-2">Password:</label>
                <input type="password" id="password" name="password" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="tel" class="mr-2">Tel:</label>
                <input type="text" id="tel" name="tel" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="role" class="mr-2">Role:</label>
                <select id="role" name="role" class="form-control" required>
                    <option value="user">User</option>
                    <option value="admin">Admin</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Add User</button>
            <input type="hidden" name="action" value="add">
        </form>
        
        <h2 class="mb-3">Existing Users</h2>
        <table class="table table-bordered table-striped table-hover">
            <thead>
                <tr>
                    <th>UserID</th>
                    <th>Username</th>
                    <th>Tel</th>
                    <th>Role</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (Users u : userList) {
                %>
                <tr>
                    <td><%= u.getUser_id() %></td>
                    <td><%= u.getUser_name() %></td>
                    <td><%= u.getUser_tel() %></td>
                    <td><%= u.getUser_role() %></td>
                    <td>
                        <form action="AddAndDelete" method="post" style="display:inline;">
                            <input type="hidden" name="userID" value="<%= u.getUser_id() %>">
                            <button type="submit" class="btn btn-danger">Delete</button>
                            <input type="hidden" name="action" value="delete">
                        </form>
                    </td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>