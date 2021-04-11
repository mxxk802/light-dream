<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>登录页面</title>
    <link rel="stylesheet" type="text/css" href="/static/css/Styles/base.css"/>
    <link rel="stylesheet" type="text/css" href="/static/css/Styles/admin-all.css"/>
    <link rel="stylesheet" type="text/css" href="/static/css/Styles/bootstrap.min.css"/>
    <script type="text/javascript" src="/static/plugins/jquery/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="/static/plugins/jquery/jquery.spritely-0.6.js"></script>
    <script type="text/javascript" src="/static/js/chur.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/static/css/Styles/login.css"/>
    <script type="text/javascript">
        $(function () {
            $('#clouds').pan({fps: 20, speed: 0.7, dir: 'right', depth: 10});

            setTimeout("setValid()", 2000);

            $('#login').click(function () {
                var username = $('#uid').val();
                var password = $('#pwd').val();
                var code = $('#code').val();
                //alert(username+password+code);
                if ($('#uid').val() == "" || $('#pwd').val() == "" || $('#code').val() == "") {
                    $('.tip').html('用户名或密码不可为空！');
                } else {
                    $("#upData").submit();
//                    $.get("/mxxk/index", {userName: username, userPass: password, validCode: code});
                    // window.location.href = "/mxxk/index?userName=" + username + "&userPass=" + password + "&validCode=" + code;
                }


            });
        });
        function setValid() {
            $(".imgcode").attr("src","/static/image/login/validate.jpg");
        }


    </script>
</head>
<body>

<div id="clouds" class="stage"></div>
<div class="loginmain">
</div>

<div class="row-fluid">
    <h1>LSXY后台管理系统</h1>
    <form action="/mxxk/index" id="upData" method="POST">
        <p>
            <label>帐&nbsp;&nbsp;&nbsp;号：<input type="text" id="uid" name="userName"/></label>
        </p>
        <p>
            <label>密&nbsp;&nbsp;&nbsp;码：<input type="password" id="pwd" name="password"/></label>
        </p>

        <p class="pcode">
            <label>效验码：<input type="text" id="code" name="validCode" maxlength="5" class="code" value="e5g88"/>
                <img src="#" alt="" class="imgcode" style="margin-top: 4px;"/><a href="#">下一张</a></label>
        </p>
        <p class="tip">${message}&nbsp;</p>
        <hr/>
        <input type="button" value=" 登 录 " class="btn btn-primary btn-large" id="login"/>
        &nbsp;&nbsp;&nbsp;<input type="reset" value=" 取 消 " class="btn btn-large"/>
    </form>


</div>
</body>
</html>