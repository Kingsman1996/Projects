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
<body class="bg-light">

<div class="container mt-5">
    <div class="card shadow-lg">
        <div class="card-header bg-primary text-white text-center">
            <h2 class="mb-0">Cập nhật Lịch Chiếu Phim</h2>
        </div>
        <div class="card-body">
            <form method="POST">
                <input type="hidden" name="playTimeId" value="${playTime.playTimeId}">
                <div class="mb-3">
                    <label for="newMovieId" class="form-label">Phim Mới:</label>
                    <select id="newMovieId" name="newMovieId" class="form-select" required>
                        <option value="" disabled selected>-- Chọn phim --</option>
                        <c:forEach var="movie" items="${movies}">
                            <option value="${movie.movieId}">${movie.movieName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="newShowDate" class="form-label">Ngày Chiếu:</label>
                    <input type="date" id="newShowDate" name="newShowDate" class="form-control" required>
                </div>

                <div class="mb-3">
                    <label for="newShowTime" class="form-label">Giờ Chiếu:</label>
                    <input type="time" id="newShowTime" name="newShowTime" class="form-control" required>
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
