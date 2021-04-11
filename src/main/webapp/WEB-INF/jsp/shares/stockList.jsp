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
<head>
    <title>Title</title>
</head>
<body>
<div class="panel panel-primary">
    <div class="panel-heading">
        <div class="row">
            <div class="col-md-6">
                <h4 style="font-weight: 400;width: auto">当前位置:[${onelevel}]>>[${twolevel}]</h4>
            </div>
            <div class="col-md-4">
            </div>
            <div class="col-md-2">
                <a href="/stock/new0" class="btn btn-success" style="margin-left: 50%;" role="button">新建</a>
            </div>
        </div>

    </div>
    <div class="panel-body">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>序号</th>
                <th>股票名称</th>
                <th>股票代码</th>
                <th>行业</th>
                <th>利润</th>
                <th>市值</th>
                <th>基本操作</th>
            </thead>
            <tbody>
            <c:forEach items="${shareData}" var="shares">
            <tr class="active">
                <td>${shares.id}</td>
                <td>${shares.stockName}</td>
                <td>${shares.stockCode}</td>
                <td>${shares.industry}</td>
                <td>${shares.profit}</td>
                <td>${shares.holdShare}</td>
                <td>
                    <div class="btn-group">
                        <button type="button" class="btn btn-primary" style="margin-right: 5px;"
                                onclick="getDetail(${shares.id})">详情
                        </button>&nbsp;&nbsp;
                        <button type="button" class="btn btn-success" style="margin-right: 5px;"
                                onclick="getTradeData('${shares.stockCode}')">提取交易数据
                        </button>&nbsp;&nbsp;
                        <button type="button" class="btn btn-warning" style="margin-right: 5px;"
                                onclick="showTradeData('${shares.stockCode}')">显示交易
                        </button>&nbsp;&nbsp;
                    </div>
                </td>
                </c:forEach>
            </tbody>
        </table>
        <div class="row">
            <div class="col-md-12">
                <ul class="pagination pagination-lg">
                    <li><a href="#" class="active">&laquo;</a></li>
                    <c:if test="${totalRecord > 0}">
                        <c:forEach var="i" begin="1" end="${pageCount}" step="1">
                            <li><a href="/stock/getAllStocks?onelevel=股票查询&twolevel=股票列表&pageIndex=${i}">${i} </a></li>
                        </c:forEach>
                    </c:if>
                    <li><a href="#">&raquo;</a></li>&nbsp;&nbsp;
                    <li><a>共&nbsp;${totalRecord}&nbsp;条</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>

</body>
<script>

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

    //抽取股票交易数据
    function getTradeData(stockCode) {
        window.location.href = "/stock/tradeData/" + stockCode;
    }

    function showTradeData(stockCode) {
        window.location.href = "/stock/showTradeData/" + stockCode;
    }

</script>
</html>
