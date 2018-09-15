<%@ page import="pl.sda.java9.model.Server" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="pl.sda.java9.model.User" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Server</title>
</head>
<body>
<h1>C H C E U M R Z E C</h1>


<%
    Server server = (Server) request.getAttribute("server");
    Map<Integer, User> allUsers = (Map<Integer, User>) request.getAttribute("allUsers");
    String isAdmin = (String) session.getAttribute("isAdmin");
    String id = (String) session.getAttribute("id");

    if (server == null) {
%>
<h2>Utwórz nowy serwer</h2>
<%

} else {

%>
<h2>Edytuj serwer</h2>
<%
    }
%>

<jsp:useBean id="admin" class="java.lang.String"></jsp:useBean>
<jsp:useBean id="idUser" class="java.lang.String"></jsp:useBean>


<%
    admin = isAdmin;
    idUser = id;

    Boolean adminProp = admin.equals("1");
%>

<form action="/editServer" method="post">

    <input type="hidden" name="id">
    Name: <input type="text" name="name" placeholder="name">
    <br>
    Host: <input type="text" name="host" placeholder="host">
    <br>
    Port: <input type="text" name="user" placeholder="port">
    <br>
    Status: <input type="text" name="status" placeholder="status">
    <br>


    <%--<c:choose>--%>
    <%--<c:when test="${adminProp}">--%>

    <%--Owner: <input type="text" name="owner" placeholder="owner">--%>
    <%--<br>--%>

    <%--</c:when>--%>
    <%--<c:otherwise>--%>
    <%--<input type="hidden" name="owner" value="${idUser}">--%>
    <%--</c:otherwise>--%>
    <%--</c:choose>--%>

    <%
        if (adminProp) {
    %>
    
    Owner:
    <select name="owner">
        <%
            for (Integer userID : allUsers.keySet()) {
                User user = allUsers.get(userID);
                String nameAndSurname = user.getName() + " " + user.getSurname();
        %>
        <option value="<%=userID%>"><%=nameAndSurname%></option>
        <%
            }
        %>
    </select>

    <br>
    <%
    } else {
    %>
    <input type="hidden" name="owner" value="${idUser}">
    <%
        }
    %>

    <input type="submit" value="submit">
</form>
</body>
</html>
