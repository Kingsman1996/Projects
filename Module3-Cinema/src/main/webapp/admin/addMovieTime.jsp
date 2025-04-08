<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thêm Lịch Chiếu</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<c:if test="${not empty message}">
    <div class="alert alert-success">${message}</div>
    <c:remove var="message" scope="session"/>
</c:if>

<div class="container mt-4">
    <h2 class="mb-4">Thêm Lịch Chiếu</h2>

    <c:if test="${not empty message}">
        <div id="alert-box" class="alert alert-success">${message}</div>
        <script>
            setTimeout(() => document.getElementById("alert-box").style.display = 'none', 3000);
        </script>
    </c:if>

    <form method="POST">
        <div class="form-group">
            <label for="movieId">Chọn Phim:</label>
            <select id="movieId" name="movieId" class="form-control" required>
                <c:forEach var="movie" items="${movies}">
                    <option value="${movie.id}">${movie.name}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label for="showDate">Ngày Chiếu:</label>
            <input type="date" id="showDate" name="showDate" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="showTime">Giờ Chiếu:</label>
            <input type="time" id="showTime" name="showTime" class="form-control" required>
        </div>

        <button type="submit" class="btn btn-success">Thêm Lịch Chiếu</button>
        <a href="admin" class="btn btn-secondary">Hủy</a>
    </form>
</div>
</body>
</html>
