<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="/resources/dashboards/assets/css/style.css">

</head>
<body>

<div class="content">
    <div class="header">

        <div class="col_1">
            <p>
                <strong>Name:</strong> ${myUserData.firstName} <br>
                <strong>Surname:</strong> ${myUserData.lastName} <br>
                <strong>Birthday:</strong> ${myUserData.birthday} <br>
                <strong>Email:</strong> ${myUserData.email}<br>
            </p>

        </div>

        <div class="col_2">
            <form method="get" action="/logout">
                <button type="submit" class="btn-out">Log out</button>
            </form>
        </div>
    </div>
    <div class="body">
        <p>
            <c:if test="${param.regSuccess != null}">
                <div class="alert alert-success">
                    <p>Registration successful.</p>
                </div>
            </c:if>
        </p>
    </div>
</div>
</body>
</html>
