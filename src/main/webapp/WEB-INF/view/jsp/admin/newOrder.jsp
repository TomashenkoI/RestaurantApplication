<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <c:if test="${alertNeed == true}">
        <script>
            alert("Not enough ingredients");
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

<div class="newOrderCentralPanel">
    <c:if test="${doesItAlreadyExist == false}">
        <form action="/admin/orders" method="post">
            <table>
                <tr>
                    <td>Waiter</td>
                    <td><select name="waiterId">
                        <c:forEach items="${waiters}" var="waiter">
                            <option value="${waiter.ID}">${waiter.firstName} ${waiter.lastName}</option>
                        </c:forEach>
                    </select>
                    </td>
                </tr>
                <tr>
                    <td>Table number</td>
                    <td><input autocomplete="off" type="number" name="tableNumber" required></td>
                </tr>
            </table>
            <p/>
            <select multiple size="8" name="selectedDishes">
                <c:forEach items="${dishes}" var="dish">
                    <option value=${dish.ID}>${dish.name}</option>
                </c:forEach>
            </select>
            <p/>
            <button>Submit</button>
        </form>
    </c:if>

    <c:if test="${doesItAlreadyExist == true}">

        <button onclick="location.href='orders/orderId=${order.ID}';">< Back</button>

        <form action="/admin/update_orderId=${order.ID}" method="post">
            <table>
                <tr>
                    <td>Waiter</td>
                    <td><select name="waiterId">
                        <c:forEach items="${waiters}" var="waiter">
                            <option value="${waiter.ID}">${waiter.firstName} ${waiter.lastName}</option>
                        </c:forEach>
                    </select>
                    </td>
                </tr>
                <tr>
                    <td>Table number</td>
                    <td><input autocomplete="off" type="number" value="${order.tableNumber}" name="tableNumber" required></td>
                </tr>
            </table>
            <p/>
            <table class="listOfDishes">
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
                        <div class="listOfDishesWhileEditingOrderInfo">
                            <table>
                                <c:forEach items="${order.dishes}" var="dish">
                                    <tr class="hover" onclick="location.href='/admin/orders/orderId=${order.ID}/deleteDishId=${dish.ID}'">
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
            <div class="updateOrderButtons">
                <button>Submit</button>
            </div>
        </form>
        <div class="updateOrderButtons1">
            <button onclick="window.location.href='/admin/addDish_OrderId=${order.ID}';">add the dish</button>
        </div>
    </c:if>
</div>
</body>
</html>
