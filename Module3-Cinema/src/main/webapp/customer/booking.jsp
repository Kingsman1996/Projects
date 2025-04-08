<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Select Seats - Cinema</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        body {
            background-color: #eef1f5;
            font-family: 'Segoe UI', sans-serif;
        }

        h2 {
            font-weight: bold;
            color: #343a40;
        }

        .booked {
            background-color: #dc3545;
            color: white;
            border: 1px solid #b02a37;
            cursor: not-allowed;
            pointer-events: none;
        }

        .available {
            background-color: #28a745;
            color: white;
            border: 1px solid #1c7430;
            cursor: pointer;
        }

        .selected {
            background-color: #ffc107 !important;
            color: white;
            border: 1px solid #e0a800;
            transform: scale(1.1);
            cursor: pointer;
        }

        .seat {
            width: 42px;
            height: 42px;
            margin: 3px;
            text-align: center;
            line-height: 42px;
            font-weight: bold;
            font-size: 13px;
            border-radius: 8px;
            transition: all 0.2s ease-in-out;
            box-shadow: 0 1px 2px rgba(0,0,0,0.2);
        }

        .seat:hover {
            filter: brightness(1.1);
        }

        .seat-container {
            display: grid;
            grid-template-columns: repeat(8, 1fr);
            gap: 8px;
            justify-content: center;
            margin: 0 auto;
            padding: 20px;
            background: white;
            border-radius: 12px;
            box-shadow: 0 4px 10px rgba(0,0,0,0.1);
        }

        .layout-wrapper {
            display: flex;
            justify-content: space-between;
            align-items: flex-start;
            margin-top: 30px;
        }

        .left-sidebar, .right-sidebar {
            width: 20%;
        }

        .main-content {
            width: 60%;
            text-align: center;
        }

        .form-group label {
            font-weight: bold;
            color: #495057;
        }

        .btn-lg {
            width: 100%;
        }

        .left-sidebar .form-group {
            background: white;
            padding: 20px;
            border-radius: 12px;
            box-shadow: 0 4px 10px rgba(0,0,0,0.1);
        }
    </style>
</head>
<body>
<c:if test="${not empty message}">
    <div class="alert alert-info text-center">${message}</div>
</c:if>

<div class="container mt-4">
    <h2 class="text-center mb-4">Chọn ghế</h2>

    <form method="POST">
        <input type="hidden" name="movieId" value="${movieId}">
        <input type="hidden" name="selectedSeats" id="selectedSeatsInput">

        <div class="layout-wrapper">
            <div class="left-sidebar">
                <div class="form-group">
                    <label for="showtimeSelect">Chọn giờ chiếu:</label>
                    <select id="showtimeSelect" name="selectedShowtime" class="form-control">
                        <c:forEach var="movieTime" items="${movieTimes}">
                            <option value="${movieTime.id}">
                                    ${movieTime.showDate} - ${movieTime.showTime}
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="main-content">
                <div class="seat-container">
                    <c:forEach var="seat" items="${seatList}">
                        <div id="seat-${seat.code}" class="seat
                            ${seat.status == 'available' ? 'available' : 'booked'}">
                                ${seat.code}
                        </div>
                    </c:forEach>
                </div>
            </div>
            <div class="right-sidebar d-flex flex-column align-items-stretch">
                <button type="submit" class="btn btn-primary btn-lg mb-3">Đặt vé</button>
                <a href="customer" class="btn btn-secondary btn-lg">Hủy</a>
            </div>
        </div>
    </form>
</div>

<script>
    let selectedSeats = [];
    document.querySelectorAll('.seat.available').forEach(seat => {
        seat.addEventListener('click', function () {
            const seatCode = this.id.split('-')[1];
            if (this.classList.contains('selected')) {
                this.classList.remove('selected');
                selectedSeats = selectedSeats.filter(s => s !== seatCode);
            } else {
                this.classList.add('selected');
                selectedSeats.push(seatCode);
            }
            document.getElementById("selectedSeatsInput").value = selectedSeats.join(",");
        });
    });
</script>
</body>
</html>
