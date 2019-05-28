<%--
  Created by IntelliJ IDEA.
  User: TseL
  Date: 26.05.2019
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ввод данных</title>
    <form method="POST" name="inputForm">
        <b>Текст сообщения: </b>
        <input type="text" name="string"/><br><br>
        <b>Число: </b>
        <input type="number" name="number" pattern=".*" value="0"/></br><br>
        <button type="button" onclick=addNumToUrl();>Ввод</button>
    </form>
    <script>
        function addNumToUrl() {
            var num = document.getElementsByName("number")[0].value;
            document.getElementsByName("inputForm")[0].action = "/message/" + num;
            document.getElementsByName("inputForm")[0].submit();
        }
    </script>
</head>
<body>

</body>
</html>
