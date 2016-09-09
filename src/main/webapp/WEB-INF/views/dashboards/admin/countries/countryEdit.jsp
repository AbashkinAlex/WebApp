<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Utility bills</title>
    <jsp:include page="/WEB-INF/views/dashboards/template/header.jsp"/>
</head>
<body>

<div class="wrapper">
    <div class="sidebar" data-color="purple" data-image="/resources/dashboards/assets/img/sidebar-4.jpg">


        <div class="sidebar-wrapper">
            <div class="logo">
                <a href="/home" class="simple-text">
                    Modern Town
                </a>
            </div>

            <ul class="nav">

                <li>
                    <a href="/adminDash/profile">
                        <i class="pe-7s-user"></i>

                        <p>Admin Profile</p>
                    </a>
                </li>
                <li>
                    <a href="/listOfUsers">
                        <i class="pe-7s-note2"></i>

                        <p>List of users</p>
                    </a>
                </li>
                <li>
                    <a href="/listOfCountry">
                        <i class="pe-7s-note2"></i>

                        <p>List of country</p>
                    </a>
                </li>
                <li>
                    <a href="/listOfCity">
                        <i class="pe-7s-note2"></i>

                        <p>List of city</p>
                    </a>
                </li>
                <li>
                    <a href="/listOfStreet">
                        <i class="pe-7s-note2"></i>

                        <p>List of streets</p>
                    </a>
                </li>

                <li>
                    <a href="/showMap">
                        <i class="pe-7s-map-marker"></i>

                        <p>Maps</p>
                    </a>
                </li>

            </ul>
        </div>
    </div>

    <div class="main-panel">
        <nav class="navbar navbar-default navbar-fixed">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse"
                            data-target="#navigation-example-2">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">Profile</a>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-left">

                    </ul>

                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a href="/accountLogout">
                                Log out
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>


        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-8">
                        <div class="card">
                            <div class="header">
                                <h4 class="title">Edit Country</h4>
                            </div>
                            <div class="content">
                                <form action="/addNewCountry" method="post">
                                    <form method="post" action="/addNewCountry">

                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label>Country Name</label>
                                                    <input type="hidden" name="countryId" value=${country.id}>
                                                    <input type="text" name="countryName" class="form-control"
                                                           value="${country.name}">

                                                </div>
                                            </div>
                                        </div>
                                        <input type="submit" class="btn btn-danger btn-sm custom-width"
                                               value="Accept">
                                    </form>

                                </form>


                                <div class="clearfix"></div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card card-user">
                            <div class="image">
                                <img src="https://ununsplash.imgix.net/photo-1431578500526-4d9613015464?fit=crop&fm=jpg&h=300&q=75&w=400"
                                     alt="..."/>
                            </div>
                            <div class="content">
                                <div class="author">
                                    <a href="#">
                                        <img class="avatar border-gray"
                                             src="/resources/dashboards/assets/img/faces/face-3.jpg" alt="..."/>

                                        <h4 class="title">${myUserData.firstName}<br/>
                                            <small>${myUserData.secondName}</small>
                                        </h4>
                                    </a>
                                </div>

                            </div>
                            <hr>
                            <div class="text-center">
                                </button>

                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>


        <footer class="footer">
            <div class="container-fluid">
                <nav class="pull-left">
                    <ul>
                        <li>
                            <a href="/home">
                                Home
                            </a>
                        </li>

                    </ul>
                </nav>
                <p class="copyright pull-right">
                    &copy; 2016 <a href="/home">Agios</a>, & Co
                </p>
            </div>
        </footer>

    </div>
</div>


</body>

<jsp:include page="/WEB-INF/views/dashboards/template/footer.jsp"/>

</html>