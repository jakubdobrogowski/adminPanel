<%@ page import="pl.sda.java9.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mamamacka</title>
</head>
<body>

<h1>Rejsetracja zakonczyla sie sukcesem</h1>

<%
    String welcome = "Witaj ";
    User user = (User) request.getAttribute("user");

    String message = user.getName();
%>

<h1><%=welcome + message %>
</h1>
<br>
<h2>Twój login to: <%=user.getLogin()%>
</h2>

<a href="/login.jsp">Zaloguj sie</a>
<a href="/showServerList">Server List</a>

</body>
</html>
