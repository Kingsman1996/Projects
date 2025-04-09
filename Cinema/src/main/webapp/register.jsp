<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Đăng ký</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body class="bg-dark text-white d-flex justify-content-center align-items-center vh-100">
<div class="text-center position-absolute top-0 mt-4 w-100 px-3">
    <c:if test="${not empty error}">
        <div class="alert alert-danger mx-auto" style="max-width: 400px;">${error}</div>
    </c:if>
    <c:if test="${not empty success}">
        <div class="alert alert-success mx-auto" style="max-width: 400px;">${success}</div>
    </c:if>
</div>

<div class="card shadow-lg p-4 rounded-4"
     style="max-width: 400px; width: 100%; background: rgba(255, 255, 255, 0.1); backdrop-filter: blur(10px);">
    <h2 class="text-center mb-4">Đăng ký tài khoản</h2>
    <form action="register" method="post">
        <div class="mb-3">
            <input type="text" name="username" class="form-control form-control-lg rounded-pill"
                   placeholder="Tên đăng nhập" required/>
        </div>
        <div class="mb-3">
            <input type="password" name="password" class="form-control form-control-lg rounded-pill"
                   placeholder="Mật khẩu" required/>
        </div>
        <button type="submit" class="btn btn-primary btn-lg w-100 rounded-pill mb-3">Đăng ký</button>
        <div class="text-center">
            <p>Đã có tài khoản?</p>
            <a href="login" class="btn btn-outline-danger btn-lg w-100 rounded-pill">Đăng nhập</a>
        </div>
    </form>
</div>
</body>

</html>
