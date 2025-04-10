<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>KingCinema</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        html {
            scroll-behavior: smooth;
        }

        body {
            height: 100vh;
            background-color: #121212;
            display: flex;
            flex-direction: column;
            color: white;
            max-width: 90%;
            margin: auto;
        }

        main {
            flex: 1;
            background: #181818;
            padding: 20px;
            border-radius: 8px;
        }

        .header {
            background: #1a1a1a;
        }

        .header {
            padding: 10px 20px;
            display: flex;
            align-items: center;
            justify-content: space-between;
        }

        .header .logo img {
            height: 50px;
        }

        .navbar {
            background: #222;
        }

        .nav-link.active {
            font-weight: bold;
            color: #ffc107 !important;
            border-bottom: 2px solid #ffc107;
        }

        .card {
            background: #242424;
            height: 100%;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
        }

        .card-title {
            font-size: 18px;
            font-weight: bold;
        }

        .card-text {
            font-size: 14px;
            margin-bottom: 5px;
        }

        .list-group-item {
            font-size: 14px;
            padding: 6px;
        }

        .card-img {
            object-fit: fill;
            width: 100%;
            height: 600px;
        }

        .card-body {
            flex-grow: 1;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            text-align: center;
            padding: 15px;
        }

        .list-group {
            margin-top: 10px;
        }

        footer {
            text-align: center;
            padding: 10px 0;
            border-top: 2px solid #333;
        }
    </style>
</head>
<body>

<header class="header d-flex align-items-center">
    <div class="logo">
        <a href="customer"><img src="${pageContext.request.contextPath}/images/cinema.jpg" alt="KingCinema"></a>
    </div>
</header>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav mx-auto">
                <li class="nav-item">
                    <a class="nav-link" href="customer">Phim đang chiếu</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="customer?action=incoming">Phim sắp chiếu</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="customer?action=history">Lịch sử đặt vé</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#contact">Liên hệ</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<main class="main">
    <div class="container mt-4">
        <div class="row">
            <c:forEach var="movie" items="${movies}">
                <div class="col-lg-4 col-md-6 col-sm-12">
                    <div class="card bg-dark text-white mb-4">
                        <img src="${pageContext.request.contextPath}/images/${movie.imageUrl}" class="card-img"
                             alt="${movie.movieName}">
                        <div class="card-body">
                            <h5 class="card-title">${movie.movieName}</h5>
                            <p class="card-text"><strong>Thể loại:</strong> ${movie.movieType}</p>
                            <p class="card-text"><strong>Thời lượng:</strong> ${movie.movieDuration} phút</p>
                            <h6 class="mt-3">Lịch chiếu:</h6>
                            <ul class="list-group list-group-flush">
                                <c:forEach var="playTime" items="${movie.playTimes}">
                                    <c:if test="${playTime.movieId == movie.movieId}">
                                        <li class="list-group-item bg-dark text-white">
                                            Ngày: ${playTime.playDay} - Giờ: ${playTime.hour}
                                        </li>
                                    </c:if>
                                </c:forEach>

                            </ul>
                            <a href="customer?action=booking&movieId=${movie.movieId}" class="btn btn-warning mt-3">Đặt
                                vé</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</main>
<section id="contact" class="py-4" style="background-color: #1e1e1e;">
    <div class="container text-white text-center">
        <h4>Thông tin liên hệ</h4>
        <p><i class="bi bi-geo-alt-fill"></i> Số 23, Lô TT01, KĐT Mon City, Mỹ Đình 2, Q. Nam Từ Liêm</p>
        <p>
            <i class="bi bi-envelope-fill"></i>
            <a href="mailto:support@kingcinema.vn" style="color: white; text-decoration: none;">
                Email: support@kingcinema.vn
            </a>
        </p>

        <p><i class="bi bi-telephone-fill"></i>Hotline: 0909 123 456</p>
        <div class="mt-3">
            <a href="https://www.facebook.com/kingcinema" target="_blank" class="text-white me-3 fs-4">
                <i class="bi bi-facebook"></i>
            </a>
            <a href="https://www.instagram.com/kingcinema" target="_blank" class="text-white me-3 fs-4">
                <i class="bi bi-instagram"></i>
            </a>
            <a href="https://www.twitter.com/kingcinema" target="_blank" class="text-white fs-4">
                <i class="bi bi-twitter"></i>
            </a>
        </div>
    </div>
</section>

<footer class="text-center py-3" style="background-color: #111; border-top: 2px solid #333;">
    <p class="mb-0 text-white">&copy; 2025 KingCinema. All rights reserved.</p>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        const currentUrl = new URL(window.location.href);
        const currentPath = currentUrl.pathname + currentUrl.search;

        let matchedLink = null;
        let maxMatchLength = 0;

        document.querySelectorAll('.nav-link').forEach(link => {
            const linkUrl = new URL(link.href, window.location.origin);
            const linkPath = linkUrl.pathname + linkUrl.search;
            if (currentPath.startsWith(linkPath) && linkPath.length > maxMatchLength) {
                matchedLink = link;
                maxMatchLength = linkPath.length;
            }
        });
        if (matchedLink) {
            matchedLink.classList.add("active");
        }
    });
</script>

</body>
</html>