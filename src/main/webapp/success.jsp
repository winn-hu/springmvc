<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Demo</title>
</head>
<body>
${username}注册成功！
入职时间：<fmt:formatDate value="${hiredate}" type="date" dateStyle="full"/>
</body>
</html>
