<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="../boot/js/jquery-3.4.1.min.js"></script>
    <!-- 引入 echarts.js -->
    <script src="../echarts/echarts.min.js"></script>
    <script>
        $(function () {
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main'));
            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: 'ECharts 入门示例'
                },
                tooltip: {},
                legend: {
                    data:['销量']
                },
                xAxis: {
                    data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
                },
                yAxis: {},
                series: [{
                    name: '销量',
                    type: 'bar',
                }]
            };
// 使用刚指定的配置项和数据显示图表。
           myChart.setOption(option);
           $.ajax({
               url:"${path}/ECharts/getECharts",
               datatype:"json",
               success:function (data) {
                   console.log(data);
                myChart.setOption({
                    series:[{
                        data:data,
                    }]
                })
               }
           })
        })
    </script>
    <title>Document</title>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 600px;height:400px;"></div>
</body>
</html>