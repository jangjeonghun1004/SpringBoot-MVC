<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>JSTL Example</title>
</head>
<body>
    <h1>Handling Time: ${handlingTime} ms</h1>
    <p>Handling Time: <c:out value="${handlingTime}"/></p>
</body>
</html>
