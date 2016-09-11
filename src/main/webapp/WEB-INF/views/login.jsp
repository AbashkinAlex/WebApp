<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8"/>

    <title>Front End</title>

    <link rel="stylesheet" href="resources/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/assets/css/style.css">
    <script src="resources/assets/js/jquery.js"></script>
    <script src="resources/assets/js/jquery.validate.min.js"></script>

</head>
<body>

<div class="row">

    <%--Registration--%>
    <div class="col-md-6 reg">
        <div class="row">
            <div class="col-md-2"></div>
            <div class="col-md-8">
                <h2>REGISTRATION</h2>
                <c:if test="${param.regSuccess != null}">
                    <div class="alert alert-success">
                        <p>Registration successful.</p>
                    </div>
                </c:if>
                <c:if test="${param.regEerror != null}">
                    <div class="alert alert-danger">
                        <p>Registration error! Please try again...</p>
                    </div>
                </c:if>
            </div>
        </div>
        <div class="form-log">
            <form:form method="post" action="/newUser" modelAttribute="user" id="contact-form" class="form-horizontal">

                <div class="control-group controls row">
                    <div class="col-md-2"></div>
                    <div class="col-md-3">
                        <h5>Name* :</h5>
                    </div>
                    <div class="col-md-5">
                        <form:input type="text" path="firstName" id="firstName" class="reg" name="name"
                                    placeholder="enter your Name"/>
                    </div>
                </div>

                <div class="control-group controls row">
                    <div class="col-md-2"></div>
                    <div class="col-md-3">
                        <h5>Surname* :</h5>
                    </div>
                    <div class="col-md-5">
                        <form:input type="text" path="lastName" name="lastName" id="lastName" class="reg"
                                    placeholder="enter your Surname"/>
                    </div>

                </div>

                <div class="control-group controls row">
                    <div class="col-md-2"></div>
                    <div class="col-md-3">
                        <h5>Email* :</h5>
                    </div>
                    <div class="col-md-5">
                        <form:input type="email" path="email" id="email" name="email" class="reg"
                                    placeholder="enter your Email"/>
                        <c:if test="${param.emailExist != null}">
                            <div class="alert alert-danger">
                                <p>This email already exist, please choose other.</p>
                            </div>
                        </c:if>
                    </div>

                </div>

                <div class="control-group controls row">
                    <div class="col-md-2"></div>
                    <div class="col-md-3 pass">
                        <h5>Password* :</h5>
                    </div>
                    <div class="col-md-5">
                        <form:input type="password" path="password" name="password" id="password" class="reg"
                                    placeholder="enter your Password"/>
                    </div>
                </div>

                <div class="control-group controls row">
                    <div class="col-md-2"></div>
                    <div class="col-md-3 pass">
                        <h5>Confirm Password* :</h5>
                    </div>
                    <div class="col-md-5">
                        <input type="password" class="reg" id="conf" name="conf" placeholder="repeat your Password"
                               required>
                    </div>
                </div>

                <div class="control-group controls row">
                    <div class="col-md-2"></div>
                    <div class="col-md-3">
                        <h5>Birthday :</h5>
                    </div>
                    <div class="col-md-5">
                        <form:input type="date" pattern="yyyy-mm-dd" min="1900-01-01" max="2015-01-31" path="birthday"
                               name="birthday"
                               id="birthday" class="reg" required="false"/>
                    </div>
                </div>

                <div class="control-group controls row">
                    <div class="col-md-2"></div>
                    <div class="col-md-3">
                        <h5>Message :</h5>
                    </div>
                    <div class="col-md-5">
                        <textarea class="message" path="message" name="message" id="message"
                                  maxlength="500" placeholder="write about yourself..." ></textarea>
                    </div>
                </div>
                <div class="control-group controls row">
                    <div class="col-md-2"></div>
                    <div class="col-md-3">
                        <h5>Choose your role :</h5>
                    </div>
                    <div class="col-md-5">
                        <form:select path="userProfiles" items="${roles}" multiple="false" itemValue="id"
                                     itemLabel="type"
                                     class="reg">
                        </form:select>
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

    <%--Login--%>
    <div class="col-md-6 log">
        <div class="row">
            <div class="col-md-2"></div>
            <div class="col-md-8">
                <h2>LOGIN</h2>
                <c:if test="${param.error != null}">
                    <div class="alert alert-danger">
                        <p>Invalid username or password.</p>
                    </div>
                </c:if>
                <c:if test="${param.logout != null}">
                    <div class="alert alert-success">
                        <p>You have been logged out successfully.</p>
                    </div>
                </c:if>
            </div>
        </div>
        <div class="form-log">
            <form:form method="post" action="/login" id="contact-formL" class="form-horizontal">

                <div class="control-group controls row">
                    <div class="col-md-2"></div>
                    <div class="col-md-3">
                        <h5>Enter your Email</h5>
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