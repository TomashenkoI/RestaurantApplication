<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
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

    <c:if test="${doesItAlreadyExist == false}">
        <form action="/admin/storage" method="post">
            <table class="table">

                <tr>
                    <th>Name</th>
                    <td><input autocomplete="off" type="text" name="ingredientName" required></td>
                </tr>
                <tr>
                    <th>Amount</th>
                    <td><input autocomplete="off" type="number" name="ingredientAmount" required></td>
                </tr>
            </table>

            <div class="ingredientButtons">
                <button>Submit</button>
            </div>
        </form>
    </c:if>
    <c:if test="${doesItAlreadyExist == true}">
        <c:set var="ingredientId" value="${ingredient.ingredient.ID}"/>
        <form action="/admin/updated_ingredientId=${ingredientId}" method="post">
            <table class="table">

                <tr>
                    <th>Name</th>
                    <td><input autocomplete="off" class="input" type="text" value="${ingredient.ingredient.name}" name="ingredientName"></td>
                </tr>
                <tr>
                    <th>Amount</th>
                    <td><input autocomplete="off" class="input" type="number" value="${ingredient.amount}" name="ingredientAmount"></td>
                </tr>
            </table>

            <div class="ingredientButtons">
                <button>Submit</button>
            </div>
        </form>
    </c:if>
</div>
</body>
</html>
