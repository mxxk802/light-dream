<#ftl attributes={"content_type":"text/html; charset=UTF-8"}>
<?xml version="1.0" encoding="utf-8"?>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
${name}

<#assign m=5>
#解决> 符号问题1.加括号，2使用gt 代替
<#if (m>4)>
    m>4
</#if>
<#if m gt 3>
    m>3
</#if>
#assign 标签的使用有四种
<#assign name="zpf">
my name is ${name}

<#assign info={"mobile":"153","address":"朝阳区"}>
my mobile is ${info.mobile},address is ${info.address}

<#if mouse??>
Mouse found
<#else>
No mouse found
</#if>
Creating mouse...
<#assign mouse = "Jerry">
<#if mouse??>
Mouse found
<#else>
No mouse found
</#if>

<#assign tempNum=20>

${tempNum}
${tempNum?string.number}或${tempNum?string("number")}  //结果为20
${tempNum?string.currency}或${tempNum?string("currency")} //结果为￥20.00
${tempNum?string. percent}或${tempNum?string("percent")} //结果为2,000%

<#--FreeMarker中的round、floor和ceiling数字的舍入处理-->
<#--round:四舍五入-->
<#--floor:向下取整-->
<#--ceiling:向上取整-->

<#assign numList = [12,0.23,89,12.03,69.56,45.67,-0.56,-8.05,-89.56,4.69]/>
<#list numList as num>
${num} ?round=${num?round} ?floor=${num?floor} ?ceiling=${num?ceiling}
</#list>

<#list userList as user>
用户名：${user.username}，密码：${user.password}，年龄: ${user.age!"20"}
    <#if !user_has_next>共有${userList?size}，最后一个用户是:${user.username}</#if>
</#list>
#排序
<#list userList?sort_by("username") as user>
用户名：${user.username}，密码：${user.password}，年龄: ${user.age!"10"}
</#list>
#FreeMarker遍历list并按用户年龄降序排序
<#list userList?sort_by("username")?reverse as user>
用户名：${user.username}密码：${user.password}年龄: ${user.age!"10"}
</#list>

#FreeMarker遍历list当用户年龄大于21岁时，停止输出，list中应用break:

#定义宏
<#macro but name>
    <a href="hello.html">${name}</a>
</#macro>

<@but name="点击我"/>
</body>
</html>