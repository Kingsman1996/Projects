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
    <div id="alert-box" class="alert alert-success">${message}</div>
</c:if>
<c:if test="${not empty error}">
    <div id="alert-box" class="alert alert-danger">${error}</div>
</c:if>
<div class="container mt-4">
    <h2 class="mb-4">Thêm Lịch Chiếu</h2>
    <form method="POST">
        <div class="form-group">
            <label for="movieId">Chọn Phim:</label>
            <select id="movieId" name="movieId" class="form-control" required>
                <c:forEach var="movie" items="${movieList}">
                    <option value="${movie.id}">${movie.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="roomId">Chọn phòng chiếu:</label>
            <select id="roomId" name="roomId" class="form-control" required>
                <c:forEach var="room" items="${roomList}">
                    <option value="${room.id}">${room.name}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label for="day">Ngày Chiếu:</label>
            <input type="date" id="day" name="day" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="hour">Giờ Chiếu:</label>
            <input type="time" id="hour" name="hour" class="form-control" required>
        </div>

        <button type="submit" class="btn btn-success">Thêm Lịch Chiếu</button>
        <a href="admin" class="btn btn-secondary">Hủy</a>
    </form>
</div>
<script>
    setTimeout(() => document.getElementById("alert-box").style.display = 'none', 3000);
</script>
</body>
</html>
