<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Result JSP</title>
    </head>
    <body>
        <table style="width: 100%;">
            <tr>
                <th>Category</th>
                <th>Word</th>
                <th>Description</th>
            </tr>
            <c:forEach items="${records}" var="record">
                <tr>
                    <td>${record.getCategory()}</td>
                    <td>${record.getWord()}</td>
                    <td>${record.getDefinition()}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
