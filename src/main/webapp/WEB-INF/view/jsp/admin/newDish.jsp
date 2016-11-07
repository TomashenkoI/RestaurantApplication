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
<div class="wrap-0">
    <c:if test="${doesItAlreadyExist == false}">
        <form action="/admin/dishes" method="post">
            <table>
                <tr>
                    <td>Name</td>
                    <td><input autocomplete="off" type="text" name="dishName" required></td>
                </tr>
                <tr>
                    <td>Category</td>
                    <td>
                        <select name="dishCategory">
                            <option>Main course</option>
                            <option>Garnish</option>
                            <option>Soup</option>
                            <option>Salad</option>
                            <option>Dessert</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Price</td>
                    <td><input autocomplete="off" type="number" name="dishPrice" required></td>
                </tr>
                <tr>
                    <td>Weight</td>
                    <td><input autocomplete="off" type="number" name="dishWeight" required></td>
                </tr>
            </table>

            <div class="ingredients">
                <select class="listOfIngredients" multiple size="8" name="selectedIngredients">
                    <c:forEach items="${ingredients}" var="ingredientName">
                        <option value="${ingredientName.ID}">${ingredientName.name}</option>
                    </c:forEach>
                </select>
            </div>

            <button>Submit</button>
        </form>
    </c:if>

    <c:if test="${doesItAlreadyExist == true}">
        <form action="/admin/updated_DishId=${dish.ID}" method="post">
            <table>
                <tr>
                    <td>Name</td>
                    <td><input autocomplete="off" type="text" value="${dish.name}" name="dishName"></td>
                </tr>
                <tr>
                    <td>Category</td>
                    <td>
                        <select name="dishCategory">
                            <option>Main course</option>
                            <option>Garnish</option>
                            <option>Soup</option>
                            <option>Salad</option>
                            <option>Dessert</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Price</td>
                    <td><input autocomplete="off" type="number" value="${dish.price}" name="dishPrice"></td>
                </tr>
                <tr>
                    <td>Weight</td>
                    <td><input autocomplete="off" type="number" value="${dish.weight}" name="dishWeight"></td>
                </tr>
            </table>
            <p/>
            <select class="listOfIngredients" multiple size="8" name="selectedIngredients">
                <c:forEach items="${ingredients}" var="ingredientName">
                    <option>${ingredientName.name}</option>
                </c:forEach>
            </select>
            <p/>
            <button>Submit</button>
        </form>
    </c:if>
</div>
</body>
</html>
