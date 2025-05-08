<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>KingCinema</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;600&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        html {
            scroll-behavior: smooth;
        }

        body {
            background-color: #121212;
            color: white;
            max-width: 90%;
            margin: auto;
            min-height: 100vh;
            display: flex;
            flex-direction: column;
            font-family: 'Poppins', sans-serif;
        }

        main {
            flex: 1;
            background: #181818;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.5);
        }

        .header {
            background: #1a1a1a;
            padding: 10px 0;
            border-bottom: 1px solid #333;
        }

        .logo img {
            height: 50px;
        }

        .username {
            font-size: 16px;
            font-weight: 500;
        }

        .logout-btn {
            padding: 8px 15px;
            font-size: 14px;
            font-weight: bold;
            transition: background-color 0.3s ease;
        }

        .logout-btn:hover {
            background-color: #c82333;
            text-decoration: none;
        }

        .nav-link.active {
            font-weight: bold;
            color: #3498db !important;
            border-bottom: 2px solid #3498db;
        }

        .navbar {
            border-radius: 0 0 10px 10px;
            margin-bottom: 10px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.2);
        }

        .nav-link {
            transition: all 0.3s;
        }

        .nav-link:hover {
            color: #3498db !important;
            text-shadow: 0 0 5px #3498db;
        }

        .card {
            background: #1f1f1f;
            border: none;
            border-radius: 12px;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }

        .card:hover {
            transform: translateY(-5px);
            box-shadow: 0 4px 20px rgba(255, 193, 7, 0.15);
        }

        .card-img {
            border-radius: 12px 12px 0 0;
            object-fit: cover;
            height: 500px;
        }

        .card-title {
            font-size: 20px;
            font-weight: 600;
        }

        .btn-warning {
            font-weight: 500;
            letter-spacing: 0.5px;
            transition: background-color 0.3s ease;
            background-color: #3498db;
        }

        .btn-warning:hover {
            background-color: #3498db;
            color: white;
        }

        footer {
            background: #101010;
            border-top: 2px solid #333;
            margin-top: 20px;
            padding: 20px 0;
        }

        #contact {
            background: #1b1b1b;
            padding: 40px 0;
            border-top: 1px solid #333;
        }

        #contact i {
            color: #3498db;
        }

        #contact a:hover {
            text-decoration: underline;
        }

        .bi {
            transition: transform 0.3s ease;
        }

        .bi:hover {
            transform: scale(1.2);
            color: #3498db !important;
        }
    </style>
</head>
<body>

<header class="header py-2">
    <div class="container d-flex align-items-center justify-content-between">
        <div class="logo">
            <a href="customer"><img src="${pageContext.request.contextPath}/images/cinema.jpg" alt="KingCinema"></a>
        </div>
        <div class="d-flex align-items-center gap-3">
            <span class="username">Xin chào, ${user.username}</span>
            <a href="logout" class="logout-btn btn btn-danger">Đăng xuất</a>
        </div>
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
            <c:forEach var="movie" items="${movieList}">
                <div class="col-lg-4 col-md-6 col-sm-12">
                    <div class="card bg-dark text-white mb-4">
                        <img src="${pageContext.request.contextPath}/images/${movie.imageUrl}" class="card-img"
                             alt="">
                        <div class="card-body text-center">
                            <h5 class="card-title">${movie.name}</h5>
                            <p class="card-text"><strong>Thể loại:</strong> ${movie.type}</p>
                            <p class="card-text"><strong>Thời lượng:</strong> ${movie.duration} phút</p>
                            <a href="customer?action=checkPlaytime&movieName=${movie.name}"
                               class="btn btn-warning mt-3">
                                Xem lịch chiếu</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</main>

<section id="contact" class="py-4 bg-dark">
    <div class="container text-white text-center">
        <h4>Thông tin liên hệ</h4>
        <p><i class="bi bi-geo-alt-fill"></i> Số 23, Lô TT01, KĐT Mon City, Mỹ Đình 2, Q. Nam Từ Liêm</p>
        <p>
            <i class="bi bi-envelope-fill"></i>
            <a href="mailto:support@kingcinema.vn" class="text-white text-decoration-none">Email:
                support@kingcinema.vn</a>
        </p>
        <p><i class="bi bi-telephone-fill"></i> Hotline: 0909 123 456</p>
        <div class="mt-3">
            <a href="https://www.facebook.com/kingcinema" target="_blank" class="text-white me-3 fs-4"><i
                    class="bi bi-facebook"></i></a>
            <a href="https://www.instagram.com/kingcinema" target="_blank" class="text-white me-3 fs-4"><i
                    class="bi bi-instagram"></i></a>
            <a href="https://www.twitter.com/kingcinema" target="_blank" class="text-white fs-4"><i
                    class="bi bi-twitter"></i></a>
        </div>
    </div>
</section>

<footer class="text-center py-3 bg-black">
    <p class="mb-0 text-white">© 2025 KingCinema. All rights reserved.</p>
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