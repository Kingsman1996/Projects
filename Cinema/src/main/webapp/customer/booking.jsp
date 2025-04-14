<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
    <title>Chọn Ghế</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #1e1e2f;
            color: #f0f0f0;
            font-family: "Segoe UI", sans-serif;
        }

        h1 {
            color: #ffffff;
        }

        .seat-map {
            display: grid;
            grid-template-columns: repeat(8, 50px);
            gap: 10px;
            justify-content: center;
            margin-bottom: 20px;
        }

        .seat-btn {
            width: 50px;
            height: 50px;
            font-weight: bold;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(255, 255, 255, 0.08);
            transition: background-color 0.3s ease;
            color: white;
        }

        .btn-success {
            background-color: #4CAF50 !important;
            border-color: #4CAF50 !important;
            color: white;
        }

        .btn-warning {
            background-color: #f1c40f !important;
            border-color: #f1c40f !important;
            color: black;
        }

        .btn-secondary {
            background-color: #7f8c8d !important;
            border-color: #7f8c8d !important;
            color: white;
        }

        .screen {
            width: 100%;
            max-width: 500px;
            margin: 20px auto;
            background-color: #2c3e50;
            height: 25px;
            border-radius: 5px;
            position: relative;
            font-weight: bold;
            color: #ecf0f1;
            display: flex;
            align-items: center;
            justify-content: center;
            letter-spacing: 1px;
        }

        .legend {
            margin-top: 20px;
        }

        .legend .btn {
            pointer-events: none;
            width: 30px;
            height: 30px;
            border-radius: 6px;
        }

        .legend span {
            margin-left: 6px;
            margin-right: 20px;
            font-size: 14px;
            color: #f0f0f0;
        }

        .btn-outline-primary {
            border-color: #3498db;
            color: #3498db;
        }

        .btn-outline-primary:hover {
            background-color: #3498db;
            color: white;
        }
    </style>


</head>
<body class="container text-center mt-4">

<h1 class="mb-4">Sơ đồ ghế</h1>

<div class="screen">Màn hình</div>

<div class="seat-map mt-4">
    <c:set var="seatIndex" value="0"/>
    <c:forEach var="seat" items="${seatList}">
        <c:set var="isBooked" value="false"/>
        <c:forEach var="bSeat" items="${bookedSeatList}">
            <c:if test="${bSeat.id == seat.id}">
                <c:set var="isBooked" value="true"/>
            </c:if>
        </c:forEach>

        <c:choose>
            <c:when test="${isBooked}">
                <button class="btn btn-secondary seat-btn" disabled>${seat.name}</button>
            </c:when>
            <c:otherwise>
                <button class="btn btn-success seat-btn seat-btn-active" data-seat-id="${seat.id}"
                        onclick="toggleSeat(this)">${seat.name}</button>
            </c:otherwise>
        </c:choose>

        <c:set var="seatIndex" value="${seatIndex + 1}"/>
    </c:forEach>
</div>

<div class="legend d-flex justify-content-center align-items-center gap-3 flex-wrap">
    <div>
        <button class="btn btn-success"></button>
        <span>Ghế trống</span></div>
    <div>
        <button class="btn btn-warning"></button>
        <span>Đã chọn</span></div>
    <div>
        <button class="btn btn-secondary"></button>
        <span>Đã đặt</span></div>
</div>

<form method="POST" class="mt-4">
    <input type="hidden" id="selectedSeatList" name="selectedSeatList" value="">
    <input type="hidden" name="playtimeId" value="${playtimeId}">
    <button type="submit" class="btn btn-warning px-4 py-2" id="bookBtn" disabled>Đặt Vé</button>
</form>

<a href="customer" class="btn btn-outline-primary mt-3">Quay về</a>

<script>
    function toggleSeat(seatElement) {
        if (!seatElement.disabled) {
            seatElement.classList.toggle("btn-warning");
            seatElement.classList.toggle("btn-success");
            updateSelectedSeats();
        }
    }

    function updateSelectedSeats() {
        let selectedSeats = document.querySelectorAll(".btn-warning.seat-btn");
        let seatInput = document.getElementById("selectedSeatList");
        let seatIds = Array.from(selectedSeats).map(seat => seat.getAttribute("data-seat-id"));
        seatInput.value = seatIds.join(",");
        document.getElementById("bookBtn").disabled = selectedSeats.length === 0;
    }
</script>

</body>
</html>
