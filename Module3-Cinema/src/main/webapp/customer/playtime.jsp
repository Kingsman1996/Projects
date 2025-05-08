<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Suất Chiếu Phim</title>
    <style>
        body {
            font-family: "Segoe UI", sans-serif;
            margin: 20px;
            background-color: #eef2f3;
        }

        h2 {
            color: #2c3e50;
            text-align: center;
        }

        table {
            width: 90%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: #fff;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 14px 16px;
            text-align: center;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #3498db;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        .no-data {
            text-align: center;
            color: #999;
            font-style: italic;
            padding: 20px;
        }

        .btn-link {
            background-color: #2ecc71;
            color: white;
            text-decoration: none;
            padding: 8px 14px;
            border-radius: 5px;
            font-weight: bold;
            transition: background-color 0.3s ease;
            display: inline-block;
        }

        .btn-link:hover {
            background-color: #27ae60;
        }

        .expired {
            color: #e74c3c;
            font-weight: bold;
        }
    </style>

</head>
<body>
<h2>Lịch chiếu ${movieName}</h2>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Phòng Chiếu</th>
        <th>Ngày Chiếu</th>
        <th>Giờ Chiếu</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:choose>
        <c:when test="${not empty playtimeList}">
            <c:forEach var="playtime" items="${playtimeList}">
                <tr>
                    <td>${playtime.id}</td>
                    <td>${playtime.room.name}</td>
                    <td>${playtime.day}</td>
                    <td>${playtime.hour}</td>
                    <td>
                        <c:set var="playtimeDateTime" value="${playtime.day} - ${playtime.hour}" />
                        <c:choose>
                            <c:when test="${playtimeDateTime > now}">
                                <a href="customer?action=booking&playtimeId=${playtime.id}" class="btn-link">Đặt vé ngay</a>
                            </c:when>
                            <c:otherwise>
                                <span class="expired">Đã hết hạn</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <tr>
                <td colspan="5" class="no-data">Không có suất chiếu nào cho phim này.</td>
            </tr>
        </c:otherwise>
    </c:choose>
    </tbody>
</table>
</body>
</html>