<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Đăng nhập</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(to right, #0f2027, #203a43, #2c5364);
        }

        .card {
            background: rgba(255, 255, 255, 0.1);
            backdrop-filter: blur(15px);
            border: none;
            border-radius: 1.5rem;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.3);
            max-width: 400px;
            width: 100%;
        }

        .form-control {
            background-color: rgba(255, 255, 255, 0.2);
            border: none;
            color: #fff;
        }

        .form-control::placeholder {
            color: #ccc;
        }
    </style>
</head>
<body class="d-flex justify-content-center align-items-center vh-100">
<div class="card p-4">
    <c:if test="${not empty error}">
        <div class="alert alert-danger text-center mb-3">${error}</div>
    </c:if>
    <p class="text-center text-white-50 mb-4">Đăng nhập để truy cập vào CinemaKing</p>
    <form action="login" method="POST">
        <div class="mb-3">
            <label for="username" class="form-label text-white fw-medium">Tên đăng nhập</label>
            <input type="text" id="username" name="inputUsername" class="form-control form-control-lg rounded-pill" required />
        </div>
        <div class="mb-3">
            <label for="password" class="form-label text-white fw-medium">Mật khẩu</label>
            <input type="password" id="password" name="inputPassword" class="form-control form-control-lg rounded-pill" required />
        </div>
        <button type="submit" class="btn btn-primary btn-lg w-100 rounded-pill mb-3">Đăng nhập</button>
        <p class="text-center text-white-50 mb-3">Chưa có tài khoản?</p>
        <a href="register" class="btn btn-outline-danger btn-lg w-100 rounded-pill">Đăng ký ngay</a>
    </form>
</div>
</body>
</html>