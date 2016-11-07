<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="utf-8" contentType="text/html;charset=UTF-8" %>
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

<div class="wrap">

    <div class="wrap-1">
        <form action="/admin/findIngredientsByName">
            <input autocomplete="off" type="text" placeholder="find by name" name="ingredientName">
            <button>Search</button>
        </form>
    </div>

    <c:if test="${not empty storage}">

        <table class="storageTable">
            <tr>
                <td>
                    <table>
                        <tr class="title">
                            <th>Name</th>
                            <th>Amount</th>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="wrap-2">
                        <table>
                            <c:forEach items="${storage}" var="ingredient">
                                <tr class="hover" onclick="location.href='/admin/storage/ingredientId=${ingredient.ingredient.ID}'">
                                    <td>${ingredient.ingredient.name}</td>
                                    <td>${ingredient.amount}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </td>
            </tr>
        </table>

    </c:if>
    <c:if test="${empty storage}">
        There is no ingredient with name like '${ingredientName}'
    </c:if>

    <div class="buttonCreate">
        <button onclick="location.href='/admin/newIngredient'">Add new ingredient</button>
    </div>

</div>


</body>
</html>
