<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Dashboard</title>
    <link rel="stylesheet" href="/resources/dashboards/assets/css/style.css">

    <%--jGalllery--%>
    <link rel="stylesheet" type="text/css" media="all" href="/resources/dashboards/assets/css/font-awesome.min.css"/>
    <link rel="stylesheet" type="text/css" media="all"
          href="/resources/dashboards/assets/css/jgallery.min.css?v=1.5.0"/>


    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">


    <script type="text/javascript" src="/resources/dashboards/assets/js/jquery-2.0.3.min.js"></script>
    <script type="text/javascript" src="/resources/dashboards/assets/js/jgallery.min.js?v=1.5.0"></script>
    <script type="text/javascript" src="/resources/dashboards/assets/js/touchswipe.min.js"></script>

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
        <div id="gallery">



    </div>


</div>


</body>
</html>
