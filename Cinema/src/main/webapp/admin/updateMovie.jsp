<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cập nhật Phim</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">
    <h1 class="mb-4">Cập nhật Thông tin Phim</h1>

    <c:if test="${not empty message}">
        <div class="alert alert-success">
                ${message}
        </div>
    </c:if>

    <form action="admin?action=updateMovie" method="POST">
        <input type="hidden" name="movieId" value="${movie.id}">
        <div class="form-group">
            <label>Tên Phim Cũ:</label>
            <input type="text" class="form-control" value="${movie.name}" disabled>
        </div>
        <div class="form-group">
            <label>Tên Phim Mới:</label>
            <input type="text" class="form-control" name="name" required>
        </div>
        <div class="form-group">
            <label>Thể Loại Cũ:</label>
            <input type="text" class="form-control" value="${movie.type}" disabled>
        </div>
        <div class="form-group">
            <label>Thể Loại Mới:</label>
            <input type="text" class="form-control" name="type" required>
        </div>
        <div class="form-group">
            <label>Thời Lượng Cũ (phút):</label>
            <input type="text" class="form-control" value="${movie.duration}" disabled>
        </div>
        <div class="form-group">
            <label>Thời Lượng Mới (phút):</label>
            <input type="number" class="form-control" name="duration" required>
        </div>
        <button type="submit" class="btn btn-primary">Cập nhật</button>
        <a href="admin" class="btn btn-secondary">Hủy</a>
    </form>
</div>
</body>
</html>
