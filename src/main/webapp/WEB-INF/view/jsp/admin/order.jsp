<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>

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

<div class="orderButtonBack">
    <button onclick="location.href='/admin/orders';">< Back</button>
</div>
<div class="orderTable">
    <table class="table">

        <tr class="title">
            <th>ID</th>
            <th>Waiter</th>
            <th>Table number</th>
            <th>Order date</th>
        </tr>
        <tr>
            <td>${order.ID}</td>
            <td>${order.employee.firstName} ${order.employee.lastName}</td>
            <td>${order.tableNumber}</td>
            <td>${order.date}</td>
        </tr>

    </table>
</div>

<div>
        <table class="mainTable">
            <tr>
                <td>
                    <table>
                        <tr class="title">
                            <th>Name</th>
                            <th>Price</th>
                            <th>Weight</th>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="listOfDishesInOrder">
                        <table>
                            <c:forEach items="${order.dishes}" var="dish">
                                <c:if test="${order.access == true}">
                                    <tr>
                                </c:if>
                                <c:if test="${order.access == false}">
                                    <tr>
                                </c:if>
                                <td>${dish.name}</td>
                                <td>${dish.price}</td>
                                <td>${dish.weight}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </td>
            </tr>
        </table>
</div>
<div class="selectedOrderButtons">
    <c:if test="${order.access == true}">
        <c:set var="orderID" value="${order.ID}"/>
        <button onclick="window.location.href='/admin/update_orderId=${orderID}';">Update</button>
        <button onclick="location.href='/admin/delete_orderId=${orderID}';">Delete</button>
        <button onclick="location.href='/admin/close_orderId=${orderID}';">Close the order</button>
    </c:if>

</div>
</body>
</html>
