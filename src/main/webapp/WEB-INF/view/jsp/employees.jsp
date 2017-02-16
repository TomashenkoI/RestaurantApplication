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
        <form action="/findEmployeeByName">
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
                            <%--<tr class="hover" onclick="location.href='/employee/employeeId=${employee.ID}'">--%>
                            <tr>
                                <td>${employee.firstName}</td>
                                <td>${employee.lastName}</td>
                                <td>${employee.employeePosition}</td>
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
