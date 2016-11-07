<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <c:if test="${needAlert == true}">
        <script>
            alert("The dish is present in orders");
        </script>
    </c:if>
</head>
<body>

<header>
    <nav>
        <ul>
            <li><a href="/admin/employees">Employees</a></li>
            <li><a href="/admin/dishes">Menu</a></li>
            <li><a href="/admin/orders">Orders</a></li>
            <li><a href="/admin/storage">Storage</a></li>
        </ul>
    </nav>
</header>

<div class="dishCentralPanel">
    <button class="selectedPositionButton" onclick="location.href='/admin/dishes';">< Back to list</button>;
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

    <div class="dishButtons">
        <button onclick="location.href='/admin/update_dishId=${dish.ID}';">Update Info</button>
        <button onclick="location.href='/admin/delete_dishId=${dish.ID}';">Delete</button>
    </div>

</div>

</body>
</html>
