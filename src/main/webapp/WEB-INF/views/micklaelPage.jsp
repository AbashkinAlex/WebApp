<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />

    <title>Front End</title>

    <link rel="stylesheet" href="resources/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/assets/css/style.css">


    <script src="resources/assets/js/jquery.js"></script>
    <script src="resources/assets/js/jquery.validate.min.js"></script>

</head>
<body>

<div class="row">

    <%--registration--%>
    <div class="col-md-6 reg">
        <div class="row">
            <div class="col-md-2"></div>
            <div class="col-md-8">
                <h2>REGISTRATION</h2>
            </div>
        </div>
        <div class="form-log">
            <form:form method="post" action="/saveUser" id="contact-form" class="form-horizontal">

                <div class="control-group controls row">
                    <div class="col-md-2"></div>
                    <div class="col-md-3">
                        <h5>Enter your First Name</h5>
                    </div>
                    <div class="col-md-5">
                        <input type="text" class="reg"   name="name" id="name" value="${dto.email}" required>
                    </div>

                </div>
                <div class="control-group controls row">
                    <div class="col-md-2"></div>
                    <div class="col-md-3">
                        <h5>Enter your Second Name</h5>
                    </div>
                    <div class="col-md-5">
                        <input type="text" class="reg"  name="surname" id="surname" value="${dto.email}" required>
                    </div>

                </div>
                <div class="control-group controls row">
                    <div class="col-md-2"></div>
                    <div class="col-md-3">
                        <h5>Enter your Birthday</h5>
                    </div>
                    <div class="col-md-5">
                        <input type="date" class="reg"   name="birthday" id="birthday" value="${dto.email}" >
                    </div>

                </div>
                <div class="control-group controls row">
                    <div class="col-md-2"></div>
                    <div class="col-md-3">
                        <h5>Enter your Email</h5>
                    </div>
                    <div class="col-md-5">
                        <input type="email" class="reg"  placeholder="" name="email" id="email" value="${dto.email}" required>
                    </div>

                </div>
                <div class="control-group controls row">
                    <div class="col-md-2"></div>
                    <div class="col-md-3 pass">
                        <h5>Enter your Password</h5>
                    </div>
                    <div class="col-md-5">
                        <input type="password" class="reg"  id="password" placeholder="" name="password"  value="${dto.password}">
                    </div>

                </div>

                <div class="control-group controls row">
                    <div class="col-md-2"></div>
                    <div class="col-md-3 pass">
                        <h5>Confirm your Password</h5>
                    </div>
                    <div class="col-md-5">
                        <input type="password" class="reg" id="conf"  placeholder="" name="conf">
                    </div>

                </div>
                <div class="control-group controls row">
                    <div class="col-md-2"></div>
                    <div class="col-md-3">
                        <h5>Enter your Message</h5>
                    </div>
                    <div class="col-md-5">
                        <textarea  class="message"  name="message" id="message"></textarea>
                    </div>

                </div>

                <div class=" form-actions row">
                    <div class="col-md-2"></div>
                    <div class="col-md-3"></div>
                    <div class=" col-md-4">
                        <input role="button" type="submit" class="sign btn btn-primary  btn-block" value="Registration">
                    </div>

                </div>

            </form:form>
        </div>
    </div>

    <%--login--%>
    <div class="col-md-6 log">
        <div class="row">
            <div class="col-md-2"></div>
            <div class="col-md-8">
                <h2>LOGIN</h2>
            </div>
        </div>
        <div class="form-log">
            <form:form method="post" action="/userLogin" id="contact-formL" class="form-horizontal">

                <div class="control-group controls row">
                    <div class="col-md-2"></div>
                    <div class="col-md-3">
                        <h5>Enter your Email</h5>

                    </div>
                    <div class="col-md-5">
                        <input type="email" class="reg"  placeholder="" name="email" id="emailL" value="${dto.email}" required>
                    </div>

                </div>
                <div class="control-group controls row">
                    <div class="col-md-2"></div>
                    <div class="col-md-3 pass">
                        <h5>Enter your Password</h5>

                    </div>
                    <div class="col-md-5">
                        <input type="password" class="reg"  id="passwordL" placeholder="" name="password"  value="${dto.password}" required >
                    </div>

                </div>

                <div class=" form-actions row">
                    <div class="col-md-2"></div>
                    <div class="col-md-3"></div>
                    <div class=" col-md-4">
                        <input role="button" type="submit" class="sign btn btn-primary  btn-block" value="Login">
                    </div>
                </div>
            </form:form>
        </div>
    </div>

</div>

<!-- Javascript
================================================== -->

<script src="resources/assets/js/jquery.nav.js"></script>
<script src="resources/assets/js/bootstrap.min.js"></script>
<script src="resources/assets/js/home.js"></script>

</body>

</html>