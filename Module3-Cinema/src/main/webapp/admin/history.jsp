<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Lịch sử đặt vé</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-dark text-white">

<div class="container mt-5">
    <h2 class="text-center mb-4">Lịch sử đặt vé</h2>
    <c:choose>
        <c:when test="${not empty ticketDetails}">
            <table class="table table-dark table-bordered table-hover text-center align-middle">
                <thead class="table-light text-dark">
                <tr>
                    <th>ID vé</th>
                    <th>Khách hàng</th>
                    <th>Tên phim</th>
                    <th>Mã ghế</th>
                    <th>Ngày chiếu</th>
                    <th>Giờ chiếu</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="ticket" items="${ticketDetails}">
                    <tr>
                        <td>${ticket.ticketId}</td>
                        <td>${ticket.username}</td>
                        <td>${ticket.movieName}</td>
                        <td>${ticket.seatCode}</td>
                        <td>${ticket.showDate}</td>
                        <td>${ticket.showTime}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <p class="text-center mt-5">Chưa có thông tin.</p>
        </c:otherwise>
    </c:choose>

    <div class="text-center mt-4">
        <a href="admin" class="btn btn-outline-light">Quay lại</a>
    </div>
</div>

</body>
</html>
