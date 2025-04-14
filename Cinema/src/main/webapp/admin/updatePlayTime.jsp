<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Cập nhật Lịch Chiếu</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<c:if test="${not empty message}">
    <div class="alert alert-success">
            ${message}
    </div>
</c:if>
<c:if test="${not empty error}">
    <div class="alert alert-danger">
            ${error}
    </div>
</c:if>
<body class="bg-light">

<div class="container mt-5">
    <div class="card shadow-lg">
        <div class="card-header bg-primary text-white text-center">
            <h2 class="mb-0">Cập nhật Lịch Chiếu Phim</h2>
        </div>
        <div class="card-body">
            <form method="POST">
                <input type="hidden" name="id" value="${playtime.id}">
                <div class="mb-3">
                    <label for="movieId" class="form-label">Phim Mới:</label>
                    <select id="movieId" name="movieId" class="form-select" required>
                        <option value="" disabled selected>-- Chọn phim --</option>
                        <c:forEach var="movie" items="${movieList}">
                            <option value="${movie.id}" ${movie.id == playtime.movie.id ? 'selected' : ''}>
                                    ${movie.name}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="roomId" class="form-label">Phim Mới:</label>
                    <select id="roomId" name="roomId" class="form-select" required>
                        <option value="" disabled selected>-- Chọn phòng chiếu --</option>
                        <c:forEach var="room" items="${roomList}">
                            <option value="${room.id}" ${room.id == playtime.room.id ? 'selected' : ''}>
                                    ${room.name}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="day" class="form-label">Ngày Chiếu:</label>
                    <input type="date" id="day" name="day" class="form-control" required>
                </div>

                <div class="mb-3">
                    <label for="hour" class="form-label">Giờ Chiếu:</label>
                    <input type="time" id="hour" name="hour" class="form-control" required>
                </div>

                <div class="d-flex justify-content-between">
                    <button type="submit" class="btn btn-success px-4">Cập nhật</button>
                    <a href="admin" class="btn btn-secondary px-4">Hủy</a>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>
