<%--
  Created by IntelliJ IDEA.
  User: zhangpengfei
  Date: 2020/7/13
  Time: 0:10
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../jsfile.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    String onelevel=request.getParameter("onelevel");
    String twolevel=request.getParameter("twolevel");

    session.setAttribute("onelevel",onelevel);
    session.setAttribute("twolevel",twolevel);
%>
<head>
    <title>Title</title>
</head>
<body>
<div class="panel panel-primary">
    <div class="panel-heading">
        <div class="row">
            <div class="col-md-6">
                <h4 style="font-weight: 400;width: auto">当前位置:[<%=session.getAttribute("onelevel")%>]>>[<%=session.getAttribute("twolevel")%>]</h4>
            </div>
            <div class="col-md-3"></div>
            <div class="col-md-3">
                <button type="button" class="btn btn-success" style="margin-left: 80%;" onclick="create()">新建</button>&nbsp;&nbsp;
            </div>
        </div>


    </div>
    <div class="panel-body">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>序号</th>
                <th>视频地址</th>
                <th>视频介绍</th>
                <th>关键词</th>
                <th>类型</th>
                <th>基本操作</th>
            </thead>
            <tbody>
            <c:forEach items="${videos}" var="video">
            <tr class="active">
                <td>${video.id}</td>
                <td><a href="${video.address}"/>${video.address}</td>
                <td>${video.introduce}</td>
                <td>${video.keyWord}</td>
                <td>${video.videoType}</td>
                <td>
                    <div class="btn-group">
                        <button type="button" class="btn btn-primary" style="margin-right: 5px;"
                                onclick="getDetail(${video.id})">详情
                        </button>&nbsp;&nbsp;
                        <button type="button" class="btn btn-success" style="margin-right: 5px;"
                                onclick="edit(${video.id})">编辑
                        </button>&nbsp;&nbsp;
                        <button type="button" class="btn btn-warning" style="margin-right: 5px;"
                                onclick="deleteVideo(${video.id})">删除
                        </button>&nbsp;&nbsp;
                    </div>
                </td>
                </c:forEach>

            </tbody>
        </table>
        <ul class="pagination pagination-lg">
            <li><a href="#" class="active">&laquo;</a></li>
            <c:if test="${totalRecord > 0}">
                <c:forEach var="i" begin="1" end="${pageCount}" step="1">
                    <li><a href="/video/getAllVideo?onelevel=${onelevel}&twolevel=${twolevel}&pageIndex=${i}">${i} </a>
                    </li>
                </c:forEach>
            </c:if>
            <li><a href="#">&raquo;</a></li>
        </ul>
    </div>
</div>

</body>
<script>
    var aa = '${totalRecord}';
    $(document).ready(function () {
        $("table tr:not(:first):odd").attr("class", "success");
        $("table tr:not(:first):even").attr("class", "warning");

    });

    function skip(index) {
        window.location.href = "/stock/getAllStocks?pageIndex=" + index;
    }

    function skipTo() {
        var pageIndex = $("#skipIndex").val();
        window.location.href = "/stock/getAllStocks?pageIndex=" + pageIndex;
    }

    function getDetail(id) {
        window.location.href = "/stock/getDetail/" + id;
    }

    function create() {
        window.location.href = "/video/new0";
    }

    function edit(id) {
        window.location.href = "/video/edit/" + id+"?onelevel=${onelevel}&twolevel=${twolevel}";
    }

    function deleteVideo(id) {

        var mymessage=confirm("确定要删除当前信息吗？");
        if(mymessage==true){
            // window.location.href = "/video/delete/" + id;
        }

    }
</script>
</html>
