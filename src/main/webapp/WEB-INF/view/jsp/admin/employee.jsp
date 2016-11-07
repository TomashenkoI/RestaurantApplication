<%@ page import="java.io.File" %>
<%@ page import="java.net.URL" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <c:if test="${alertNeed == true}">
        <script>
            alert("The employee can't be deleted. There is orders on him!")
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

<div class="employeeCentralPanel">

    <button class="selectedPositionButton" onclick="location.href='/admin/employees';">< Back to list</button>;

    <div class="employeePhoto">
        <img width="250" height="300" src="${employee.photoURL}"/>
    </div>

    <table class="table">

        <tr>
            <th>First Name</th>
            <td>${employee.firstName}</td>
        </tr>
        <tr>
            <th>Last Name</th>
            <td>${employee.lastName}</td>
        </tr>
        <tr>
            <th>Position</th>
            <td>${employee.position}</td>
        </tr>
        <tr>
            <th>Date of birth</th>
            <td>${employee.dateOfBirth}</td>
        </tr>
        <tr>
            <th>Phone Number</th>
            <td>${employee.phoneNumber}</td>
        </tr>
        <tr>
            <th>Salary, uah</th>
            <td>${employee.salary}</td>
        </tr>
    </table>

    <div class="employeeButtons">
        <button onclick="location.href='/admin/update_EmployeeId=${employee.ID}';">Update Info</button>
        <button onclick="location.href='/admin/delete_EmployeeId=${employee.ID}';">Delete employee</button>
    </div>

</div>
</body>
</html>
