<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Front End</title>
    <%--------------------------------------------------------------------%>

    <link rel="stylesheet" href="resources/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/assets/css/style.css">
    <script src="resources/assets/js/jquery.js"></script>
    <script src="resources/assets/js/jquery.validate.min.js"></script>

</head>

<body>

<%--registration--%>
<div class="col-md-6 reg">
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <h2>REGISTRATION</h2>
        </div>
    </div>
    <div class="form-log">
        <form:form method="post" action="/newUser" modelAttribute="user" id="contact-form" class="form-horizontal">

            <div class="control-group controls row">
                <div class="col-md-2"></div>
                <div class="col-md-3">
                    <h5>Enter your First Name</h5>
                </div>
                <div class="col-md-5">
                    <form:input type="text" path="firstName" id="firstName" class="reg" name="name"/>
                        <%--<input type="text" class="reg"   name="name" id="name" value="${dto.email}" required>--%>
                </div>

            </div>
            <div class="control-group controls row">
                <div class="col-md-2"></div>
                <div class="col-md-3">
                    <h5>Enter your Second Name</h5>
                </div>
                <div class="col-md-5">
                        <%--<input type="text" class="reg"  name="surname" id="surname" value="${dto.email}" required>--%>
                    <form:input type="text" path="lastName" name="lastName" id="lastName" class="reg"/>
                </div>

            </div>

            <div class="control-group controls row">
                <div class="col-md-2"></div>
                <div class="col-md-3">
                    <h5>Enter your Birthday</h5>
                </div>
                <div class="col-md-5">
                    <form:input type="date" min="1900-01-01" max="2015-01-01" path="birthday" name="birthday" id="birthday" class="reg"/>
                        <%--<form:input path="birthday" id="birthdayInput" placeholder="MM/DD/YYYY" />--%>

                        <%--<input type="date" class="reg" name="birthday" id="birthday" value="${dto.email}">--%>
                </div>

            </div>

            <div class="control-group controls row">
                <div class="col-md-2"></div>
                <div class="col-md-3">
                    <h5>Enter you Email</h5>
                </div>
                <div class="col-md-5">
                        <%--<input type="text" class="reg"  name="surname" id="surname" value="${dto.email}" required>--%>
                        <%--<form:input type="text" path="email" id="email" class="reg"/>--%>
                    <form:input type="email" path="email" id="email" name="email" class="reg"/>
                </div>

            </div>

            <div class="control-group controls row">
                <div class="col-md-2"></div>
                <div class="col-md-3 pass">
                    <h5>Enter your Password</h5>
                </div>
                <div class="col-md-5">
                    <form:input type="password" path="password" name="password" id="password" class="reg"/>
                    <input type="hidden" class="reg" id="password" placeholder="" name="password">
                </div>

            </div>

            <div class="control-group controls row">
                <div class="col-md-2"></div>
                <div class="col-md-3 pass">
                    <h5>Confirm your Password</h5>
                </div>
                <div class="col-md-5">
                    <%--<form:input type="password" path="password" name="password" id="password" class="reg"/>--%>
                    <input type="password" class="reg" id="conf" placeholder="" name="conf">
                </div>

            </div>


            <div class="control-group controls row">
                <div class="col-md-2"></div>
                <div class="col-md-3">
                    <h5>Enter you ROLE</h5>
                </div>
                <div class="col-md-5">
                        <%--<input type="text" class="reg"  name="surname" id="surname" value="${dto.email}" required>--%>
                    <form:select path="userProfiles" items="${roles}" multiple="true" itemValue="id" itemLabel="type"
                                 class="form-control input-sm"/>
                </div>

            </div>

            <%--<div class="control-group controls row">--%>
            <%--<div class="col-md-2"></div>--%>
            <%--<div class="col-md-3 pass">--%>
            <%--<h5>Confirm your Password</h5>--%>
            <%--</div>--%>
            <%--<div class="col-md-5">--%>
            <%--<input type="password" class="reg" id="conf"  placeholder="" name="conf">--%>
            <%--</div>--%>

            <%--</div>--%>
            <%--<div class="control-group controls row">--%>
            <%--<div class="col-md-2"></div>--%>
            <%--<div class="col-md-3">--%>
            <%--<h5>Enter your Message</h5>--%>
            <%--</div>--%>
            <%--<div class="col-md-5">--%>
            <%--<textarea  class="message"  name="message" id="message"></textarea>--%>
            <%--</div>--%>

            <%--</div>--%>

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
        <form:form method="post" action="/login" id="contact-formL" class="form-horizontal">

            <div class="control-group controls row">
                <div class="col-md-2"></div>
                <div class="col-md-3">
                    <h5>Enter your email</h5>

                </div>
                <div class="col-md-5">
                    <input type="email" class="reg" placeholder="Enter Username" name="email" id="email" required>

                </div>

            </div>
            <div class="control-group controls row">
                <div class="col-md-2"></div>
                <div class="col-md-3 pass">
                    <h5>Enter your Password</h5>

                </div>
                <div class="col-md-5">
                    <input type="password" class="reg" id="password" placeholder="Enter Password" name="password"
                           required>
                </div>

            </div>

            <input type="hidden" name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>

            <div class=" form-actions row">
                <div class="col-md-2"></div>
                <div class="col-md-3"></div>
                <div class=" col-md-4">
                    <input role="button" type="submit" class="sign btn btn-primary  btn-block" value="Log in">
                </div>
            </div>
        </form:form>
    </div>
</div>

<!-- Javascript
================================================== -->

<script src="resources/assets/js/jquery.nav.js"></script>
<script src="resources/assets/js/bootstrap.min.js"></script>
<script src="resources/assets/js/home.js"></script>

<%--<c:url var="loginUrl" value="/login" />--%>
<%--<form action="${loginUrl}" method="post" class="form-horizontal">--%>
<%--<c:if test="${param.error != null}">--%>
<%--<div class="alert alert-danger">--%>
<%--<p>Invalid username and password.</p>--%>
<%--</div>--%>
<%--</c:if>--%>
<%--<c:if test="${param.logout != null}">--%>
<%--<div class="alert alert-success">--%>
<%--<p>You have been logged out successfully.</p>--%>
<%--</div>--%>
<%--</c:if>--%>
<%--<div class="input-group input-sm">--%>
<%--<label class="input-group-addon" for="username"><i class="fa fa-user"></i></label>--%>
<%--<input type="text" class="form-control" id="username" name="email" placeholder="Enter Username" required>--%>
<%--</div>--%>
<%--<div class="input-group input-sm">--%>
<%--<label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label> --%>
<%--<input type="password" class="form-control" id="password" name="password" placeholder="Enter Password" required>--%>
<%--</div>--%>
<%--<input type="hidden" name="${_csrf.parameterName}"--%>
<%--value="${_csrf.token}" />--%>

<%--<div class="form-actions">--%>
<%--<input type="submit"--%>
<%--class="btn btn-block btn-primary btn-default" value="Log in">--%>
<%--</div>--%>
<%--</form>--%>


</body>
</html>