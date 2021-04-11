<%--
  Created by IntelliJ IDEA.
  User: zhangpengfei
  Date: 2020/8/19
  Time: 18:39
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../jsfile.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Jquery实现仿搜索引擎文本框自动补全插件 - Jquery-school</title>

    <link rel="stylesheet" href="/static/css/jquery.bigautocomplete.css" type="text/css" />

    <script type="text/javascript" src="/static/js/jquery-1.7.1.js"></script>
    <script type="text/javascript" src="/static/js/jquery.bigautocomplete.js"></script>
    <script type="text/javascript">
        var videos=${videos};
        //var arr = jQuery.parseJSON('${data}');

        console.info(videos);

//        for (var i=0;i<;i++) {
//            console.log(i);
//        }
        $(function(){

            $("#videoVal").bigAutocomplete({
                width:543,
//                data:[{title:"宾馆1",result:{ff:"qq"}},
//                    {title:"宾馆12"},
//                    {title:"中国银行"},
//                    {title:"中国移动"},
//                    {title:"中国好声音第三期"},
//                    {title:"中国好声音 第一期"},
//                    {title:"中国电信网上营业厅"},
//                    {title:"中国工商银行"},
//                    {title:"中国好声音第二期"},
//                    {title:"中国地图"}],

                data:videos,
                callback:function(data){
                   console.info(data.result);
                   window.location.href=data.result;

                }
            });

        })
    </script>
</head>
<body>
<style type="text/css">
    *{margin:0;padding:0;list-style-type:none;}
    a,img{border:0;}
    .demo{width:720px;margin:30px auto;}
    .demo h2{font-size:16px;color:#3366cc;height:30px;}
    .demo li{float:left;}
    .text,.button{}
    .text{width:529px;height:40px;padding:4px 7px;padding:6px 7px 2px\9;font:16px arial;border:1px solid #cdcdcd;border-color:#9a9a9a #cdcdcd #cdcdcd #9a9a9a;vertical-align:top;outline:none;margin:0 5px 0 0;}
    .button{width:95px;height:40px;padding:0;padding-top:2px\9;border:0;background-position:0 -35px;background-color: #5597ff;cursor:pointer}
</style>

<div class="demo">
    <div class="row">
        <div class="col-md-3" style="position: relative;left:10%;color: #0066FF;">
            <h1>Blue</h1>
        </div>
        <div class="col-md-3">
            <img  src="/static/image/search/boshi.jpg"/>
        </div>
        <div class="col-md-3" style="position: relative;right:10%;color: #0066FF;top:40%;">
            <h1>Search</h1>
        </div>
    </div>
    <%--<div align="center"><h2>Blue</h2><h2>search</h2></div>--%>
    <form action="/video/" method="post" name="searchform" id="searchform" class="searchinfo">
        <ul>
            <li><input type="text" id="videoVal" value="" class="text" /></li>
            <%--<li><input type="submit" value="搜索" class="button" /></li>--%>
            <li><input type="submit" value="搜索" style="font-size: large;color: #ebebee" class="button" /></li>
        </ul>
    </form>
</div>

</body>
</html>
