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

<div class="ordersCentralPanel">

    <div class="ordersFindButtons">

        <form action="/admin/findOrdersByDate" , method="get">
            <input autocomplete="off" type="date" name="date" placeholder="dd.mm.yyyy">
            <button>Search</button>
        </form>
        <form action="/admin/findOrdersByTableNumber" method="get">
            <input autocomplete="off" type="number" name="tableNumber" placeholder="find by table number">
            <button>Search</button>
        </form>
        <form action="/admin/ordersByEmployee" method="get">
            <select name="selectedWaiterId">
                <c:forEach items="${waiters}" var="waiter">
                    <c:set var="waiterId" value="${waiter.ID}"/>
                    <option value="${waiterId}">${waiter.firstName} ${waiter.lastName}</option>
                </c:forEach>
            </select>
            <button>Search</button>
        </form>

    </div>

    <table class="mainTable">
        <tr>
            <td>
                <table class="ordersTable">
                    <tr class="title">
                        <th>Id</th>
                        <th>Waiter</th>
                        <th>Table number</th>
                        <th>Date</th>
                        <th>Status</th>
                    </tr>
                </table>
            </td>
        </tr>

        <tr>
            <td>
                <div class="wrap-2">

                    <table>
                        <c:if test="${not empty orders}">
                            <c:forEach items="${orders}" var="order">
                                <tr class="hover" onclick="location.href='/admin/orders/orderId=${order.ID}'">
                                    <td>${order.ID}</td>
                                    <td>${order.employee.firstName} ${order.employee.lastName}</td>
                                    <td>${order.tableNumber}</td>
                                    <td>${order.date}</td>
                                    <td>
                                        <c:if test="${order.access == true}">active</c:if>
                                        <c:if test="${order.access == false}">closed</c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        <c:if test="${empty orders}">
                            <span>There is no orders!<span>
                        </c:if>

                    </table>
                </div>
            </td>
        </tr>


    </table>
</div>


<div class="ordersButtons">

    <button onclick="location.href='/admin/newOrder';">New order</button>

</div>

</body>
</html>
