<%--
  Created by IntelliJ IDEA.
  User: zhangpengfei
  Date: 2020/4/9
  Time: 21:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../jsfile.jsp" %>
<html>
<head>
    <title>无标题文档</title>
    <style type="text/css">
        <!--
        body {
            margin-left: 0px;
            margin-top: 0px;
            margin-right: 0px;
            margin-bottom: 0px;
        }

        .STYLE1 {
            font-size: 16px;
        }

        .STYLE3 {
            font-size: 16px;
            font-weight: bold;
        }

        .STYLE4 {
            color: #03515d;
            font-size: 16px;
        }
        #userName,#tel,#age{
            padding: -5px;
            width: 125px;
            height: 20px;
        }
        -->
    </style>
    <script>
        var highlightcolor = '#c1ebff';
        //此处clickcolor只能用win系统颜色代码才能成功,如果用#xxxxxx的代码就不行,还没搞清楚为什么:(
        var clickcolor = '#51b2f6';

        function changeto() {
            source = event.srcElement;
            if (source.tagName == "TR" || source.tagName == "TABLE")
                return;
            while (source.tagName != "TD")
                source = source.parentElement;
            source = source.parentElement;
            cs = source.children;
//alert(cs.length);
            if (cs[1].style.backgroundColor != highlightcolor && source.id != "nc" && cs[1].style.backgroundColor != clickcolor)
                for (i = 0; i < cs.length; i++) {
                    cs[i].style.backgroundColor = highlightcolor;
                }
        }

        function changeback() {
            if (event.fromElement.contains(event.toElement) || source.contains(event.toElement) || source.id == "nc")
                return
            if (event.toElement != source && cs[1].style.backgroundColor != clickcolor)
//source.style.backgroundColor=originalcolor
                for (i = 0; i < cs.length; i++) {
                    cs[i].style.backgroundColor = "";
                }
        }

        function clickto() {
            source = event.srcElement;
            if (source.tagName == "TR" || source.tagName == "TABLE")
                return;
            while (source.tagName != "TD")
                source = source.parentElement;
            source = source.parentElement;
            cs = source.children;
//alert(cs.length);
            if (cs[1].style.backgroundColor != clickcolor && source.id != "nc")
                for (i = 0; i < cs.length; i++) {
                    cs[i].style.backgroundColor = clickcolor;
                }
            else
                for (i = 0; i < cs.length; i++) {
                    cs[i].style.backgroundColor = "";
                }
        }

        function skip(index) {
            window.location.href = "/user/allUser?pageIndex=" + index;
        }

        function skipTo() {
            var pageIndex = $("#skipIndex").val();
            window.location.href = "/user/allUser?pageIndex=" + pageIndex;
        }

        $(function () {

            $("#standardQueryBtn").click(function () {

//                $("#dg").datagrid("load",{
//                    "standard.name": $("#qName").val(),
//                    "standard.minWeight": $("#qMinWeight").val(),
//                    "standard.minLength": $("#qMinLength").val()
//                });

            });

            $("#exportUser").click(function () {
                $.get("/user/exportUser",function (result) {
                    if(result.success){
                        alert("生成成功！");
                    }
                })

            })

        });

    </script>
