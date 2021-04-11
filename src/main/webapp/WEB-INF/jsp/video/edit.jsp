<%--
  Created by IntelliJ IDEA.
  User: zhangpengfei
  Date: 2020/8/20
  Time: 20:09
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../jsfile.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑页面</title>
</head>
<body>
<div id="info" class="alert alert-success alert-dismissable hidden">
    <button type="button" class="close" data-dismiss="alert"
            aria-hidden="true">
        &times;
    </button>
    成功！很好地完成了提交。
</div>

<form class="form-horizontal" role="form" action="/video/update" modelAttribute="video" method="POST">
    <input type="hidden" name="id" value="${video.id}">
    <div class="form-group">
        <label for="address" class="col-sm-2 control-label">地址:</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="address" name="address" placeholder="地址" value="${video.address}">
        </div>
    </div>
    <div class="form-group">
        <label for="introduce" class="col-sm-2 control-label">视频介绍:</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="introduce" name="introduce" value="${video.introduce}" placeholder="视频介绍">
        </div>
    </div>
    <div class="form-group">
        <label for="keyWord" class="col-sm-2 control-label">关键词:</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="keyWord" name="keyWord" value="${video.keyWord}" placeholder="关键词">
        </div>
    </div>
    <div class="form-group">
        <label for="videoType" class="col-sm-2 control-label">类型:</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="videoType" name="videoType" value="${video.videoType}" placeholder="类型">
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-primary">保存</button>
        </div>
    </div>
</form>
</body>
</html>
