<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="path"/>
<%--富文本编辑器--%>
<script charset="utf-8" src="${path}/kindeditor/kindeditor-all.js"></script>
<script charset="utf-8" src="${path}/kindeditor/lang/zh-CN.js"></script>
<link rel="stylesheet" href="${path}/kindeditor/themes/default/default.css"/>
<style>
    .ui-jqgrid .ui-userdata {
        padding: 4px 36px;
        overflow: hidden;
        min-height: 32px;
    }
</style>
<script>
<%--全局变量--%>
    var modifyGlobal;
    var queryByIdGlobal;
    var addGlobal;
    //懒加载
    $(function () {
        /*
           *  添加kindEditor
           * */
        //添加kindEditor全局变量
        addGlobal = KindEditor.create('#editor_id', {
            themeType: "default",//主图风格
            width: "770px",//宽度
            height: "300px",//高度
            minWidth: "770px",//最小宽度
            minHeight: "300px",//最小高度
            resizeType: 0,//1，改变高度，2，改变高度和宽度，0，不能改变高度和宽度
            allowFileManager: true,//是否展示图片空间
            filePostName: "img",//上传是后台接收的名字
            uploadJson: "${path}/article/articleUpload",//上传后台的路径
            fileManagerJson: "${path}/article/getAllImgs",//指定浏览远程图片的服务器端程序
            //丢失焦点后执行同步数据
            afterBlur: function () {
                this.sync();
            }
        });
        /*
              * 查询KindEditor
              * */
        //查询KindEditor的全局变量
        queryByIdGlobal = KindEditor.create('#queryByIdEditor_id', {
            themeType: "default",//主图风格
            width: "100%",//宽度
            height: "300px",//高度
            minWidth: "100%",//最小宽度
            minHeight: "300px",//最小高度
            resizeType: 0,//1，改变高度，2，改变高度和宽度，0，不能改变高度和宽度
            allowFileManager: true,//是否展示图片空间
            filePostName: "img",//上传是后台接收的名字
            uploadJson: "${path}/article/articleUpload",//上传后台的路径
            fileManagerJson: "${path}/article/getAllImgs",//指定浏览远程图片的服务器端程序
            //丢失焦点后执行同步数据
            afterBlur: function () {
                this.sync();
            }
        });

        /*
                     * 修改KindEditor
                     * */
        //修改KindEditor的全局变量
        modifyGlobal = KindEditor.create('#modifyEditor_id', {
            themeType: "default",//主图风格
            width: "770px",//宽度
            height: "300px",//高度
            minWidth: "770px",//最小宽度
            minHeight: "300px",//最小高度
            resizeType: 0,//1，改变高度，2，改变高度和宽度，0，不能改变高度和宽度
            allowFileManager: true,//是否展示图片空间
            filePostName: "img",//上传是后台接收的名字
            uploadJson: "${path}/article/articleUpload",//上传后台的路径
            fileManagerJson: "${path}/article/getAllImgs",//指定浏览远程图片的服务器端程序
            //丢失焦点后执行同步数据
            afterBlur: function () {
                this.sync();
            }
        });

        $("#articleList").jqGrid({
            url: "${path}/article/articleQueryPage",
            editurl: "${path}/article/articleEdit",
            styleUI: "Bootstrap",//Bootstrap样式
            datatype: "json",//返回形式是json
            autowidth: true,//自适应父容器
            viewrecords: true,//定义要设置的总条数
            toolbar: [true, "top"],//表格工具栏
            hidegrid: true,//是否展示折叠按钮
            height: 230,//高度
            pager: "#articlePager",//分页工具栏
            rowNum: 2,
            rowList: [1, 2, 3, 4, 5, 6, 7, 8],
            caption: "文章",
            multiselect: true,//复选框
            colNames: ["编号", "标题", "作者", "内容", "上师id", "创建时间", "状态"],
            colModel: [
                {name: "id", align: "center"},
                {name: "title", align: "center", editable: true, edittype: "title"},
                {name: "author", align: "center"},
                {name: "content", align: "center", editable: true,hidden:true},
                {name: "guru_id", align: "center"},
                {name: "create_date", align: "center"},
                {
                    name: "status", align: "center", editable: true, edittype: "select", editoptions: {
                        value: "正常:正常;冻结:冻结"
                    }
                }
            ]
        }).jqGrid("navGrid", "#articlePager", {edit: false, add: false, del: true, search: true});

        $("#t_articleList").html("<button class='btn btn-primary'onclick=\"play('" + articleList + "')\"><span class='glyphicon glyphicon-th-list'></span> 查看</button>" +
            "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
            "<button class='btn btn-primary'onclick=\"modify('" + articleList + "')\"><span class='glyphicon glyphicon-pencil'></span> 修改</button>"
        )
        //添加
        $("#save").click(function () {
            //对象序列化
            var serialize = $("#form").serialize();
            $.ajax({
                url: "${path}/article/add",
                type: "POST",
                data: serialize,
                success: function () {
                    //隐藏模态框
                    $("#myModal").modal("hide");
                    //    重置表单
                    $("#form")[0].reset();
                    $("#articleList").trigger("reloadGrid");//刷新表格
                },
                datatype: "json"
            })
        })

        //修改
        $("#modifySave").click(function () {
            //对象序列化
            var serialize = $("#modifyForm").serialize();
            $.ajax({
                url: "${path}/article/modify",
                type: "POST",
                data: serialize,
                success: function () {
                    //隐藏模态框
                    $("#modifyModal").modal("hide");
                    //    重置表单
                    $("#modifyForm")[0].reset();
                    $("#articleList").trigger("reloadGrid");
                },
                datatype: "json"
            })
        })
    })

    //查询
    function play(articleList) {
        var gr = $("#articleList").jqGrid("getGridParam", 'selrow');
        if (gr == null) {
            alert("请选择查看的文章");
        } else {
            //   jqgrid  提供方法  根据id拿到对应的值
            var data = $("#articleList").jqGrid("getRowData",gr);
            //获取选中的这一行的id
            var id = data.id;
           $.ajax({
               url:"${path}/article/queryById",
               type:"POST",
               data:{id:id},
               success:function (result) {
                   $("#queryByIdTitle").val(result.title);
                   $("#queryByIdAuthor").val(result.author);
                   $("#queryByIdGuru_id").val(result.guru_id);
                   $("#queryByIdCreate_date").val(result.create_date);
                   $("#queryByIdStatus").val(result.status);
                   queryByIdGlobal.html(result.content);
                   $("#queryByIdModal").modal({
                       show:true,
                   })
                   $("#articleList").trigger("reloadGrid");
               },
               datatype:"json"
           })
        }
    }

