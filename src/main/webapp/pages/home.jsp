<%--
  Created by IntelliJ IDEA.
  User: itsmymac
  Date: 16.06.21
  Time: 20:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div>ADD USER</div>
<form action="/user/save" method="post">
    <input name="username" placeholder="username">
    </br>
    <input name="password" placeholder="password">
    </br>
    <input name="name" placeholder="name">
    </br>
    <input name="street" placeholder="street">
    </br>
    <input name="home" placeholder="home">
    </br>
    <button> save</button>
</form>

</br>
</br>

<div>find by username</div>
<form action="/user/findByUserName" method="post">
    <input name="username" placeholder="username">
    </br>
    <div>${byUserName}</div>
    <button> find</button>
</form>

</br>
</br>

<div>update name</div>
<form action="/user/updateName" method="post">
    <input name="name" placeholder="username">
    </br>
    <input name="id" placeholder="id">
    </br>
    <button> update</button>
</form>

</br>
</br>

<div>delete</div>
<form action="/user/delete" method="post">
    <input name="id" placeholder="id">
    </br>
    <button> delete</button>
</form>

</br>
</br>

<div>find all</div>
<form action="/user/findAll" method="post">
    <button> find</button>
</form>
<ul class="list-group">
    <c:forEach items="${users}" var="user">
        <li>
            <div> id: ${user.id}</div>
            <div> username: ${user.username}</div>
            <div> password: ${user.password}</div>
            <div> name: ${user.name}</div>
            <div> address: s.${user.address.street} h.${user.address.home}</div>
        </li>
    </c:forEach>
</ul>


</body>
</html>
