<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="text-center text-info">THỐNG KÊ SỐ CHUYẾN XE THEO TUYẾN XE</h1>
<div class="row">
    <div class="col-md-5" style="margin-left: 25px"> 
        <table class="table">
            <tr>
                <th>ID</th>
                <th>Tên Tuyến Xe</th>
                <th>Số Chuyến Xe</th>
            </tr>
            <c:forEach items="${stats}" var="c">
                <tr>
                    <td>${c[0]}</td>
                    <td>${c[1]}-${c[2]}</td>
                    <td>${c[3]}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div class="col-md-7" style="width: 35%">
        <canvas id="myChart"></canvas>
    </div>
    <script src="<c:url value="/js/stats.js/" />"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script>
        let data = [];
        let labels=[];
         <c:forEach items="${stats}" var="c">
               data.push(${c[3]});
               labels.push('${c[1]}'+ '-' +'${c[2]}' )
            </c:forEach>
        window.onload = function() {
            routeStats(data, labels);
}
    </script>
