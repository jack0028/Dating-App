<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dating</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <style>
        body {
            background: linear-gradient(to bottom, #ff7f7f, #ffcccc);
            font-family: 'Arial', sans-serif;
            color: #fff;
        }
        h1, h2 {
            color: #fff;
            font-weight: bold;
        }
        .btn-primary {
            background-color: #ff4d4d;
            border-color: #ff4d4d;
        }
        .btn-primary:hover {
            background-color: #ff3333;
            border-color: #ff3333;
        }
        .modal-content {
            background-color: rgba(255, 255, 255, 0.95);
        }
        .table {
            background-color: rgba(255, 255, 255, 0.9);
        }
        .table th, .table td {
            color: #333;
        }
        .heart-icon {
            color: #ff4d4d;
            font-size: 1.2rem;
            margin-left: 5px;
        }
        .form-group label {
            color: #fff;
        }
    </style>
</head>
<body>
    <div class="container mt-4">
        <h1 class="text-center"><i class="fas fa-heart heart-icon"></i> Dating  <i class="fas fa-heart heart-icon"></i></h1>

        <!-- Add User Button -->
        <div class="d-flex justify-content-end">
            <button class="btn btn-success mb-3" data-toggle="modal" data-target="#addUserModal"><i class="fas fa-user-plus"></i> Add User</button>
        </div>

        <form action="/recommendation" method="get" class="mt-4">
            <div class="form-group">
                <label for="userId">Select User:</label>
                <select class="form-control" id="userId" name="userId" required>
                    <c:forEach var="user" items="${users}">
                        <option value="${user.id}">${user.name} (${user.gender}, ${user.age})</option>
                    </c:forEach>
                </select>
            </div>

            <!-- Number of Recommendations Box -->
            <div class="form-group">
                <label for="topN">Number of Recommendations:</label>
                <input type="number" class="form-control" id="topN" name="topN" value="2" min="1" max="1000" required>
            </div>

            <button type="submit" class="btn btn-primary btn-block"><i class="fas fa-search"></i> Get Recommendations</button>
        </form>

        <hr>

        <h2 class="mt-4 text-danger"><i class="fas fa-heart"></i> Recommendations:</h2>
        <div class="table-responsive">
            <table class="table table-bordered mt-3">
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
                    <c:if test="${not empty recommendations}">
                        <c:forEach var="match" items="${recommendations}" varStatus="status">
                            <tr>
                                <td>${status.count}</td>
                                <td>${match.name}</td>
                                <td>${match.gender}</td>
                                <td>${match.age}</td>
                                <td>${match.interests}</td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <c:if test="${empty recommendations}">
                        <tr>
                            <td colspan="5" class="text-center">No recommendations found.</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>

    <!-- Modal for Adding User -->
    <div class="modal fade" id="addUserModal" tabindex="-1" role="dialog" aria-labelledby="addUserModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="addUserModalLabel"><i class="fas fa-user-plus"></i> Add New User</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form action="/users" method="POST">
                        <div class="form-group">
                            <label for="username">Username</label>
                            <input type="text" class="form-control" id="username" name="name" required>
                        </div>
                        <div class="form-group">
                            <label for="age">Age</label>
                            <input type="number" class="form-control" id="age" name="age" required>
                        </div>
                        <div class="form-group">
                            <label for="gender">Gender</label>
                            <select class="form-control" id="gender" name="gender" required>
                                <option value="Male">Male</option>
                                <option value="Female">Female</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="interests">Interests (Comma separated)</label>
                            <input type="text" class="form-control" id="interests" name="interests" required>
                            <small class="form-text text-muted">Enter interests separated by commas (e.g., "Sports, Music, Reading").</small>
                        </div>
                        <button type="submit" class="btn btn-primary btn-block"><i class="fas fa-save"></i> Save User</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
