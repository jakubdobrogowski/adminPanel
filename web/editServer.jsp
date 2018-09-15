<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Server</title>
</head>
<body>
<h1>C H C E U M R Z E C</h1>


<%
    Integer id = (Integer) request.getAttribute("id");

    if (id == null) {
%>
<h2>Utwórz nowy serwer</h2>
<%

} else {

%>
<h2>Edytuj serwer</h2>
<%
    }
%>


<form action="/editServer" method="post">

    <input type="hidden" name="id">
    Name: <input type="text" name="name" placeholder="name">
    <br>
    Host: <input type="text" name="host" placeholder="host">
    <br>
    Port: <input type="text" name="user" placeholder="port">
    <br>
    Owner: <input type="text" name="owner" placeholder="owner">
    <br>
    Status: <input type="text" name="status" placeholder="status">
    <br>

    <input type="submit" value="submit">

</form>


</body>
</html>
