<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Login</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="d-flex justify-content-center align-items-center min-vh-100">
<div class="container">
    <div class="row justify-content-center">
        <div class="col-sm-8 col-md-6 col-lg-4">
            <div class="card p-4 shadow-sm">
                <h2 class="text-center mb-4">Login</h2>
                <form action="login" method="POST">

                    <div class="mb-3">
                        <label for="username" class="form-label">Username</label>
                        <input type="text" id="username" name="username" class="form-control" required>
                    </div>

                    <div class="mb-3">
                        <label for="password" class="form-label">Password</label>
                        <input type="password" id="password" name="password" class="form-control" required>
                    </div>

                    <%--          <div class="mb-3">--%>
                    <%--            <label for="email" class="form-label">Email</label>--%>
                    <%--            <input type="email" id="email" name="email" class="form-control" required>--%>
                    <%--          </div>--%>
                    <div class="mb-3 form-check d-flex justify-content-between text-center">
                        <div>
                            <input type="checkbox" class="form-check-input" id="rememberMe" name="rememberMe">
                            <label class="form-check-label" for="rememberMe">Remember me</label>
                        </div>
                        <a href="forgotPassword">Forget password</a>
                    </div>



                    <button type="submit" class="btn btn-primary w-100">Login</button>
                </form>

                <c:if test="${not empty errorMessage}">
                    <span class="mt-3" style="color: red">${errorMessage}</span>
                </c:if>

                <c:if test="${not empty successMessage}">
                    <span class="mt-3" style="color: green">${successMessage}</span>
                </c:if>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
