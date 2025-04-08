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
<div class="container mt-4">
    <h1>Thêm Phim Mới</h1>
    <form action="admin?action=addMovie" method="post">
        <div class="form-group">
            <label for="mvname">Tên Phim:</label>
            <input type="text" class="form-control" id="mvname" name="mvname" required>
        </div>
        <div class="form-group">
            <label for="mvtype">Thể Loại:</label>
            <input type="text" class="form-control" id="mvtype" name="mvtype" required>
        </div>
        <div class="form-group">
            <label for="duration">Thời Lượng (phút):</label>
            <input type="number" class="form-control" id="duration" name="duration" required>
        </div>
        <button type="submit" class="btn btn-primary">Lưu Phim</button>
        <a href="/admin" class="btn btn-secondary">Hủy</a>
    </form>
    <c:if test="${param.message == 'success'}">
        <div class="alert alert-success">Thêm phim thành công!</div>
    </c:if>

</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
