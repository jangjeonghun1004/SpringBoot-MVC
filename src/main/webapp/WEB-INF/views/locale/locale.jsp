<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>JSTL Example</title>
</head>
<body>
    <a href="?language=en">English</a>
    <a href="?language=ko">한국어</a>
    <br>

    <p>Language = ${pageContext.response.locale}</p>
    <p><fmt:message key="locale.hello"/></p>
    <p><spring:message code="locale.hello" text="hello" /></p>
    <p><spring:message code="locale.hello.uy" text="key 값이 존재하지 않을때 출력되는 값." /></p>
</body>
</html>
