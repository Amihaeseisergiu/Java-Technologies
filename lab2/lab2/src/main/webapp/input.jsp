<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Input JSP</title>
    </head>
    <body>
        <form action="InputController">
            <div style="padding: 10px;">
                <label for="category" style="padding-right: 7px;">Category:</label>
                <select name="category" id="category" style="padding: 4px;">
                    <c:choose>
                        <c:when test="${cookie.categoryCookie == null}">
                            <option selected value></option>
                        </c:when>    
                        <c:otherwise>
                            <option selected value>${cookie.categoryCookie.getValue()}</option>
                        </c:otherwise>
                    </c:choose>
                    <c:forEach items="${categories}" var="category">
                      <option value="${category}">${category}</option>
                    </c:forEach>
                </select>
            </div>
            <div style="padding: 10px;">
                <label for="word" style="padding-right: 31px;">Word:</label>
                <input type="text" name="word" id="word" />
            </div>
            <div style="padding: 10px;">
                <label for="definition">Definition:</label>
                <input type="textarea" name="definition" id="definition" />
            </div>
            <div>
                <img src="CaptchaImage" />
                <br>
                <label for="captcha">Number of circles:</label>
                <input type="number" name="captcha" id="captcha" />
            </div>
            <div style="padding: 10px;">
                <input type="submit" value="Submit">
            </div>
        </form>
    </body>
</html>
