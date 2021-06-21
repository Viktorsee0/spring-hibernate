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
<div>ADD USER</div>
<form action="/user/addTag" method="post">
    <input name="username" placeholder="username">
    </br>
    <input name="tagName" placeholder="tagName">
    </br>
    <button> save</button>
</form>

</br>


<div>find by username</div>
<form action="/user/findByUserName" method="post">
    <input name="username" placeholder="username">
    </br>
    <div>${byUserName}</div>
    <button> find</button>
</form>

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

</br>

<div>find all by name</div>
<form action="/user/findAllByName" method="post">
    <input name="name" placeholder="name">
    </br>
    <button> find</button>
</form>
<ul class="list-group">
    <c:forEach items="${usersByName}" var="usersByName">
        <li>
            <div> id: ${usersByName.id}</div>
            <div> username: ${usersByName.username}</div>
            <div> password: ${usersByName.password}</div>
            <div> name: ${usersByName.name}</div>
            <div> address: s.${usersByName.address.street} h.${usersByName.address.home}</div>
        </li>
    </c:forEach>
</ul>

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

<div>UPDATE USER</div>
<form action="/user/updateUser" method="post">
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
    <button>save</button>
</form>

</br>

<div>delete</div>
<form action="/user/delete" method="post">
    <input name="id" placeholder="id">
    </br>
    <button> delete</button>
</form>

</br>

<div>delete</div>
<form action="/user/deleteByName" method="post">
    <input name="username" placeholder="username">
    </br>
    <button> delete</button>
</form>

</body>
</html>
