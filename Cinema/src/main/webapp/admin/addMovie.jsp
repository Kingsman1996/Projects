<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thêm Phim</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<c:if test="${not empty message}">
    <div class="alert alert-success">${message}</div>
</c:if>

<div class="container mt-4">
    <h1 class="text-center mb-4">Thêm Phim Mới</h1>
    <div class="card p-4 shadow">
        <form action="${pageContext.request.contextPath}/admin?action=addMovie" method="POST">
            <div class="mb-3">
                <label for="movieName" class="form-label">Tên phim</label>
                <input type="text" class="form-control" id="movieName" name="movieName" required>
            </div>
            <div class="mb-3">
                <label for="movieType" class="form-label">Thể loại</label>
                <input type="text" class="form-control" id="movieType" name="movieType" required>
            </div>
            <div class="mb-3">
                <label for="movieDuration" class="form-label">Thời lượng (phút)</label>
                <input type="number" class="form-control" id="movieDuration" name="movieDuration" required>
            </div>
            <div class="d-flex justify-content-between">
                <a href="${pageContext.request.contextPath}/admin" class="btn btn-secondary">Quay lại</a>
                <button type="submit" class="btn btn-primary">Thêm phim</button>
            </div>
        </form>
    </div>
</div>

</body>
</html>