//修改的查询
function modify(articleList) {
    var gr = $("#articleList").jqGrid("getGridParam", 'selrow');
    if (gr == null) {
        alert("请选择查看的文章");
    } else {
        //   jqgrid  提供方法  根据id拿到对应的值
        var data = $("#articleList").jqGrid("getRowData",gr);
        //获取选中的这一行的id
        var id = data.id;
        $.ajax({
            url:"${path}/article/queryById",
            type:"POST",
            data:{id:id},
            success:function (result) {
                $("#modifyId").val(result.id);
                $("#modifyTitle").val(result.title);
                $("#modifyAuthor").val(result.author);
                $("#modifyGuru_id").val(result.guru_id);
                $("#modifyCreate_date").val(result.create_date);
                $("#modifyStatus").val(result.status);
                modifyGlobal.html(result.content);
                $("#modifyModal").modal({
                    show:true,
                })
                $("#articleList").trigger("reloadGrid");//刷新表格
            },
            datatype:"json"
        })
    }
}
</script>
<%--标签页--%>
<ul class="nav nav-tabs">
    <li role="presentation" class="active">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
            文章信息
        </a>
    </li>
    <li role="presentation" class="danger">
        <a class="dropdown-toggle" data-toggle="modal" data-target="#myModal" href="#">
            添加文章
        </a>
    </li>
</ul>

