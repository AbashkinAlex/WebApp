<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <script type="text/javascript" src="/resources/dashboards/assets/js/jquery-2.0.3.min.js"></script>
    <script type="text/javascript" src="/resources/dashboards/assets/js/jgallery.min.js?v=1.5.0"></script>
    <script type="text/javascript" src="/resources/dashboards/assets/js/touchswipe.min.js"></script>


</head>
<%--<body style="width: 900px; margin: 100px auto; height: auto;">--%>
<body>
<div class="content">
    <div class="header">
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
        <div class="row_2">
            <form:form method="post"
                       action="/adminDash/uploadPictures"
                       enctype="multipart/form-data">
                <input type="hidden" name="Id" value="${myUserData.id}">
                <input id="upload-file-input"
                       name="pictures"
                       type="file"
                       multiple="true"
                       onchange="$('#fileName').val($(this).val());"
                       accept="image/png,image/jpeg,image/jpg"
                />
                <input type="submit" value="Submit"/>
            </form:form>
        </div>
    </div>
</div>


</body>
</html>