</head>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td height="30" background="/static/image/user/tab_05.gif">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td width="12" height="30"><img src="/static/image/user/tab_03.gif" width="12" height="30"/></td>
                    <td>
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td width="40%" valign="middle">
                                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                        <tr>
                                            <td width="5%">
                                                <div align="center"><img src="/static/image/user/tb.gif" width="16"
                                                                         height="16"/></div>
                                            </td>
                                            <td width="95%" class="STYLE1"><span class="STYLE3">你当前的位置</span>：[业务中心]-[我的邮件]
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                                <td width="60%">
                                    <table border="0" align="right" cellpadding="0" cellspacing="0">
                                        <tr>
                                            <td width="60">
                                                <table width="87%" border="0" cellpadding="0" cellspacing="0">
                                                    <tr>
                                                        <td class="STYLE1">
                                                            <div align="center">
                                                                <input type="checkbox" name="checkbox62"
                                                                       value="checkbox"/>
                                                            </div>
                                                        </td>
                                                        <td class="STYLE1">
                                                            <div align="center">全选</div>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </td>
                                            <td width="60">
                                                <table width="90%" border="0" cellpadding="0" cellspacing="0">
                                                    <tr>
                                                        <td class="STYLE1">
                                                            <div align="center"><img src="/static/image/user/22.gif"
                                                                                     width="10" height="14"/></div>
                                                        </td>
                                                        <td class="STYLE1">
                                                            <div align="center">新增</div>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </td>
                                            <td width="60">
                                                <table width="90%" border="0" cellpadding="0" cellspacing="0">
                                                    <tr>
                                                        <td class="STYLE1">
                                                            <div align="center"><img src="/static/image/user/33.gif"
                                                                                     width="10" height="14"/></div>
                                                        </td>
                                                        <td class="STYLE1">
                                                            <div align="center">修改</div>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </td>
                                            <td width="52">
                                                <table width="88%" border="0" cellpadding="0" cellspacing="0">
                                                    <tr>
                                                        <td class="STYLE1">
                                                            <div align="center"><img src="/static/image/user/11.gif"
                                                                                     width="10" height="14"/></div>
                                                        </td>
                                                        <td class="STYLE1">
                                                            <div align="center">删除</div>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </td>
                    <td width="16"><img src="/static/image/user/tab_07.gif" width="16" height="30"/></td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td width="8" background="/static/image/user/tab_12.gif">&nbsp;</td>
                    <td>
                        <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6"
                               onmouseover="changeto()" onmouseout="changeback()">
                            <tr>

                                <div class="row">
                                    <div class="col-md-3">
                                        <div class="control-group pull-left">
                                            <input type="text" name="userName" id="userName"/> 
                                            <label class="control-label">用户名</label>
                                        </div>
                                    </div>

                                    <div class="col-md-3">
                                        <div class="control-group pull-left">
                                            <input type="text" name="tel" id="tel"/>
                                            <label class="control-label">手机号码</label>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="control-group pull-left">
                                            <input type="text" name="age" id="age"/>
                                            <label class="control-label">年龄</label>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="control-group pull-left">
                                            <a id="standardQueryBtn" href="javascript:void(0)" class="btn btn-primary btn-xs" data-options="iconCls:'icon-search'" style="padding-top: -5px;">搜索</a>
                                            <a id="exportUser" href="javascript:void(0)" class="btn btn-primary btn-xs" data-options="iconCls:'icon-search'" style="padding-top: -5px;">导出</a>
                                        </div>
                                    </div>
                                </div>
                            </tr>
                            <tr>
                                <td width="3%" height="22" background="/static/image/user/bg.gif" bgcolor="#FFFFFF">
                                    <div align="center">
                                        <input type="checkbox" name="checkbox" value="checkbox"/>
                                    </div>
                                </td>
                                <td width="3%" height="22" background="/static/image/user/bg.gif" bgcolor="#FFFFFF">
                                    <div align="center"><span class="STYLE1">序号</span></div>
                                </td>
                                <td width="12%" height="22" background="/static/image/user/bg.gif" bgcolor="#FFFFFF">
                                    <div align="center"><span class="STYLE1">用户名</span></div>
                                </td>
                                <td width="14%" height="22" background="/static/image/user/bg.gif" bgcolor="#FFFFFF">
                                    <div align="center"><span class="STYLE1">密码</span></div>
                                </td>
                                <td width="18%" height="22" background="/static/image/user/bg.gif" bgcolor="#FFFFFF">
                                    <div align="center"><span class="STYLE1">联系电话</span></div>
                                </td>
                                <td width="23%" height="22" background="/static/image/user/bg.gif" bgcolor="#FFFFFF">
                                    <div align="center"><span class="STYLE1">邮箱地址</span></div>
                                </td>
                                <td width="15%" height="22" background="/static/image/user/bg.gif" bgcolor="#FFFFFF"
                                    class="STYLE1">
                                    <div align="center">基本操作</div>
                                </td>
                            </tr>
                            <c:forEach items="${allUser}" var="user">
                                <tr>
                                    <td height="20" bgcolor="#FFFFFF">
                                        <div align="center">
                                            <input type="checkbox" name="checkbox2" value="checkbox"/>
                                        </div>
                                    </td>
                                    <td height="20" bgcolor="#FFFFFF">
                                        <div align="center" class="STYLE1">
                                            <div align="center">${user.id}</div>
                                        </div>
                                    </td>
                                    <td height="20" bgcolor="#FFFFFF">
                                        <div align="center"><span class="STYLE1">${user.username}</span></div>
                                    </td>
                                    <td height="20" bgcolor="#FFFFFF">
                                        <div align="center"><span class="STYLE1">${user.password} </span></div>
                                    </td>
                                    <td bgcolor="#FFFFFF">
                                        <div align="center"><span class="STYLE1">${user.tel}</span></div>
                                    </td>
                                    <td height="20" bgcolor="#FFFFFF">
                                        <div align="center"><span class="STYLE1">${user.mail}</span></div>
                                    </td>
                                    <td height="20" bgcolor="#FFFFFF">
                                        <div align="center"><span class="STYLE4"><img src="/static/image/user/edt.gif"
                                                                                      width="16" height="16"/>编辑&nbsp; &nbsp;<img
                                                src="/static/image/user/del.gif" width="16" height="16"/>删除</span></div>
                                    </td>
                                </tr>
                            </c:forEach>

                        </table>
                    </td>
                    <td width="8" background="/static/image/user/tab_15.gif">&nbsp;</td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td height="35" background="/static/image/user/tab_19.gif">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td width="12" height="35"><img src="/static/image/user/tab_18.gif" width="12" height="35"/></td>
                    <td>
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td class="STYLE4">&nbsp;&nbsp;共有 ${totalRecord} 条记录，当前第 ${pageIndex}/${pageCount} 页
                                </td>
                                <td>
                                    <table border="0" align="right" cellpadding="0" cellspacing="0">
                                        <tr>
                                            <td width="45"><img src="/static/image/user/first.gif" width="40"
                                                                height="20" onclick="skip(1)"/></td>
                                            <td width="45"><img src="/static/image/user/back.gif" width="43" height="20"
                                                                onclick="skip(${pageIndex}-1)"/></td>
                                            <td width="45"><img src="/static/image/user/next.gif" width="43" height="20"
                                                                onclick="skip(${pageIndex}+1)"/></td>
                                            <td width="40"><img src="/static/image/user/last.gif" width="37" height="20"
                                                                onclick="skip(${pageCount})"/></td>
                                            <td width="100">
                                                <div align="center"><span class="STYLE1">转到第
                    <input id="skipIndex" name="textfield" type="text" size="20"
                           style="height:20px; width:20px; border:1px solid #999999;"/>
                    页 </span></div>
                                            </td>
                                            <td width="40"><img src="/static/image/user/go.gif" width="37" height="20"
                                                                onclick="skipTo()"/></td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </td>
                    <td width="16"><img src="/static/image/user/tab_20.gif" width="16" height="35"/></td>
                </tr>
            </table>
        </td>
    </tr>
</table>
</body>
</html>
