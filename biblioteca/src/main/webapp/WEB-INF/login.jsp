<%--
  Created by IntelliJ IDEA.
  User: a867759
  Date: 7/14/2024
  Time: 11:43 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h2>Login</h2>
<form action="login" method="post">
    Email: <input type="text" name="email" required><br>
    Senha: <input type="password" name="senha" required><br>
    <input type="submit" value="Login">
</form>
    <c:if test="${param.erro !=null}">
        <p style="color: red;">Email ou senha inválidos!</p>
    </c:if>
<a href="cadastrarUsuario.jsp">Cadastre-se</a>
</body>
</html>
