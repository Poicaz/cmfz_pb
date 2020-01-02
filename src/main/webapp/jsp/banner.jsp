<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="path"/>
<style>
    .aa{
        margin-top: -20px;
    }
</style>
<script>
    $(function () {
        $("#list").jqGrid({
            url:"${path}/banner/queryPage",//获取数据的请求路径
            editurl:"${path}/banner/edit",//增  删  改 查
            styleUI:"Bootstrap",//Bootstrap样式
            datatype:"json",//返回数据json形式
            autowidth:true,//自适应父容器
            viewrecords:true,//定义要设置的总条数
            toolbar:[true,"top"],//表格的工具栏
            hidegrid:true,//用来控制在使用标题情况下是否显示折叠按钮
            pager:"#pager",//分页工具栏
            rowNum:2,
            rowList:[1,2,3,4,5,6,7,8],//自定义每页显示条数
            //caption:"轮播图表",//表名
            multiselect:true,//复选框  批量删除  数组接收
            colNames:["编号","标题","创建时间","状态","图片"],//表头
            colModel:[
                {name:"id",align:"center"},
                {name:"title",editable:true,align:"center",edittype:"title"},
                {name:"create_date",editable:true,align:"center",edittype:"date"},
                {name:"status",editable:true,align:"center",edittype:"select",editoptions:{
                        value:'显示:显示;不显示:不显示'
                    }},
                {name:"img",editable:true,align:"center",edittype:"file",
                    formatter:function (cellvalue,options,rowObject) {
                        return "<img style='width:50%;height: 50px;'src='${path}/upload/img/"+cellvalue+"'/>"//返回图片的路径(路径是上传的文件夹的路径)
                    }
                }
            ]
        }).jqGrid("navGrid","#pager",{search:false,addtext:"添加",edittext:"修改",deltext:"删除"},{
                /*
                * 修改
                * */
                //修改成功后点击提交 关闭修改模态框
                closeAfterEdit:true,
                afterSubmit:function (response) {//提交后响应
                    var msg = response.responseJSON.msg;//通过json的形式响应信息
                    var id = response.responseJSON.id;//通过json的形式响应id
                    console.log(id);
                    if(id == "" || id == ''){
                        return response;
                    }else{
                        $.ajaxFileUpload({//通过ajax文件上传
                            url:"${path}/banner/upload",//上传处理程序位置
                            fileElementId:"img",//需要上传文件域的id
                            data:{id:id},//自定义参数
                            type:"post",//提交数据的形式
                            success:function () {
                                $("#list").trigger("reloadGrid");//刷新表格
                                //添加成功展示添加成功
                                $("#msDiv1").html(msg).show();
                                //设置时间添加成功 展示多少时间就隐藏
                                setTimeout(function () {
                                    $("#msDiv1").hide();
                                },3000)
                            }
                        })
                    return response;//返回响应
                }
            }
        },
            {
                /*
                * 添加
                * */
                closeAfterAdd:true,//添加成功后点击提交 关闭添加模态框
                afterSubmit:function (response) {//提交之后响应
                    var msg = response.responseJSON.msg;//通过json的形式响应消息
                    var id = response.responseJSON.id;//通过json的形式响应id
                    $.ajaxFileUpload({
                        url:"${path}/banner/upload",//上传处理程序的位置
                        fileElementId:"img",//需要上传文件域的id
                        data:{id:id},//自定义参数
                        type:"post",//提交数据的形式
                        success:function () {
                            $("#list").trigger("reloadGrid");//刷新表格
                            //添加成功展示添加成功
                            $("#msDiv").html(msg).show();
                            setTimeout(function () {
                                $("#msDiv").hide();
                            },3000)
                        }
                    })
                    return response;
                }
            },{
                /*
                * 删除
                * */
                //完成之后响应
                afterComplete:function (response) {
                    $("#list").trigger("reloadGrid");//刷新表格
                    //添加成功展示添加成功
                    $("#msDiv2").show();
                    setTimeout(function () {
                        $("#msDiv2").hide();
                    },3000)
                    return response;
                },
            })
        $("#t_list").html("<button class='btn btn-primary' onclick=\"save(list)\">保存</button>")
    })
    function save() {
        location.href="${path}/banner/easyPoi";
    }
</script>
<%--页头--%>
<div class="page-header aa">
    <h3>轮播图管理</h3>
</div>
<%--两端对齐标签页--%>
<ul class="nav nav-tabs">
    <li role="presentation" class="active">
        <a  class="dropdown-toggle" data-toggle="dropdown" href="#">
            轮播图信息
        </a>
    </li>
</ul>
<%--面板--%>
<div class="panel panel-default">
    <div class="panel-body">
        <%--表--%>
        <table id="list"></table>
        <div style="height: 50px" id="pager"></div>
        <div class="alert alert-success" style="display: none" id="msDiv">
            添加成功
        </div>
        <div class="alert alert-success" style="display: none" id="msDiv1">
            修改成功
        </div>
        <div class="alert alert-success" style="display: none" id="msDiv2">
            删除成功
        </div>
    </div>
</div>

