<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><!-- import="com.mxy.vo.Admin" -->
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>lsxy系统管理后台主页</title>
    <%@include file="jsfile.jsp" %>
    <%@ include file="model.jsp" %>
    <script type="text/javascript">
        $(function () {
            //更改密码
            $('#updatePass').click(function () {
                $.dialog({
                    title: '密码修改',
                    content: 'url:<%=request.getContextPath() %>/loginController/moodEssay',
                    lock: true,
                    width: 500,
                    height: 300,
                    cancelVal: '取消',
                    button: [{
                        id: 'chur',
                        name: '确定',
                        callback: function () {
                            $('body').alert({
                                title: '提示信息',
                                content: '您确定修改密码吗？',
                                buttons: [{
                                    id: 'yes',
                                    name: '确定',
                                    callback: function () {
                                        alert('确定');
                                    }
                                }, {
                                    id: 'no',
                                    name: '取消',
                                    callback: function () {
                                        alert('取消');
                                    }
                                }]
                            });
                        }
                    }],
                    cancel: true,
                });
            });
        });


    </script>
</head>
<body>
<div class="warp">
    <!--头部开始-->
    <div class="top_c">
        <div class="top-menu">
            <ul class="top-menu-nav">
                <li><a href="#">首页</a></li>
                <li><a href="#">我的日记<i class="tip-up"></i></a>
                    <ul class="kidc">
                        <li><a target="Conframe"
                               href="<%=request.getContextPath() %>/loginController/moodEssay">心情随笔</a></li>
                        <li><a target="Conframe" href="/CxfTestServer/loginController/contactList">联系人</a></li>
                        <li><a target="Conframe" href="#"></a></li>
                        <li><a target="Conframe" href="#"></a></li>
                        <li><a target="Conframe" href="#"></a></li>
                    </ul>
                </li>
                <li><a href="#">我的备忘录<i class="tip-up"></i></a>
                    <ul class="kidc">
                        <li><a target="Conframe" href="/CxfTestServer/ContactPersonController/contactList">联系人信息管理</a>
                        </li>
                        <li><a target="Conframe" href="<%=request.getContextPath() %>/ContactPersonController/hello">用户列表</a>
                        </li>
                        <li><a target="Conframe" href="testController">查询排序</a></li>
                        <li><a target="Conframe" href="Template/find-1.html">查询结果一</a></li>
                        <li><a target="Conframe" href="Template/find-2.html">查询结果二</a></li>
                    </ul>
                </li>
                <li><a href="#">测试页面<i class="tip-up"></i></a>
                    <ul class="kidc">
                        <li><b class="tip"></b><a target="Conframe"
                                                  href="<%=request.getContextPath() %>/testController/userTest">request测试</a>
                        </li>
                        <li><b class="tip"></b><a target="Conframe"
                                                  href="<%=request.getContextPath() %>/testController/autoShow">自动补全测试</a>
                        </li>
                        <li><b class="tip"></b><a target="Conframe"
                                                  href="<%=request.getContextPath() %>/testController/setTime">定时</a>
                        </li>
                        <li><b class="tip"></b><a target="Conframe"
                                                  href="<%=request.getContextPath() %>/testController/netoutCityList">网外城市</a>
                        </li>
                        <li><b class="tip"></b><a target="Conframe"
                                                  href="<%=request.getContextPath() %>/supplyDataController/supplyInformation">补充信息</a>
                        </li>
                    </ul>
                </li>
                <li><a href="#">查询用户<i class="tip-up"></i></a>
                    <ul class="kidc">
                        <li><a target="Conframe" href="<%=request.getContextPath() %>/loginController/userList">用户查询</a>
                        </li>
                        <li><a target="Conframe"
                               href="<%=request.getContextPath() %>/loginController/userSearch">用户列表</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/form-tabs.html">标签式表单</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/form-tree.html">树+表单</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/form-guide.html">向导</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/form-tabs-list.html">标签页+列表</a></li>
                    </ul>
                </li>
                <li><a href="#">提示<i class="tip-up"></i></a>
                    <ul class="kidc">
                        <li><b class="tip"></b><a target="Conframe" href="Template/Alert.html">权限提示</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/Alert.html">出错提示</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/Alert.html">警告提示</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/Alert.html">询问提示</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/Alert.html">对话框一</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/Alert.html">对话框二</a></li>
                    </ul>
                </li>
                <li><a href="#">辅助信息<i class="tip-up"></i></a>
                    <ul class="kidc">
                        <li><b class="tip"></b><a target="Conframe" href="/video/index">视频检索</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="/video/getAllVideo?onelevel=辅助信息&twolevel=视频列表">video列表</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="/website/index">学习网址</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/formstyle.html">操作记录</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/formstyle.html">提示</a></li>
                    </ul>
                </li>
            </ul>
        </div>
        <div class="top-nav">欢迎登录，管理员 : ${admin.username} &nbsp;&nbsp;<a id="updatePass" style="color:#FEF8FF"
                                                                       href="javascript:void(0)"
                                                                       class="btn btn-primary btn-xs">修改密码</a> | <a
                href="javascript:window.history.go(-1)" style="color:#FEF8FF" class="btn btn-primary btn-xs" id="goOut">安全退出</a>
        </div>

    </div>
    <!--头部结束-->
    <!--左边菜单开始-->
    <div class="left_c left">
        <h1>系统操作菜单</h1>
        <div class="acc">
            <div>
                <a class="one">股票查询</a>
                <ul class="kid">
                    <li><b class="tip"></b><a target="Conframe"
                                              href="/stock/getAllStocks?onelevel=股票查询&twolevel=股票列表" >股票列表</a></li>
                    <li><b class="tip"></b><a target="Conframe" href=""></a>aaaa</li>
                    <li><b class="tip"></b><a target="Conframe" href="Template/find-order.html">查询排序</a></li>
                    <li><b class="tip"></b><a target="Conframe" href="Template/find-1.html">查询结果一</a></li>
                    <li><b class="tip"></b><a target="Conframe" href="Template/find-2.html">查询结果二</a></li>
                </ul>
            </div>
            <div>
                <a class="one">维护界面</a>
                <ul class="kid">
                    <li><b class="tip"></b><a target="Conframe" href="Template/Maintain-add.html">增加</a></li>
                    <li><b class="tip"></b><a target="Conframe" href="Template/Maintain-edit.html">修改</a></li>
                    <li><b class="tip"></b><a target="Conframe" href="Template/Maintain-del.html">删除</a></li>
                </ul>
            </div>
            <div>
                <a class="one">表单风格</a>
                <ul class="kid">
                    <li><b class="tip"></b><a target="Conframe" href="Template/form-Master-slave.html">主从表单</a></li>
                    <li><b class="tip"></b><a target="Conframe" href="Template/form-collapse.html">折叠表单</a></li>
                    <li><b class="tip"></b><a target="Conframe" href="Template/form-tabs.html">标签式表单</a></li>
                    <li><b class="tip"></b><a target="Conframe" href="Template/form-tree.html">树+表单</a></li>
                    <li><b class="tip"></b><a target="Conframe" href="Template/form-guide.html">向导</a></li>
                    <li><b class="tip"></b><a target="Conframe" href="Template/form-tabs-list.html">标签页+列表</a></li>
                </ul>
            </div>
            <div>
                <a class="one">提示</a>
                <ul class="kid">
                    <li><b class="tip"></b><a target="Conframe" href="Template/Alert.html">权限提示</a></li>
                    <li><b class="tip"></b><a target="Conframe" href="Template/Alert.html">出错提示</a></li>
                    <li><b class="tip"></b><a target="Conframe" href="Template/Alert.html">警告提示</a></li>
                    <li><b class="tip"></b><a target="Conframe" href="Template/Alert.html">询问提示</a></li>
                    <li><b class="tip"></b><a target="Conframe" href="Template/Alert.html">对话框一</a></li>
                    <li><b class="tip"></b><a target="Conframe" href="Template/Alert.html">对话框二</a></li>
                </ul>
            </div>
            <div>
                <a class="one">用户管理</a>
                <ul class="kid">
                    <li><b class="tip"></b><a target="Conframe" href="/user/allUser?pageIndex=1">用户列表</a></li>
                    <li><b class="tip"></b><a target="Conframe" href="Template/formstyle.html">数据说明</a></li>
                    <li><b class="tip"></b><a target="Conframe" href="Template/formstyle.html">操作记录</a></li>
                    <li><b class="tip"></b><a target="Conframe" href="Template/formstyle.html">提示</a></li>
                </ul>
            </div>
            <%--<div id="datepicker"></div>--%>
        </div>

    </div>
    <!--左边菜单结束-->
    <!--右边框架开始-->
    <div class="right_c">
        <div class="nav-tip" onclick="javascript:void(0)">&nbsp;</div>

    </div>
    <div class="Conframe">
        <iframe name="Conframe" id="Conframe"></iframe>
    </div>
    <!--右边框架结束-->

    <!--底部开始-->
    <div class="bottom_c">Copyright &copy;2020-2030 mxxk工作室版权所有</div>
    <!--底部结束-->
</div>

</body>
</html>
