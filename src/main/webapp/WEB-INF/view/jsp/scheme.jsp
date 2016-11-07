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

    <div class="schemaCentralPanel">
        <img height="564" width="1000" src="<c:url value="/images/club_shema_1.jpg"/>"/>
    </div>

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
