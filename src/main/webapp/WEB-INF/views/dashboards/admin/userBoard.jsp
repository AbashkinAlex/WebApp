<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
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

    <%--Modal window--%>


</head>
<%--<body style="width: 900px; margin: 100px auto; height: auto;">--%>
<body>

<div class="content">
    <div class="header">
        <c:if test="${param.regSuccess != null}">

        </c:if>
        <div class="col_1">
            <p>
                <strong>Name:</strong> ${myUserData.firstName} <br>
                <strong>Surname:</strong> ${myUserData.lastName} <br>
                <strong>Birthday:</strong> ${myUserData.birthday} <br>
                <strong>Email:</strong> ${myUserData.email}
            </p>
        </div>

        <div class="col_2">
            <form method="get" action="/logout">
                <button type="submit" class="btn-out">Log out</button>
            </form>
        </div>
    </div>


    <div class="body">
        <div id="gallery">
            <a href="/resources/dashboards/assets/img/large/1.jpg"><img
                    src="/resources/dashboards/assets/img/thumbs/1.jpg" alt="Photo 1"/></a>
            <a href="/resources/dashboards/assets/img/large/2.jpg"><img
                    src="/resources/dashboards/assets/img/thumbs/2.jpg" alt="Photo 2"/></a>
            <a href="/resources/dashboards/assets/img/large/3.jpg"><img
                    src="/resources/dashboards/assets/img/thumbs/3.jpg" alt="Photo 3"/></a>
        </div>
        <script type="text/javascript">
            $(function () {
                $("#gallery").jGallery({
                    "transitionCols": "1",
                    "transitionRows": "1",
                    "thumbnailsPosition": "bottom",
                    "thumbType": "image",
                    "backgroundColor": "FFFFFF",
                    "textColor": "000000",
                    "mode": "standard"
                });
            });
        </script>


        <%--<div class="row_1">--%>
        <%--<img src="" class="image">--%>
        <%--</div>--%>

        <div class="row_2">

            <input type="button" class="btn-foto" value="Add new photo">

        </div>

    </div>


</div>

</body>
</html>
