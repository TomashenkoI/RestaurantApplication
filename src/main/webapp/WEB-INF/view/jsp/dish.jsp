<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
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

<div class="dishCentralPanel">
    <button class="selectedPositionButton" onclick="location.href='/dishes';">< Back to list</button>;
    <div class="dishPhoto">
        <img width="300" height="300" src="<c:url value="${picturePath}"/>"/>
    </div>
    <table class="table">

        <tr>
            <th>Name</th>
            <td>${dish.name}</td>
        </tr>
        <tr>
            <th>Category</th>
            <td>${dish.dishCategory}</td>
        </tr>
        <tr>
            <th>Price, uah</th>
            <td>${dish.price}</td>
        </tr>
        <tr>
            <th>Weight, g</th>
            <td>${dish.weight}</td>
        </tr>

    </table>
    <ul class="listOfIngredients">
        <h3>List of ingredients :</h3>
        <c:forEach items="${ingredientsToDish}" var="ingredient">
            <li>${ingredient.name}</li>
        </c:forEach>
    </ul>
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
