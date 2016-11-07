<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <c:if test="${needAlert == true}">
        <script>
            alert("Ingredient can't be deleted, it's present in some dish");
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

<div class="ingredientCentralPanel">

    <button class="selectedPositionButton" onclick="location.href='/admin/storage';">< Back to list</button>;

    <table class="table">

        <tr>
            <th>Name</th>
            <td>${ingredient.ingredient.name}</td>
        </tr>
        <tr>
            <th>Amount</th>
            <td>${ingredient.amount}</td>
        </tr>
    </table>

    <div class="ingredientButtons">
        <button onclick="location.href='/admin/update_ingredientId=${ingredient.ingredient.ID}';">Update Info</button>
        <button onclick="location.href='/admin/delete_ingredientId=${ingredient.ingredient.ID}';">Delete ingredient</button>
    </div>
</div>
</body>
</html>
