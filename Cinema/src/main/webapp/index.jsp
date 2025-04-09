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
            padding: 2rem;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.3);
            max-width: 400px;
            width: 100%;
        }

        .welcome-text {
            font-size: 1.8rem;
            font-weight: 700;
            text-align: center;
            color: #fff;
        }

        .form-label {
            font-weight: 500;
            color: #fff;
        }

        .form-control {
            background-color: rgba(255, 255, 255, 0.2);
            border: none;
            color: #fff;
        }

        .form-control::placeholder {
            color: #ccc;
        }

        .alert-box {
            margin-bottom: 15px;
        }

        .btn-primary {
            background-color: #007bff;
            border: none;
        }

        .btn-outline-danger {
            border-color: #ff4d4d;
            color: #ff4d4d;
        }

        .btn-outline-danger:hover {
            background-color: #ff4d4d;
            color: #fff;
        }
    </style>
</head>
<body class="d-flex justify-content-center align-items-center vh-100">

<div class="card">
    <c:if test="${not empty error}">
        <div class="alert alert-danger text-center alert-box">${error}</div>
    </c:if>
    <p class="text-center text-white-50 mb-4">Đăng nhập để bắt đầu trải nghiệm</p>

    <form action="login" method="post">
        <div class="mb-3">
            <label for="username" class="form-label">Tên đăng nhập</label>
            <input type="text" id="username" name="username" class="form-control form-control-lg rounded-pill" placeholder="Nhập tên đăng nhập" required />
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Mật khẩu</label>
            <input type="password" id="password" name="password" class="form-control form-control-lg rounded-pill" placeholder="Nhập mật khẩu" required />
        </div>
        <button type="submit" class="btn btn-primary btn-lg w-100 rounded-pill mb-3">Đăng nhập</button>
        <div class="text-center text-white-50">
            <p>Chưa có tài khoản?</p>
        </div>
        <a href="register" class="btn btn-outline-danger btn-lg w-100 rounded-pill">Đăng ký ngay</a>
    </form>
</div>

</body>
</html>
