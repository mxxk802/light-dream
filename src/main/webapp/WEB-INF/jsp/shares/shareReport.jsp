<%--
  Created by IntelliJ IDEA.
  User: zhangpengfei
  Date: 2020/7/1
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../jsfile.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="/static/css/share/a4.css" type="text/css" rel="stylesheet"/>
<link href="/static/css/share/shareReport.css" type="text/css" rel="stylesheet"/>
<html>
<head>
    <title>股票详情</title>
    <style type="text/css">

    </style>
</head>
<body>
<c:set var="softNo" value="0"></c:set>
<div class="btn-group module-menu">
    <button type="button" class="btn btn-success">下载word</button>
    <button type="button" class="btn btn-primary">下载pdf</button>
    <button type="button" class="btn btn-warning">下载excel</button>
    <button type="button" class="btn btn-warning">下载xml</button>
</div>
<div class="book" style="margin-top: 20px;">

    <div class="page">

        <h2 align="center" style="color: #0066FF">${doc.stockName}</h2><br>


        <div class="row  title-block">
            <div class="col-md-12">
                <div class="pull-left softNo" style="">${softNo+1}<c:set var="softNo"
                                                                         value="${softNo+1}"></c:set></div>
                <div class="pull-left module-title">${doc.entSurveyModel.moduleTitle['entSurvey']}</div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <table class="table no-border">
                    <c:forEach items="${doc.entSurveyModel.fieldName}" var="column">
                        <tr>
                            <td align="left">${column.value}</td>
                            <td align="left">${doc.entSurveyModel.data[column.key]}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
        <div class="row  title-block">
            <div class="col-md-12">
                <div class="pull-left softNo" style="">${softNo+1}<c:set var="softNo"
                                                                         value="${softNo+1}"></c:set></div>
                <div class="pull-left module-title">${doc.diagnosisResultModel.moduleTitle['diagnosisResult']}</div>
            </div>
        </div>

        <div class="row">
            <div class="" align="center">
                <%--<div id="dash-board" style="width:30%;height:400px;"></div>--%>
                <div id="radar-chart" style="width:400px;height:400px;"></div>
            </div>

        </div>


        <div id="k-line" style="width: 600px;height:400px;"></div>


        <%--<div class="subpage">--%>

        <%--</div>--%>
    </div>
</div>
</body>
<script>
    // 第二个参数可以指定前面引入的主题
    //var k_line_graph= echarts.init(document.getElementById('k-line'),'macarons');
    var reportId =${reportId};
    var radar_graph = echarts.init(document.getElementById('radar-chart'), 'macarons');

    //  var dash_graph= echarts.init(document.getElementById('dash-board'),'macarons');
    // setInterval(function(){ alert("Hello"); }, 3000);
    //雷达图
    option = {
        title: {
            text: '基础雷达图'
        },
        tooltip: {},
        legend: {
            data: ['预算分配（Allocated Budget）', '实际开销（Actual Spending）']
        },
        radar: {
            // shape: 'circle',
            name: {
                textStyle: {
                    color: '#fff',
                    backgroundColor: '#1d21ff',
                    borderRadius: 3,
                    padding: [3, 5]
                }
            },
            indicator: [
                {name: '盈利性', max: 10000},
                {name: '现金流', max: 10000},
                {name: '安全性', max: 10000},
                {name: '便宜度', max: 10000},
                {name: '成长性', max: 10000},

            ]
        },
        series: [{
            name: '股票评测',
            type: 'radar',
            // areaStyle: {normal: {}},
            data: [
                {
                    value: [8000, 4500, 5000, 5500, 6000, 6500],
                    name: '预算分配（Allocated Budget）'
                },

            ]
        }]
    };

    radar_graph.setOption(option);
    //dash_graph.setOption(dash_option);
    //k_line_graph.setOption(k_line_option);
    //$(function () { $('#collapseThree').collapse('toggle')});
    //下载word

    //下载pdf

    //下载excel
    function radar_graphToImg() {
        var radarImg = $("#radar-chart").find("canvas")[0];

        imgData = {};

        if (radarImg) {
            var radarUrl = radarImg.toDataURL();
            //radarUrl = radarUrl.substring(22);
            imgData.radarUrl = radarUrl;
        }


        $.post("/stock/createDoc/" + reportId, {imgData: JSON.stringify(imgData)},

            function (data) {
                alert("Data Loaded: " + data);
            }, "json");


//        var imgGague = $("#radar-chart canvas:first").get(0);
//        if(imgGague) {
//            var gugueUrl = imgGague.toDataURL();
//            gugueUrl = gugueUrl.substring(22);
//            //console.info(gugueUrl);
//
//        }

    }

    setTimeout(function () {
        radar_graphToImg();
    }, 3000);
</script>
</html>
