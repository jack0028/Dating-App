<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Recommendation</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <style>
        /* Romantic theme styling */
        body {
            font-family: 'Arial', sans-serif;
            background: linear-gradient(135deg, #ffcccc, #ff6699);
            color: #333;
        }

        h1, h3, h4 {
            color: #fff;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);
        }

        .btn-secondary {
            background-color: #ff6699;
            border-color: #ff3366;
            color: white;
        }

        .btn-secondary:hover {
            background-color: #ff3366;
            border-color: #cc0055;
        }

        .table thead {
            background-color: #ff3366;
            color: white;
        }

        .table tbody tr:hover {
            background-color: #ffe6e6;
        }

        .badge-info {
            background-color: #ff6699;
            color: white;
            font-size: 0.85rem;
            margin: 2px;
        }

        .badge-info:hover {
            background-color: #ff3366;
        }

        .heart-icon {
            color: #ff3366;
        }

        /* Responsive adjustments */
        @media (max-width: 576px) {
            h1 {
                font-size: 1.8rem;
            }

            h3, h4 {
                font-size: 1.2rem;
            }

            .btn {
                font-size: 0.9rem;
            }

            .table {
                font-size: 0.85rem;
            }
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center">
            <i class="heart-icon fas fa-heart"></i> Matching Recommendations 
            <i class="heart-icon fas fa-heart"></i>
        </h1>

        <div class="mb-4 text-center">
            <a href="/" class="btn btn-secondary">Back to Home</a>
        </div>

        <h3>User: ${user.name} (${user.gender}, ${user.age})</h3>
        <h4>Showing the top ${topN} recommendations based on matching interests:</h4>

        <table class="table table-bordered mt-4">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Name</th>
                    <th>Gender</th>
                    <th>Age</th>
                    <th>Matching Interests</th>
                </tr>
            </thead>
            <tbody>
                <!-- Dynamically populate recommendations -->
                <c:if test="${not empty recommendations}">
                    <c:forEach var="match" items="${recommendations}" varStatus="status">
                        <tr>
                            <td>${status.count}</td>
                            <td>${match.name}</td>
                            <td>${match.gender}</td>
                            <td>${match.age}</td>
                            <td>
                                <!-- Show the list of matching interests -->
                                <c:forEach var="interest" items="${match.interests}">
                                    <span class="badge badge-info">${interest}</span>
                                </c:forEach>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>

                <!-- If no recommendations found -->
                <c:if test="${empty recommendations}">
                    <tr>
                        <td colspan="6" class="text-center">No recommendations found based on the selected interests.</td>
                    </tr>
                </c:if>
            </tbody>
        </table>
    </div>

    <!-- Font Awesome for heart icons -->
</body>
</html>
