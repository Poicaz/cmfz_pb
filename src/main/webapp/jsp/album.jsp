<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="path"/>
<style>
      .aaa{
          margin-top: -20px;
      }
      .ui-jqgrid .ui-userdata {
          padding: 4px 80px;
          overflow: hidden;
          min-height: 32px;
      }
</style>
<script>
    $(function () {
        $("#albumList").jqGrid({
            url:"${path}/album/queryPage",//获取数据的请求路径
            styleUI:"Bootstrap",//Bootstrap样式
            datatype:"json",//返回json形式
            autowidth:true,//自适应父容器
            viewrecords:true,//定义要设置的总条数
            toolbar:[true,"top"],//表格的工具栏
            hidegrid:true,//是否显示折叠按钮
            height:200,
            pager:"#albumPager",//分页工具栏
            rowNum:2,
            rowList:[1,2,3,4,5,6,7,8],//自定义每页显示条数
            caption:"专辑",
            //multiselect:true,//复选框
            colNames:["编号","标题","评分","作者","播音","数量","简介","创建时间","状态","图片"],
            colModel:[
                {name:"id",align:"center"},
                {name:"title",align:"center",editable:true,edittype:"title"},
                {name:"score",align:"center",editable:true},
                {name:"author",align:"center",editable:true,edittype:"text"},
                {name:"broadCaster",align:"center",editable:true,edittype:"text"},
                {name:"count",align:"center",editable:true,edittype:"text"},
                {name:"brief",align:"center",editable:true,edittype:"textarea"},
                {name:"create_date",align:"center",editable:true,edittype:"date"},
                {name:"status",align:"center",editable:true,edittype:"select",editoptions:{
                    value:"冻结:冻结;正常:正常"
                    }},
                {name:"img",align:"center",editable:true,edittype:"file",
                    formatter:function (cellvalue) {
                        return "<img style='width:60%;height: 40px' src='${path}/upload/img/"+cellvalue+"'/>"//返回图片的路径(路径是上传的文件夹的路径)
                    }
                }
            ],
            subGrid:true,//开启子表
            subGridRowExpanded:function (subGridId,album_id) {
                //添加子表格的方法
                addSubGrid(subGridId,album_id);
            }
        }).jqGrid("navGrid","#albumPager",{search:false})
    })

    //添加子表格
    function addSubGrid(subGridId,album_id) {
    //    动态table id
        var subGridTableId = subGridId + "table";
    //    动态div id
        var subGridDivId = subGridId + "div";
    //    动态添加子表格
        $("#"+subGridId).html("<table id='"+subGridTableId+"'></table>"+
                                "<div id='"+subGridDivId+"' style='height: 50px'></div>"
                              )
        $("#"+subGridTableId).jqGrid({
            url:"${path}/chapter/queryByPage?album_id="+album_id,//获取数据的请求路径
            editurl:"${path}/chapter/chapterEdit?album_id="+album_id,
            styleUI:"Bootstrap",//Bootstrap样式
            datatype:"json",//返回json形式
            autowidth:true,//自适应父容器
            viewrecords:true,//定义要设置的总条数
            toolbar:[true,"top"],//表格的工具栏
            hidegrid:true,//是否显示折叠按钮
            multiselect:true,//复选框
            pager:"#"+subGridDivId,//分页工具栏
            height:100,
            rowNum:2,//每页展示数据
            rowList:[1,2,3,4,5,6,7,8],//自定义每页显示条数
            caption:"章节",
            colNames:["编号","标题","专辑id","大小","时长","音频路径","状态"],
            colModel:[
                {name:"id",align:"center"},
                {name:"title",align:"center",editable:true,edittype:"title"},
                {name:"album_id",align:"center"},
                {name:"size",align:"center"},
                {name:"duration",align:"center"},//时长
                {name:"src",align:"center",editable:true,edittype:"file"},
                {name:"status",align:"center",editable:true,edittype:"select",editoptions:{
                    value:"冻结:冻结;正常:正常"
                    }}
            ]
        }).jqGrid("navGrid","#"+subGridDivId,{search:false,addtext:"添加",edittext:"修改",deltext:"删除"},
            {
            /*
            * 修改
            * */
                closeAfterEdit:true,//添加成功后点击提交  关闭添加模态框
                afterSubmit:function (response) {
                    var msg = response.responseJSON.msg;//通过json的形式响应消息
                    var id = response.responseJSON.id;
                    $.ajaxFileUpload({
                        url:"${path}/chapter/chapterUpload",
                        fileElementId:"src",//需要上传文件遇到id
                        data:{id:id},//自定义参数
                        type:"post",
                        success:function () {
                            $("#"+subGridTableId).trigger("reloadGrid");//刷新表格
                            //添加成功展示添加成功
                            $("#msDiv").html(msg).show();
                            setTimeout(function () {
                                $("#msDiv").hide();
                            },3000)
                        }
                    })
                    return response;
                }
            },
            {
                /*
                * 添加
                * */
                closeAfterAdd:true,//添加成功后点击提交  关闭添加模态框
                afterSubmit:function (response) {
                    var id = response.responseJSON.id;
                    var msg = response.responseJSON.msg;//通过json的形式响应消息
                    $.ajaxFileUpload({
                        url:"${path}/chapter/chapterUpload",
                        fileElementId:"src",//需要上传文件遇到id
                        data:{id:id},//自定义参数
                        type:"post",
                        success:function () {
                            $("#"+subGridTableId).trigger("reloadGrid");//刷新表格
                            //    添加成功展示添加成功
                            $("#msDiv").html(msg).show();
                            setTimeout(function () {
                                $("#msDiv").hide();
                            },3000)
                        }
                    })
                    return response;
                }
            },
            /*$("#msgDiv").html(msg).show();*/
            {
                /*
                * 删除
                * */
                afterComplete:function (response) {
                    $("#"+subGridTableId).trigger("reloadGrid");//刷新表格
                    //添加成功展示添加成功
                    $("#msDiv").show();
                    setTimeout(function () {
                        $("#msDiv").hide();
                    },3000)
                    return response;
                },
            })
    //    添加按钮
        $("#t_"+subGridTableId).html("<button class='btn btn-primary' onclick=\"play('"+subGridTableId+"')\">播放<span class='glyphicon glyphicon-play-circle'></span></button>"+
                                      "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
                                      "<button class='btn btn-primary' onclick=\"download('"+subGridTableId+"')\">下载<span class='glyphicon glyphicon-download'></span></button>"
                                      )
    }
