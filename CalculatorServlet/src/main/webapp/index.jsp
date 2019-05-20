<%--
  Created by IntelliJ IDEA.
  User: TseL
  Date: 11.05.2019
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Calculator</title>
    </head>
    <body>
    <h2>Calculator</h2>
        <form name="calculator" method="POST" action="calculator?action=submit">
            <p>Num1: <input name="num1" pattern="^[0-9]*[.]?[0-9]+$"></p>
            <p>Num2: <input name="num2" pattern="^[0-9]*[.]?[0-9]+$"></p>
            <p><input type="radio" value="1" name="operation" checked="checked">Addition (+)</p>
            <p><input type="radio" value="2" name="operation">Subtraction (-)</p>
            <p><input type="radio" value="3" name="operation">Multiplication (*)</p>
            <p><input type="radio" value="4" name="operation">Division (/)</p>
            <p><input type="submit" value="Get Result"></p>
            <p>Result: = ${ result }</p>
        </form>
    </body>
</html>
