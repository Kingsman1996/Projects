<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .table-container {
            margin-bottom: 20px;
            max-width: 90%;
            margin-left: auto;
            margin-right: auto;
        }

        .action-btn {
            margin-top: 10px;
        }

        .fixed-alert {
            position: fixed;
            top: 20px;
            right: 20px;
            z-index: 1050;
            min-width: 250px;
            box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
        }

        ul {
            list-style-type: none;
        }

        nav ul {
            display: flex;
            justify-content: center;
            padding: 0;
        }

        nav ul li {
            margin-right: 10px;
        }

        nav ul li:last-child {
            margin-right: 0;
        }

        table {
            width: 100%;
            text-align: center;
        }

        th, td {
            padding: 10px;
        }
    </style>
</head>
<body>
<c:if test="${not empty sessionScope.message}">
    <div id="alert-box" class="alert alert-success alert-dismissible fade show fixed-alert" role="alert">
            ${sessionScope.message}
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
    <c:remove var="message" scope="session"/>
</c:if>
<div class="container mt-4">
    <ul>
        <li>
            <a href="#movie" class="btn btn-primary">Quản lý Phim</a>
        </li>
        <li>
            <a href="#movietime" class="btn btn-primary">Quản lý Lịch Chiếu</a>
        </li>
        <li>
            <a href="admin?action=history" class="btn btn-primary">Xem lịch sử đặt vé</a>
        </li>
    </ul>
    <div class="table-container">
        <h2 id="movie">Danh sách Phim</h2>
        <div class="table-responsive mb-4">
            <table class="table table-bordered table-hover">
                <thead class="thead-light">
                <tr>
                    <th>ID Phim</th>
                    <th>Tên Phim</th>
                    <th>Thể Loại</th>
                    <th>Thời Lượng (phút)</th>
                    <th>Điều chỉnh</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="movie" items="${movies}">
                    <tr>
                        <td>${movie.id}</td>
                        <td>${movie.name}</td>
                        <td>${movie.type}</td>
                        <td>${movie.duration}</td>
                        <td>
                            <a href="admin?action=updateMovie&id=${movie.id}"
                               class="btn btn-warning btn-sm">Sửa</a>
                            <form action="admin?action=deleteMovie" method="POST" style="display:inline;">
                                <input type="hidden" name="id" value="${movie.id}">
                                <button type="submit" class="btn btn-danger btn-sm"
                                        onclick="return confirm('Bạn có chắc chắn muốn xóa phim này?');">
                                    Xóa
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="action-btn">
            <a href="admin?action=addMovie" class="btn btn-success">Thêm Phim</a>
        </div>
    </div>
</div>


<div class="table-container">
    <h2 id="movietime">Danh sách Lịch Chiếu</h2>
    <div class="table-responsive mb-4">
        <table class="table table-bordered table-hover">
            <thead class="thead-light">
            <tr>
                <th>ID Lịch Chiếu</th>
                <th>Tên Phim</th>
                <th>Ngày Chiếu</th>
                <th>Giờ Chiếu</th>
                <th>Điều chỉnh</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="movieTime" items="${movieTimes}">
                <tr>
                    <td>${movieTime.id}</td>
                    <td>
                        <c:forEach var="movie" items="${movies}">
                            <c:if test="${movie.id == movieTime.movieId}">
                                ${movie.name}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>${movieTime.showDate}</td>
                    <td>${movieTime.showTime}</td>
                    <td>
                        <a href="admin?action=updateMovieTime&id=${movieTime.id}&movieId=${movieTime.movieId}"
                           class="btn btn-warning btn-sm">Sửa
                        </a>
                        <form action="admin?action=deleteMovieTime&id=${movieTime.id}" method="POST"
                              style="display:inline;">
                            <button type="submit" class="btn btn-danger btn-sm"
                                    onclick="return confirm('Bạn có chắc chắn muốn xóa phim này?');">
                                Xóa
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="action-btn">
        <a href="admin?action=addMovieTime" class="btn btn-success">Thêm Lịch Chiếu</a>
    </div>
</div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    setTimeout(() => {
        let alertBox = document.getElementById("alert-box");
        if (alertBox) {
            alertBox.style.transition = "opacity 0.5s ease-out";
            alertBox.style.opacity = "0";
            setTimeout(() => alertBox.remove(), 500);
        }
    }, 3000);
</script>
</body>
</html>
