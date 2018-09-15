<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="pl.sda.java9.model.Server" %>
<%@ page import="pl.sda.java9.model.User" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Server List</title>
</head>
<body>

<%
    List<Server> allServers = (List<Server>) request.getAttribute("allServers");
    Map<Integer, User> allUsers = (Map<Integer, User>) request.getAttribute("allUsers");

    if (allServers == null || allServers.size() == 0) {
%>
<h1>Brak Serwerów, skontaktuj sie z mamamacka</h1>
<%
} else {

%>

<jsp:useBean id="ladeID" type="java.util.ArrayList"></jsp:useBean>
<jsp:useBean id="ladaMapa" type="java.util.Map"></jsp:useBean>
<jsp:useBean id="loggedInUser" type="java.lang.String"></jsp:useBean>
<jsp:useBean id="isAdmin" type="java.lang.Integer"></jsp:useBean>

<%
    ladeID.addAll(allServers);
    ladaMapa = allUsers;
    loggedInUser = (String) session.getAttribute("user");
    isAdmin = (Integer) session.getAttribute("isAdmin");
%>

<c:forEach var="server" items="${ladeID}">

    <h1>Server Name: ${server.name}</h1>
    <h1>Server Host: ${server.host}</h1>
    <h1>Server Port: ${server.port}</h1>
    <h1>Server Owner: ${ladaMapa.get(server.owner)}</h1>
    <h1>Server Status: ${server.status}</h1>

    <c:if test="${ladaMapa.get(server.owner).login.equals(loggedInUser) || isAdmin.equals(1)}">
        <a href="/editServer.jsp?id=${server.id}">edit</a>
    </c:if>

    <a href="/editServer.jsp">create</a>

</c:forEach>

<%
    }
%>
<form action="/logout" method="get">
    <input type="submit" value="Log Out">
</form>
</body>
</html>
