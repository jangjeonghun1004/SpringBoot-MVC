<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Reservation Form</title>
    <style>
        .error {
            color: #ff0000;
            font-weight: bold;
        }
        .error-input {
            border: 1px solid red;
            background-color: #ffe6e6;
        }
    </style>
</head>
<body>
<form:form method="post" modelAttribute="validatorModel">
    <table>
        <tr>
            <td>User Name</td>
            <td><form:input path="userName" cssClass="${userNameError ? 'error-input' : ''}"/></td>
        </tr>
        <tr>
            <td>Date</td>
            <td><form:input path="date"/></td>
            <td><form:errors path="date" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Number</td>
            <td><form:input path="number"/></td>
            <td><form:errors path="number" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Some Object Name</td>
            <td><form:input path="someObject.name"/></td>
            <td><form:errors path="someObject.name" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Some Object Phone</td>
            <td><form:input path="someObject.phone"/></td>
            <td><form:errors path="someObject.phone" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Select Object</td>
            <td><form:select path="selectObject.value" items="${selectObjects}" itemValue="value" itemLabel="text" /></td>
            <td><form:errors path="selectObject" cssClass="error"/></td>
        </tr>
        <tr>
            <td colspan="3"><input type="submit"/></td>
        </tr>
    </table>
    <br><br>
    <table>
        <tr>
            <td> Error Summary </td>
        </tr>
        <tr>
            <td><form:errors path="*" cssClass="error"/></td>
        </tr>
    </table>
</form:form>
</body>
</html>