<%@page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="path"/>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script src="${path}/boot/js/jquery-3.4.1.min.js"></script>
    <script>
        $.ajax({
            url:"${path}/admin/queryAll",
           /* type:"POST",*/
            /*data:"",*/
            success:function (result) {
                $.each(result,function (index, val) {
                    $("#myDiv").append(val.id+"--"+val.username+"--"+val.password);
                })
            },
            datatype:"json"
        })
    </script>
</head>
<body>
    <div id="myDiv"></div>
    <form action="${path}/admin/poiOut">
        <input type="submit" value="poi导出">
    </form>
</body>
</html>