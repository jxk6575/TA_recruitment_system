<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
</head>
<body>
<h1>105组TA招聘系统</h1>
<h2>Hello, <c:out value="${user.name}" />!</h2>
<p>Email: <c:out value="${user.email}" /></p>

<c:if test="${not empty user.name}">
    <p>欢迎你，<c:out value="${user.name}" />！</p>
</c:if>

<form method="post" action="${pageContext.request.contextPath}/hello">
    <input type="text" name="name" placeholder="输入名字" />
    <button type="submit">提交</button>
</form>
</body>
</html>