//    播放
    function play(subGridTableId) {
        //判断 用户是否选中一行 未选中-》null 选中--》被选中行的id
        var gr = $("#"+subGridTableId).jqGrid('getGridParam','selrow');
        if(gr==null){
            alert("请选中要播放的音频");
        }else{
        //  1,  请求后台
        //  2，jqgrid  提供方法  根据id拿到对应的值
            var data = $("#"+subGridTableId).jqGrid('getRowData',gr);//返回具体某一列对象
            var path = "${path}/audio/"+data.src;
            //创建js封装的Audio对象  play()：开始播放音频
            new Audio(path).play();
        }
    }
//    下载
    function download(subGridTableId) {
    //    判断用户是否被选中一行  未选中-》null 选中--》被选中行的id
        var jr = $("#"+subGridTableId).jqGrid('getGridParam','selrow');
        if(jr==null){
            alert("请选中要下载的音频");
        }else{
        //    jqgrid  提供方法  根据id拿到对应的值
            var data = $("#"+subGridTableId).jqGrid("getRowData",jr);
            var src = data.src;
            //下载音频方法的路径
            location.href="${path}/chapter/chapterDownload?src="+src;
        }
    }
</script>
<%--页头--%>
<div class="page-header aaa">
    <h3>专辑列表管理</h3>
</div>
<%--两端对齐标签页--%>
<ul class="nav nav-tabs">
    <li role="presentation" class="active">
        <a  class="dropdown-toggle" data-toggle="dropdown" href="#">
            专辑信息
        </a>
    </li>
</ul>
<%--面板--%>
<div class="panel panel-default">
    <div class="panel-body">
        <table id="albumList"></table>
        <div style="width:100%;height: 50px" id="albumPager">
            <div class="alert alert-success" style="display:none" id="msDiv">

            </div>
        </div>
    </div>
</div>