<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <title>Title</title>
</head>
<body>

<header>
    <div class="logo">
        <a href="/">GRILLPUB</a>
    </div>
    <nav>
        <ul>
            <li><a href="/">Home</a></li>
            <li><a href="/employees">Employees</a></li>
            <li><a href="/dishes">Menu</a></li>
            <li><a href="/contacts">Contacts</a></li>
            <li><a href="/restaurantScheme">Restaurant Scheme</a></li>
        </ul>
    </nav>
</header>

<div class="workTime">
    <ul>
        <li>
            Monday-Thursday:
            <p></p>9:00-22:00
        </li>
        <li>
            Friday-Sunday:
            <p></p>9:00-24:00
        </li>
    </ul>
</div>

<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d1512.0268231433756!2d30.637656696296293!3d50.40540545794618!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x40d4c5ab06b7e217%3A0x93fb9e79c6780115!2sMykhaila+Drahomanova+St%2C+40%2C+Kyiv%2C+02000!5e0!3m2!1sen!2sua!4v1477559891863"
        width="800" height="600" frameborder="0" style="border:0" allowfullscreen></iframe>


    <footer>
        <ul>
            <li>
                <img height="20" width="20" src="<c:url value="/images/location-icon.png"/>"/>
                Kiev, Dragomanova 40
            </li>
            <li>
                <img height="20" width="20" src="<c:url value="/images/phone-icon.png"/>"/>
                +380442204165
            </li>
            <li>
                <img height="20" width="20" src="<c:url value="/images/email-icon.png"/>"/>
                grilpab@gmail.com
            </li>
        </ul>
    </footer>


</body>
</html>
