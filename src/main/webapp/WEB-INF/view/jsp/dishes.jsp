<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="utf-8" contentType="text/html;charset=UTF-8" %>
<html>
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

<div class="wrap">

    <div class="wrap-1">
        <form action="/findDishesByName">
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
                                <tr class="hover" onclick="location.href='/dishes/dishId=${dish.ID}'">
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
    <c:if test="${empty dishes}">
        There is no dishes with name like '${dishesName}'
    </c:if>
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
