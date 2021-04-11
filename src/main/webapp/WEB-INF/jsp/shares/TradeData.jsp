<!DOCTYPE html>
<html lang="en">
<%@ include file="../jsfile.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="/static/css/share/a4.css" type="text/css" rel="stylesheet"/>
<link href="/static/css/share/shareReport.css" type="text/css" rel="stylesheet"/>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<head>
    <title>交易详情</title>
    <style type="text/css">

    </style>
</head>
<body>
<c:set var="softNo" value="0"></c:set>

<div class="book" style="margin-top: 20px;">

    <div class="page">
        <h2 align="left" style="color: #ff5235;">${data.stockName}(${data.stockCode})</h2><br>

        <div class="row">
            <div class="col-md-12"
                 style="background-color: #eee7ed;">
                <%--<c:if test="${data.newestPrice > data.yesPrice}">--%>

                <%--</c:if>--%>
                <div class="row">
                    <div class="col-md-1" align="left">
                        <c:choose>
                            <c:when test="${data.newestPrice > data.yesPrice}"><h4 align="left"
                                                                                   style="color: #ff5235">${data.newestPrice}↑</h4></c:when>
                            <c:otherwise><h4 align="left" style="color: #10ff06">${data.newestPrice}↓</h4></c:otherwise>
                        </c:choose>
                    </div>
                    <div class="col-md-3" align="left">
                        <c:choose>
                            <c:when test="${data.priceChange > 0}"><h4 align="left"
                                                                       style="color: #ff5235">
                                涨跌:${data.priceChange}</h4></c:when>
                            <c:otherwise><h4 align="left" style="color: #10ff06">
                                涨跌:${data.priceChange}</h4></c:otherwise>
                        </c:choose>
                        <%--<h4 align="center">${data.priceChange}</h4>--%>
                    </div>
                    <div class="col-md-3" align="left">

                        <c:choose>
                            <c:when test="${fn:startsWith(data.priceChangeRange,'-')}"><h4 align="left"
                                                                                           style="color: #10ff06">
                                涨跌幅:${data.priceChangeRange}</h4></c:when>
                            <c:otherwise><h4 align="left" style="color: #ff5235">
                                涨跌幅:${data.priceChangeRange}</h4></c:otherwise>
                        </c:choose>

                    </div>

                    <%--<div class="col-md-2">--%>
                    <%--<h4 align="left">总股本:${data.totalStocks}</h4>--%>
                    <%--</div>--%>
                    <%--<div class="col-md-2">--%>
                    <%--<h4 align="left">流通股本:${data.circulateStocks}</h4>--%>
                    <%--</div>--%>
                </div>

            </div>
            <div class="col-md-12"
                 style="background-color: #ffffff;box-shadow: inset 1px -1px 1px #ffffff, inset -1px 1px 1px #ffffff;">
                <div class="row">

                    <table class="table" border="0" cellspacing="0">

                        <tr>
                            <td align="left" width="13%"><c:out value="今开:${data.newestPrice}"></c:out></td>
                            <td align="left" width="25%" style="color: #ff1815">最高:${data.maxPrice}</td>
                            <td align="left" width="26%">振幅:${data.amplitude}</td>
                            <td align="left" width="26%">换手率:${data.changeHands}</td>

                        </tr>
                        <tr>
                            <td align="left">昨收:${data.yesPrice}</td>
                            <td align="left" style="color: #00ee00">最低:${data.minPrice}</td>
                            <td align="left">成交量:${data.tradeHands}</td>
                            <td align="left">成交额:${data.tradeSumMoney}</td>
                        </tr>
                        <tr>
                            <td align="left">市盈率:${data.peRatio}</td>
                            <td align="left">扣除后市盈率:${data.peRatioAfterDeduction}</td>
                            <td align="left">平均市盈率:${data.avgPeRatio}</td>
                            <td align="left" style="color: #0066FF">总股本:${data.totalStocks}</td>

                        </tr>
                        <tr>
                            <td align="left">市净率:${data.pbRatio}</td>
                            <td align="left">每股收益：${data.earningsPerShare}</td>
                            <td align="left">扣除后平均市盈率:${data.avgPeRatioAfterDeduction}</td>
                            <td align="left" style="color: #0066FF">流通股本:${data.circulateStocks}</td>

                        </tr>

                    </table>

                </div>
            </div>
            <%--第二行--%>
            <div class="row"
                 style="background-color: #eee7ed;box-shadow: inset 1px -1px 1px #eee7ed, inset -1px 1px 1px #eee7ed;">
                <div class="col-md-12"><h4 align="left">所属行业: ${data.industry}</h4></div>
            </div>

        </div>
        <%--    行业排名--%>
        <br>

        <div class="row">
            <div class="panel panel-danger">
                <div class="panel-heading">${data.industry}-涨幅前八名</div>
                <table class="table">
                    <th>证券名</th>
                    <th>现价</th>
                    <th>涨跌</th>
                    <th>涨跌幅</th>
                    <c:forEach items="${tradeModel}" var="up">
                        <c:if test="${up.mark eq '1'}">
                            <tr>
                                <td>${up.stockName}</td>
                                <td>${up.currentPrice}</td>
                                <td style="color: red">${up.changePrice}</td>
                                <td style="color: red">${up.upAndDownRange}</td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </table>
            </div>
        </div>
        <div class="row">
            <div class="panel panel-success">
                <div class="panel-heading">${data.industry}-跌幅前八名</div>
                <table class="table">
                    <th>证券名</th>
                    <th>现价</th>
                    <th>涨跌</th>
                    <th>涨跌幅</th>
                    <c:forEach items="${tradeModel}" var="down">
                        <c:if test="${down.mark eq '2'}">
                            <tr>
                                <td>${down.stockName}</td>
                                <td>${down.currentPrice}</td>
                                <td style="color: green">${down.changePrice}</td>
                                <td style="color: green">${down.upAndDownRange}</td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>

</div>
</body>
</html>