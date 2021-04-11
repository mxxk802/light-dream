<%--
  Created by IntelliJ IDEA.
  User: zhangpengfei
  Date: 2020/6/22
  Time: 2:17
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>股票查询</title>
    <style type="text/css">
        <!--
        body {
            margin-left: 0px;
            margin-top: 0px;
            margin-right: 0px;
            margin-bottom: 0px;
        }

        .STYLE1 {
            font-size: 15px;
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
</head>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td height="30" background="/static/image/user/tab_05.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td width="12" height="30"><img src="/static/image/user/tab_03.gif" width="12" height="30" /></td>
                <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="46%" valign="middle"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td width="5%"><div align="center"><img src="/static/image/user/tb.gif" width="16" height="16" /></div></td>
                                <td width="95%" class="STYLE1"><span class="STYLE3">你当前的位置</span>：[${onelevel}]-[${twolevel}]</td>
                            </tr>
                        </table></td>
                        <td width="54%"><table border="0" align="right" cellpadding="0" cellspacing="0">
                            <tr>
                                <td width="60"><table width="87%" border="0" cellpadding="0" cellspacing="0">
                                    <tr>
                                        <td class="STYLE1"><div align="center">
                                            <input type="checkbox" name="checkbox62" value="checkbox" />
                                        </div></td>
                                        <td class="STYLE1"><div align="center">全选</div></td>
                                    </tr>
                                </table></td>
                                <td width="60"><table width="90%" border="0" cellpadding="0" cellspacing="0">
                                    <tr>
                                        <td class="STYLE1"><div align="center"><img src="/static/image/user/22.gif" width="14" height="14" /></div></td>
                                        <td class="STYLE1"><div align="center">新增</div></td>
                                    </tr>
                                </table></td>
                                <td width="60"><table width="90%" border="0" cellpadding="0" cellspacing="0">
                                    <tr>
                                        <td class="STYLE1"><div align="center"><img src="/static/image/user/33.gif" width="14" height="14" /></div></td>
                                        <td class="STYLE1"><div align="center">修改</div></td>
                                    </tr>
                                </table></td>
                                <td width="52"><table width="88%" border="0" cellpadding="0" cellspacing="0">
                                    <tr>
                                        <td class="STYLE1"><div align="center"><img src="/static/image/user/11.gif" width="14" height="14" /></div></td>
                                        <td class="STYLE1"><div align="center">删除</div></td>
                                    </tr>
                                </table></td>
                            </tr>
                        </table></td>
                    </tr>
                </table></td>
                <td width="16"><img src="/static/image/user/tab_07.gif" width="16" height="30" /></td>
            </tr>
        </table></td>
    </tr>
    <tr>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td width="8" background="/static/image/user/tab_12.gif">&nbsp;</td>
                <td><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6" onmouseover="changeto()"  onmouseout="changeback()">
                    <tr>
                        <td width="3%" height="22" background="/static/image/user/bg.gif" bgcolor="#FFFFFF"><div align="center">
                            <input type="checkbox" name="checkbox" value="checkbox" />
                        </div></td>
                        <td width="3%" height="22" background="/static/image/user/bg.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">序号</span></div></td>
                        <td width="10%" height="22" background="/static/image/user/bg.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">股票名称</span></div></td>
                        <td width="10%" height="22" background="/static/image/user/bg.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">股票代码</span></div></td>
                        <td width="15%" height="22" background="/static/image/user/bg.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">行业</span></div></td>
                        <td width="10%" height="22" background="/static/image/user/bg.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">利润</span></div></td>
                        <td width="23%" height="22" background="/static/image/user/bg.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">市值</span></div></td>
                        <td width="15%" height="22" background="/static/image/user/bg.gif" bgcolor="#FFFFFF" class="STYLE1"><div align="center">基本操作</div></td>
                    </tr>
                    <c:forEach items="${shareData}" var="shares">
                        <tr>
                            <td height="20" bgcolor="#FFFFFF"><div align="center">
                                <input type="checkbox" name="checkbox2" value="checkbox" />
                            </div></td>
                            <td height="20" bgcolor="#FFFFFF"><div align="center" class="STYLE1">
                                <div align="center">${shares.id}</div>
                            </div></td>
                            <td height="20" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">${shares.stockName}</span></div></td>
                            <td height="20" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">${shares.stockCode}</span></div></td>
                            <td height="20" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">${shares.industry}</span></div></td>
                            <td height="20" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">${shares.profit}</span></div></td>
                            <td height="20" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">${shares.holdShare}</span></div></td>
                            <td height="20" bgcolor="#FFFFFF"><div align="center"><span class="STYLE4"><img src="/static/image/user/edt.gif" width="16" height="16" />编辑&nbsp; &nbsp;
                                <img src="/static/image/user/del.gif" width="16" height="16" />删除</span>
                                <img src="/static/image/icns/user_select.gif" width="16" height="16"/></span>
                                <a href="/stock/getDetail/${shares.id}">详情</a>
                            </div></td>
                        </tr>
                    </c:forEach>

                </table></td>
                <td width="8" background="/static/image/user/tab_15.gif">&nbsp;</td>
            </tr>
        </table></td>
    </tr>
    <tr>
        <td height="35" background="/static/image/user/tab_19.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td width="12" height="35"><img src="/static/image/user/tab_18.gif" width="12" height="35" /></td>
                <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td class="STYLE4">&nbsp;&nbsp;共有 120 条记录，当前第 1/10 页</td>
                        <td><table border="0" align="right" cellpadding="0" cellspacing="0">
                            <tr>
                                <td width="40"><img src="/static/image/user/first.gif" width="37" height="15" /></td>
                                <td width="45"><img src="/static/image/user/back.gif" width="43" height="15" /></td>
                                <td width="45"><img src="/static/image/user/next.gif" width="43" height="15" /></td>
                                <td width="40"><img src="/static/image/user/last.gif" width="37" height="15" /></td>
                                <td width="100"><div align="center"><span class="STYLE1">转到第
                    <input name="textfield" type="text" size="4" style="height:12px; width:20px; border:1px solid #999999;" />
                    页 </span></div></td>
                                <td width="40"><img src="/static/image/user/go.gif" width="37" height="15" /></td>
                            </tr>
                        </table></td>
                    </tr>
                </table></td>
                <td width="16"><img src="/static/image/user/tab_20.gif" width="16" height="35" /></td>
            </tr>
        </table></td>
    </tr>
</table>
</body>
<script>
//    function detail() {
//        alert("dd");
//    }
</script>
</html>
