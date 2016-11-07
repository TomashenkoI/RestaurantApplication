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
        <form action="/admin/findEmployeeByName">
            <input autocomplete="off" type="text" placeholder="find by name" name="employeeName">
            <button>Search</button>
        </form>
    </div>

    <c:if test="${not empty employees}">

        <table class="mainTable">
            <tr>
                <td>
                    <table>
                        <tr class="title">
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Position</th>
                            <th></th>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="wrap-2">
                        <table>
                            <c:forEach items="${employees}" var="employee">
                                <tr class="hover" onclick="location.href='/admin/employee/employeeId=${employee.ID}'">
                                    <td>${employee.firstName}</td>
                                    <td>${employee.lastName}</td>
                                    <td>${employee.position}</td>
                                    <td><img width="50" height="50"
                                             src="${employee.photoURL}"/></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </td>
            </tr>
        </table>

    </c:if>
    <c:if test="${empty employees}">
        There is no employees with name like '${employeeName}'
    </c:if>

    <div class="buttonCreate">
    <button onclick="location.href='/admin/newEmployee'">Create new employee</button>
    </div>

</div>

</body>
</html>
