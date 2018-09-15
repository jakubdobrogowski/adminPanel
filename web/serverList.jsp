<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="pl.sda.java9.model.Server" %>
<%@ page import="pl.sda.java9.model.User" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Server List</title>
</head>
<body>

<%
    List<Server> allServers = (List<Server>) request.getAttribute("allServers");
    HashMap<Integer, User> allUsers = (HashMap<Integer, User>) request.getAttribute("allUsers");

    if (allServers == null || allServers.size() == 0) {
%>
<h1>Brak Serwerów, skontaktuj sie z mamamacka</h1>
<%
} else {

%>

<jsp:useBean id="ladeID" class="java.util.ArrayList"></jsp:useBean>
<jsp:useBean id="ladaMapa" class="java.util.HashMap"></jsp:useBean>
<jsp:useBean id="loggedInUser" class="java.lang.String"></jsp:useBean>
<jsp:useBean id="isAdmin" class="java.lang.String"></jsp:useBean>

<%
    ladeID.addAll(allServers);
    ladaMapa.putAll(allUsers); // tu zmiana
    loggedInUser = (String) session.getAttribute("user");
    isAdmin = (String) session.getAttribute("isAdmin");
%>

<c:forEach var="server" items="${ladeID}">

    <h6>Server Name: ${server.name}</h6>
    <h6>Server Host: ${server.host}</h6>
    <h6>Server Port: ${server.port}</h6>
    <h6>Server Owner: ${ladaMapa.get(server.owner)}</h6>
    <h6>Server Status: ${server.status}</h6>

    <%--<c:if test="${ladaMapa.get(server.owner).login.equals(loggedInUser) || isAdmin.equals('1')}">--%>
        <%--<a href="/editServer?id=${server.id}">edit</a>--%>
    <%--</c:if>--%>

    <%

        if(ladaMapa.get())


    %>

</c:forEach>

<%
    }
%>

<a href="/editServer">create</a>


<form action="/logout" method="get">
    <input type="submit" value="Log Out">
</form>
</body>
</html>
