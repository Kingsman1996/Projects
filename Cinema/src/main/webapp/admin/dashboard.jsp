<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Quản trị hệ thống rạp phim</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .sidebar {
            min-height: 100vh;
            background-color: #212529;
        }

        .sidebar .nav-link {
            color: #fff;
            font-size: 18px;
        }

        .sidebar .nav-link.text-danger {
            font-weight: bold;
            text-align: center;
            margin-top: 20px;
        }

        .sidebar .nav-link.active {
            background-color: #495057;
        }
    </style>
</head>
<body class="bg-dark text-white">
<c:if test="${not empty sessionScope.failMessage}">
    <div class="alert alert-danger text-center">${sessionScope.failMessage}</div>
    <c:remove var="failMessage" scope="session"/>
</c:if>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-3 sidebar p-4">
            <h3 class="text-center mb-4">Quản trị</h3>
            <div class="nav flex-column">
                <a href="?tab=movie" class="nav-link ${param.tab == 'movie' || param.tab == null ? 'active' : ''}">Quản
                    lý phim</a>
                <a href="?tab=playtime" class="nav-link ${param.tab == 'playtime' ? 'active' : ''}">Quản lý lịch
                    chiếu</a>
                <a href="?tab=ticket" class="nav-link ${param.tab == 'ticket' ? 'active' : ''}">Lịch sử đặt vé</a>
                <a href="logout" class="nav-link text-danger">Đăng xuất</a>
            </div>
        </div>

        <div class="col-md-9 p-4">
            <c:choose>
                <c:when test="${param.tab == 'movie' || param.tab == null}">
                    <h2 class="text-center mb-4">Danh sách phim</h2>
                    <table class="table table-dark table-bordered table-hover text-center align-middle">
                        <thead class="table-light text-dark">
                        <tr>
                            <th>ID</th>
                            <th>Tên phim</th>
                            <th>Thể loại</th>
                            <th>Thời lượng</th>
                            <th>Điều chỉnh</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="movie" items="${movieList}">
                            <tr>
                                <td>${movie.id}</td>
                                <td>${movie.name}</td>
                                <td>${movie.type}</td>
                                <td>${movie.duration} phút</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/admin?action=updateMovie&movieId=${movie.id}"
                                       class="btn btn-sm btn-warning">Sửa</a>
                                    <form action="${pageContext.request.contextPath}/admin?action=deleteMovie"
                                          method="post" class="d-inline">
                                        <input type="hidden" name="movieId" value="${movie.id}"/>
                                        <button class="btn btn-sm btn-danger"
                                                onclick="return confirm('Bạn có chắc muốn xóa?')">Xóa
                                        </button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="5" class="text-center">
                                <a href="${pageContext.request.contextPath}/admin?action=addMovie"
                                   class="btn btn-outline-light">Thêm phim mới</a>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </c:when>

                <c:when test="${param.tab == 'playtime'}">
                    <h2 class="text-center mb-4">Lịch chiếu</h2>
                    <table class="table table-dark table-bordered table-hover text-center align-middle">
                        <thead class="table-light text-dark">
                        <tr>
                            <th>ID</th>
                            <th>Tên phim</th>
                            <th>Phòng chiếu</th>
                            <th>Ngày chiếu</th>
                            <th>Giờ chiếu</th>
                            <th>Điều chỉnh</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="playtime" items="${playtimeList}">
                            <tr>
                                <td>${playtime.id}</td>
                                <td>${playtime.movie.name}</td>
                                <td>${playtime.room.name} </td>
                                <td>${playtime.day}</td>
                                <td>${playtime.hour}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/admin?action=updatePlaytime&playtimeId=${playtime.id}
                                    " class="btn btn-sm btn-warning">Sửa</a>
                                    <form action="${pageContext.request.contextPath}/admin?action=deletePlaytime"
                                          method="POST" class="d-inline">
                                        <input type="hidden" name="playtimeId" value="${playtime.id}"/>
                                        <button class="btn btn-sm btn-danger"
                                                onclick="return confirm('Xóa lịch chiếu này?')">Xóa
                                        </button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="5" class="text-center">
                                <a href="${pageContext.request.contextPath}/admin?action=addPlaytime"
                                   class="btn btn-outline-light">Thêm lịch chiếu mới</a>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </c:when>

                <c:when test="${param.tab == 'ticket'}">
                    <h2 class="text-center mb-4">Lịch sử đặt vé</h2>
                    <c:choose>
                        <c:when test="${not empty ticketList}">
                            <table class="table table-dark table-bordered table-hover text-center align-middle">
                                <thead class="table-light text-dark">
                                <tr>
                                    <th>ID Vé</th>
                                    <th>Người Dùng</th>
                                    <th>Phim</th>
                                    <th>Phòng</th>
                                    <th>Ngày Chiếu</th>
                                    <th>Giờ Chiếu</th>
                                    <th>Ghế</th>
                                    <th>Thời Gian Đặt</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="ticket" items="${ticketList}">
                                    <tr>
                                        <td>${ticket.id}</td>
                                        <td>${ticket.user.username}</td>
                                        <td>${ticket.playtime.movie.name}</td>
                                        <td>${ticket.playtime.room.name}</td>
                                        <td>${ticket.playtime.day}</td>
                                        <td>${ticket.playtime.hour}</td>
                                        <td>${ticket.seat.name}</td>
                                        <td>${ticket.bookingTime}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </c:when>
                        <c:otherwise>
                            <p class="text-center mt-5">Chưa có thông tin.</p>
                        </c:otherwise>
                    </c:choose>
                </c:when>
            </c:choose>
        </div>
    </div>
</div>
</body>
</html>
