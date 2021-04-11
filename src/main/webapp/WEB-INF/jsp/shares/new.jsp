<%--
  Created by IntelliJ IDEA.
  User: zhangpengfei
  Date: 2021/1/25
  Time: 20:46
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../jsfile.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style type="text/css">
   .td_s{width: 250px;}
   .td_s1{width: 600px;}
    .text_s{width: 600px;height: 300px;}
    select{width: 250px;}
</style>
<script type="text/javascript">


//    $('#listDate').datetimepicker({
//        language: 'zh-CN',//显示中文
//        format: 'yyyy-mm-dd',//显示格式
//        minView: "month",//设置只显示到月份
//        initialDate: new Date(),//初始化当前日期
//        autoclose: true,//选中自动关闭
//        todayBtn: true//显示今日按钮
//    });
    $(function () {
        $('#listDate').datetimepicker({
            format: 'YYYY-MM-DD',
            locale: ('en')
        });
//        $('#datetimepicker2').datetimepicker({
//            format: 'YYYY-MM-DD hh:mm',
//            locale: moment.locale('zh-cn')
//        });
    });

</script>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/stock/saveBaseInfo" class="form-horizontal" role="form" method="POST" validate="true"
      modelAttribute="shares" cssClass="basic-form form-horizontal">
    <div class="form-group">
        <label for="entName" class="col-md-2 control-label">企业名称:</label>
        <div class="col-md-10">
            <input type="text" class="form-control td_s" id="entName" name="entName" placeholder="企业名称">
        </div>
    </div>
    <div class="form-group">
        <label for="englishName" class="col-md-2 control-label">英文名称:</label>
        <div class="col-md-10">
            <input type="text" class="form-control td_s" id="englishName" name="englishName" placeholder="英文名称">
        </div>
    </div>
    <div class="form-group">
        <label for="stockName" class="col-md-2 control-label">股票名:</label>
        <div class="col-md-10">
            <input type="text" class="form-control td_s" id="stockName" name="stockName" placeholder="股票名">
        </div>
    </div>
    <div class="form-group">
        <label for="stockCode" class="col-md-2 control-label">证券代码:</label>
        <div class="col-md-10">
            <input type="text" class="form-control td_s" id="stockCode" name="stockCode" placeholder="证券代码">
        </div>
    </div>

    <div class="form-group">
        <label for="stockType" class="col-md-2 control-label">证券类型:</label>
        <div class="col-md-10">
            <select class="form-control td_s" name="stockType" id="stockType">
                <option value="">--请选择--</option>
                <c:forEach var="stocktypes" items="${dicstockType}">
                    <option value="${stocktypes.code}">${stocktypes.nameZh}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label for="listDate" class="col-md-2 control-label">上市日期:</label>
        <div class="col-md-10" >
            <input type="text" class="form-control td_s" id="listDate" name="listDate" placeholder="上市日期">
        </div>
    </div>
    <div class="form-group">
        <label for="industry" class="col-md-2 control-label">所属行业:</label>
        <div class="col-md-10" >
            <input type="text" class="form-control td_s1" id="industry" name="industry" placeholder="所属行业">
        </div>
    </div>

    <div class="form-group">
        <label for="registeredCapital" class="col-md-2 control-label">注册资本:</label>
        <div class="col-md-10" >
            <input type="text" class="form-control td_s" id="registeredCapital" name="registeredCapital" placeholder="注册资本">
        </div>
    </div>

    <div class="form-group">
        <label for="tradableShares" class="col-md-2 control-label">流通股本:</label>
        <div class="col-md-10" >
            <input type="text" class="form-control td_s" id="tradableShares" name="tradableShares" placeholder="流通股本">
        </div>
    </div>
    <div class="form-group">
        <label for="holdShare" class="col-md-2 control-label">持股份额:</label>
        <div class="col-md-10" >
            <input type="text" class="form-control td_s" id="holdShare" name="holdShare" placeholder="持股份额">
        </div>
    </div>
    <div class="form-group">
        <label for="profit" class="col-md-2 control-label">利润:</label>
        <div class="col-md-10" >
            <input type="text" class="form-control td_s" id="profit" name="profit" placeholder="利润">
        </div>
    </div>
    <div class="form-group">
        <label for="shareBonus" class="col-md-2 control-label">红利:</label>
        <div class="col-md-10" >
            <input type="text" class="form-control td_s" id="shareBonus" name="shareBonus" placeholder="红利">
        </div>
    </div>
    <div class="form-group">
        <label  class="col-md-2 control-label">是否分红:</label>
        <div class="col-md-10" >
            <input type="radio"  name="bonusResult" value="已分红">已分红&nbsp;
            <input type="radio"  name="bonusResult" value="未分红">未分红
        </div>
    </div>
    <div class="form-group">
        <label for="businessScope" class="col-md-2 control-label">营业范围:</label>
        <div class="col-md-10">
        <textarea class="form-control text_s" rows="3" id="businessScope" name="businessScope" placeholder="营业范围"></textarea>
        </div>
    </div>
    <div class="form-group">
        <label for="mainBusiness" class="col-md-2 control-label">主营业务:</label>
        <div class="col-md-10">
            <textarea class="form-control text_s" rows="3" id="mainBusiness" name="mainBusiness" placeholder="主营业务"></textarea>
        </div>
    </div>
    <div class="form-group">
        <label for="entBackground" class="col-md-2 control-label">企业背景:</label>
        <div class="col-md-10">
            <textarea class="form-control text_s" rows="3" id="entBackground" name="entBackground" placeholder="企业背景"></textarea>
        </div>
    </div>

    <div class="form-group">
        <label for="stockInfo" class="col-md-2 control-label">重要信息</label>
        <div class="col-md-10">
            <textarea class="form-control text_s" rows="3" id="stockInfo" name="stockInfo" placeholder="重要信息"></textarea>
        </div>
    </div>
    <div class="form-group">
        <div class="col-md-offset-2 col-md-10">
            <button type="submit" class="btn btn-primary">保存</button>
        </div>
    </div>
</form>
</body>
</html>
