<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Đăng nhập</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        .welcome-text {
            font-size: 2rem;
            font-weight: bold;
            color: white;
            text-align: center;
        }
        .form-label {
            font-weight: bold;
            font-size: 1.1rem;
        }
        .card {
            width: fit-content;
            position: relative;
        }
        .error-message {
            position: relative;
            margin-bottom: 10px;
            text-align: center;
        }
    </style>
</head>
<body class="bg-gradient bg-dark text-white d-flex justify-content-center align-items-center vh-100">

<div class="card shadow-lg p-4 rounded-4"
     style="background: rgba(255, 255, 255, 0.1); backdrop-filter: blur(10px);">
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger error-message">
                ${errorMessage}
        </div>
    </c:if>
    <h3 class="welcome-text mb-4">Chào mừng bạn đến với CineKing!</h3>
    <p class="text-center">Đăng nhập hoặc đăng ký để sử dụng dịch vụ</p>
    <form action="login" method="post">
        <div class="mb-3">
            <label for="username" class="form-label">Tên đăng nhập</label>
            <input type="text" id="username" name="username" class="form-control form-control-lg rounded-pill" required />
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Mật khẩu</label>
            <input type="password" id="password" name="password" class="form-control form-control-lg rounded-pill" required />
        </div>
        <button type="submit" class="btn btn-primary btn-lg w-100 rounded-pill mb-3">Đăng nhập</button>
        <p class="text-center">Chưa có tài khoản?</p>
        <a href="register" class="btn btn-outline-danger btn-lg w-100 rounded-pill">Đăng ký ngay</a>
    </form>
</div>

</body>
</html>
