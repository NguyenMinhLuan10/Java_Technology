<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.model.Product" %>
<%
    HttpSession currentSession = request.getSession();
    String username = (String) currentSession.getAttribute("username");

    if (username == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    String error = (String) currentSession.getAttribute("errorMessage");
    String success = (String) currentSession.getAttribute("successMessage");

    currentSession.removeAttribute("errorMessage");
    currentSession.removeAttribute("successMessage");

    List<Product> productList = (List<Product>) request.getAttribute("productList");
    if(productList == null){
        System.out.println("lỗi");
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Danh sách sản phẩm</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Roboto', sans-serif;
        }
    </style>
</head>
<body style="background-color: #f8f8f8">

<div class="container-fluid p-5">
    <div class="row mb-5">
        <div class="col-md-6">
            <h3>Product Management</h3>
        </div>
        <div class="col-md-6 text-right">
            Xin chào <span class="text-danger"><%= username != null ? username : "Username" %></span> | <a href="logout">Logout</a>
        </div>
    </div>
    <div class="row rounded border p-3">
        <div class="col-md-4">
            <h4 class="text-success">Thêm sản phẩm mới</h4>
            <form class="mt-3" method="post" action="addProduct">
                <div class="form-group">
                    <label for="product-name">Tên sản phẩm</label>
                    <input class="form-control" type="text" placeholder="Nhập tên sản phẩm" id="product-name" name="name">
                </div>
                <div class="form-group">
                    <label for="price">Giá sản phẩm</label>
                    <input class="form-control" type="number" placeholder="Nhập giá bán" id="price" name="price">
                </div>
                <div class="form-group">
                    <button class="btn btn-success mr-2" type="submit">Thêm sản phẩm</button>
                </div>

                <% if (error != null) { %>
                <div class="alert alert-danger"><%= error %></div>
                <% } else if (success != null) { %>
                <div class="alert alert-success"><%= success %></div>
                <% } else { %>
                <div class="alert alert-info">Hãy nhập thông tin sản phẩm</div>
                <% } %>
            </form>
        </div>
        <div class="col-md-8">
            <h4 class="text-success">Danh sách sản phẩm</h4>
            <p>Chọn một sản phẩm cụ thể để xem chi tiết</p>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>STT</th>
                    <th>Tên sản phẩm</th>
                    <th>Giá</th>
                    <th>Thao tác</th>
                </tr>
                </thead>
                <tbody>
                    <% if (productList != null && !productList.isEmpty()) {
                        int stt = 1;
                        for (Product product : productList) { %>
                    <tr>
                        <td><%= stt++ %></td>
                        <td><a href="#"> <%= product.getName() %> </a></td>
                        <td><%= product.getPrice() %></td>
                        <td><a href="#">Chỉnh sửa</a> | <a href="delete?id=<%= product.getId() %>" onclick="return confirm('Bạn có chắc muốn xoá chứ?');">Xóa</a></td>
                    </tr>
                    <%      }
                    } else { %>
                    <tr>
                        <td colspan="4" class="text-center">Không có sản phẩm nào</td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script>
    // Add the following code if you want the name of the file appear on select
    $(".custom-file-input").on("change", function() {
        var fileName = $(this).val().split("\\").pop();
        $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
    });
</script>
</body>
</html>
