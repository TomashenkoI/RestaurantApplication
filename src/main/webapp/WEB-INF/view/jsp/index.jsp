<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <title>Title</title>
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

<div class="indexCentralPanel">
    <h1>О ресторане</h1>

    <p>Почему «GRILLPUB»? Потому что «GRILLPUB» - это Семья. Большая, дружная семья. Именно семейные ценности объединяют всех
    посетителей ресторана, и эти традиционные ценности совершенно не имеют гангстерского подтекста.</p>

    <p>Дружба, преданность и уважение - вот те киты, на которых построена система ценностей сети ресторанов «GRILLPUB».</p>

    <p>Национальная сеть ресторанов «GRILLPUB» – это большая семья, неизменно следующая своим традициям и принципам. В каждом
    нашем ресторане гость может прочувствовать себя частью этой семьи, насладиться атмосферой уюта и доброжелательности
    и, конечно, отведать блюда, приготовленные нашими поварами. В наших ресторанах мы хотим показать гостям Италию,
    какой видим ее мы, - гостеприимную, теплую и солнечную.</p>

    <p>«GRILLPUB» – это современный демократичный ресторан итальянской и японской кухни с качественным обслуживанием и ценами,
    доступными для людей со средним уровнем достатка. Меню ресторана сочетает в себе две самые популярные в Украине
    кулинарные традиции – японскую и итальянскую, и в этом кроется один из секретов успеха. А фирменная метровая пицца,
    регулярные спецпредложения и акции уже стали визитными карточками «GRILLPUB».</p>

    <p>Особого внимания достоин и интерьер ресторана. В нем мы постарались воплотить концепцию солнечной Италии. Гости
    узнают «GRILLPUB» по легким светлым тонам, большим белоснежным люстрам, витиеватым фирменным узорам на стульях и мягким
    удобным диванчикам.<p/>

    Впрочем, лучше увидеть и попробовать ;)

    Добро пожаловать в рестораны «GRILLPUB»!
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