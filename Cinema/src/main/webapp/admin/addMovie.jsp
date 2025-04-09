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
    <h1>Thêm Phim Mới</h1>
    <form action="${pageContext.request.contextPath}/admin?action=addMovie" method="post">
        <div class="mb-3">
            <label for="mvname" class="form-label">Tên phim</label>
            <input type="text" class="form-control" id="mvname" name="mvname" required>
        </div>
        <div class="mb-3">
            <label for="mvtype" class="form-label">Thể loại</label>
            <input type="text" class="form-control" id="mvtype" name="mvtype" required>
        </div>
        <div class="mb-3">
            <label for="duration" class="form-label">Thời lượng (phút)</label>
            <input type="number" class="form-control" id="duration" name="duration" required>
        </div>
        <button type="submit" class="btn btn-primary">Thêm phim</button>
    </form>

</div>
</body>
</html>
