<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="path"/>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script src="../boot/js/jquery-3.4.1.min.js"></script>
    <script src="../echarts/echarts.min.js"></script>
    <script src="../echarts/china.js"></script>
    <script>
        $(function () {
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main'));
            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: '最近七天注册的用户'
                },
                tooltip: {},
                legend: {
                    data: ['用户注册']
                },
                xAxis: {
                    data: ["最近一天", "最近两天", "最近三天", "最近四天", "最近五天", "最近六天","最近七天"]
                },
                yAxis: {},
                series: [{
                    name: '用户注册',
                    type: 'bar',
                }]
            };
// 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
            $.ajax({
                url:"${path}/userDto/queryByDate",
                datatype:"json",
                success:function (data) {
                    myChart.setOption({
                        series:[{
                            data:data,
                        }]
                    })

                }
            })

            // 基于准备好的dom，初始化echarts实例
            var myChart1 = echarts.init(document.getElementById('goeasy'));
            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: '1-12月注册的用户'
                },
                tooltip: {},
                legend: {
                    data: ['用户注册']
                },
                xAxis: {
                    data: ["12月", "11月", "10月", "9月", "8月", "7月","6月","5月","4月","3月","2月","1月"]
                },
                yAxis: {},
                series: [{
                    name: '用户注册',
                    type: 'line',
                }]
            };
// 使用刚指定的配置项和数据显示图表。
            myChart1.setOption(option);
            $.ajax({
                url:"${path}/userDto/queryByMonth",
                datatype:"json",
                success:function (data) {
                    myChart1.setOption({
                        series:[{
                            data:data,
                        }]
                    })

                }
            })
        })
    </script>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 600px;height:400px;"></div>
<div id="goeasy" style="width: 600px;height:400px;"></div>
</body>
</html>