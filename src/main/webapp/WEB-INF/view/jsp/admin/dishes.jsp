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

<c:if test="${needToAddTheDishToOrder == false}">

    <div class="wrap-1">
        <form action="/admin/findDishesByName" method="get">
            <input autocomplete="off" type="text" placeholder="find by name" name="dishName">
            <button>Search</button>
        </form>
    </div>

    <c:if test="${not empty dishes}">

        <table class="mainTable">
            <tr>
                <td>
                    <table>
                        <tr class="title">
                            <th>Name</th>
                            <th>Category</th>
                            <th>Price, uah</th>
                            <th>Weight, g</th>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="wrap-2">
                        <table>
                            <c:forEach items="${dishes}" var="dish">
                                <tr class="hover" onclick="location.href='/admin/dishes/dishId=${dish.ID}'">
                                    <td class="firstColumn">${dish.name}</td>
                                    <td>${dish.dishCategory}</td>
                                    <td>${dish.price}</td>
                                    <td>${dish.weight}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </td>
            </tr>
        </table>
    </c:if>
    </c:if>

    <c:if test="${needToAddTheDishToOrder == true}">

        <div class="wrap-1">
            <form action="/admin/findDishesByName">
                <input autocomplete="off" type="text" placeholder="find by name" name="dishName">
                <button>Search</button>
            </form>
        </div>

        <c:if test="${not empty dishes}">

            <table class="mainTable">
                <tr>
                    <td>
                        <table>
                            <tr class="title">
                                <th>Name</th>
                                <th>Category</th>
                                <th>Price, uah</th>
                                <th>Weight, g</th>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="wrap-2">
                            <table>
                                <c:forEach items="${dishes}" var="dish">
                                    <tr class="hover"
                                        onclick="location.href='/admin/addDish_orderId=${orderId}&dishId=${dish.ID}'">
                                        <td class="firstColumn">${dish.name}</td>
                                        <td>${dish.dishCategory}</td>
                                        <td>${dish.price}</td>
                                        <td>${dish.weight}</td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </td>
                </tr>
            </table>
        </c:if>
    </c:if>
    <c:if test="${empty dishes}">
        There is no dishes with name like '${dishesName}'
    </c:if>

    <div class="buttonCreate">
        <button onclick="location.href='/admin/newDish'">Create new dish</button>
    </div>
</div>
</body>
</html>