<%--添加模态框--%>
<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="Width:800px;height:700px">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">添加文章</h4>
            </div>
            <div class="modal-body">
                <%--form-horizontal类来实现表单横排显示--%>
                <%--标题--%>
                <form action="" class="form-horizontal" id="form">
                    <div class="form-group">
                        <label for="title" class="col-sm-3 control-label">标题:</label>
                        <div class="col-sm-6">
                            <input type="text" name="title" id="title" class="form-control"/>
                        </div>
                    </div>
                    <%--作者--%>
                    <form action="" class="form-horizontal" id="form">
                        <div class="form-group">
                            <label for="author" class="col-sm-3 control-label">作者:</label>
                            <div class="col-sm-6">
                                <input type="text" name="author" id="author" class="form-control"/>
                            </div>
                        </div>
                        <%--上师id--%>
                        <form action="" class="form-horizontal" id="form">
                            <div class="form-group">
                                <label for="guru_id" class="col-sm-3 control-label">上师id:</label>
                                <div class="col-sm-6">
                                    <input type="text" name="guru_id" id="guru_id" class="form-control"/>
                                </div>
                            </div>
                            <%--创建时间--%>
                            <form action="" class="form-horizontal" id="form">
                                <div class="form-group">
                                    <label for="create_date" class="col-sm-3 control-label">创建时间:</label>
                                    <div class="col-sm-6">
                                        <input type="date" name="create_date" id="create_date"
                                               class="form-control"/>
                                    </div>
                                </div>
                                <%--状态--%>
                                <div class="form-group">
                                    <label for="status" class="col-sm-3 control-label">状态:</label>
                                    <div class="col-sm-6">
                                        <select name="status" id="status" class="form-control">
                                            <option value="正常">
                                                正常
                                                <span class="caret"></span>
                                            </option>
                                            <option value="冻结">
                                                冻结
                                            </option>
                                        </select>
                                    </div>
                                </div>
                                <%--文本域--%>
                                <textarea id="editor_id" name="content"
                                          style="Width:100%;height: 330px"></textarea>
                            </form>
                    </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>
                <button type="button" id="save" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>

<%--查询模态框--%>
<div class="modal fade" id="queryByIdModal" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="Width:800px;height:450px">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="queryByIdModalLabel">查看文章</h4>
            </div>
            <div class="modal-body">
                <%--form-horizontal类来实现表单横排显示--%>
                <%--标题--%>
                <form action="" class="form-horizontal" id="queryByIdForm">
                    <%--文本域--%>
                    <textarea id="queryByIdEditor_id" name="content"style="Width:100%;height: 330px"></textarea>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>
                <%--<button type="button" id="queryByIdSave" class="btn btn-primary">保存</button>--%>
            </div>
        </div>
    </div>
</div>

<%--修改模态框--%>
<div class="modal fade" id="modifyModal" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="Width:800px;height:570px">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="modifyModalLabel">修改文章</h4>
            </div>
            <div class="modal-body">
                <%--form-horizontal类来实现表单横排显示--%>
                <%--标题--%>
                <form action="" class="form-horizontal" id="modifyForm">
                    <%--id--%>
                    <div class="form-group">
                        <label for="modifyId" class="col-sm-3 control-label"style="display:none">ID:</label>
                        <div class="col-sm-6">
                            <input type="text" name="id" id="modifyId" class="form-control" style="display:none"/>
                        </div>
                    </div>
                        <%--标题--%>
                    <div class="form-group">
                        <label for="modifyTitle" class="col-sm-3 control-label">标题:</label>
                        <div class="col-sm-6">
                            <input type="text" name="title" id="modifyTitle" class="form-control"/>
                        </div>
                    </div>
                        <%--状态--%>
                        <div class="form-group">
                            <label for="modifyStatus" class="col-sm-3 control-label">状态:</label>
                                <div class="col-sm-6">
                                    <select name="status" id="modifyStatus" class="form-control">
                                        <option value="正常">
                                            正常
                                            <span class="caret"></span>
                                        </option>
                                        <option value="冻结">
                                            冻结
                                        </option>
                                    </select>
                                </div>
                        </div>
                        <%--文本域--%>
                        <textarea id="modifyEditor_id" name="content"style="Width:100%;height: 330px;"></textarea>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger"data-dismiss="modal">关闭</button>
                <button type="button" id="modifySave" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>

<%--面板--%>
<div class="panel panel-default">
    <div class="panel-body">
        <table id="articleList"></table>
        <div id="articlePager">
        </div>
    </div>
</div>
