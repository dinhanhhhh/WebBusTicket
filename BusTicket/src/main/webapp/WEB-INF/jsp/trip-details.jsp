<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="container">
    <h1 class="text-center text-info" style="margin-top: 15px"> CHI TIẾT CHUYẾN ĐI</h1>

    <div class="row container">
        <div class="col-md-4 " >
            <img src="${id.image}" class="rounded" style="width: 70%"/>
        </div>
        <div class="col-md-4">
            <h5>Tên chuyến xe: ${id.name}</h5>
            <h5>Điểm xuất phát: ${id.idRoute.start}</h5>
            <h5>Điểm đến: ${id.idRoute.end}</h5>
            <h5>Ngày xuất phát: <fmt:formatDate value="${id.startTime}" pattern="dd-MM-yyyy"/></h5>
            <h5>Thời gian xuất phát: <fmt:formatDate value="${id.startTime}" pattern="HH:mm aa"/></h5>
            <h5>Giá vé: <fmt:formatNumber type="number" value="${id.price}" maxFractionDigits="3"/> VNÐ</h5>
            <h5>Tài xế: ${id.idDriver.fullname}</h5>
            <h5>Biển số xe: ${id.idCoach.licensePlates}</h5>
            <div class="col-auto">
                <a href="/BusTicket/trips/book_ticket/${id.id}" class="btn btn-primary">Đặt Vé Ngay</a>
            </div> 
        </div>
    </div>

    <div class="fw-bold text-danger fs-3 fs-lg-5 container"><spring:message code="chitietbinhluan.comment"/></div>

    <sec:authorize access="isAuthenticated()">
        <c:url value="/api/trips/${id.id}/feedbacks" var="endpoint"/>
        <spring:message code="chitietbinhluan.xacnhanbinhluan" var="xacnhan"/>
        <spring:message code="chitietbinhluan.binhluanthanhcong" var="thanhcong"/>
        <spring:message code="chitietbinhluan.binhluanthatbai" var="thatbai"/>
        <form class="d-flex" style="padding-left: 10%; padding-right: 25%;">
            <textarea id="comment" class="form-control me-2" placeholder="<spring:message code="chitietbinhluan.yourcomment"/>"></textarea>
            <input type="submit" class="btn btn-primary" value="<spring:message code="chitietbinhluan.gui"/>" style="height: 50px;"
                   onclick = "addFeedback('${endpoint}',${id.id}, '${xacnhan}', '${thanhcong}', '${thatbai}')"/>
        </form>
    </sec:authorize>

    <c:url value="/api/trips/${id.id}/feedbacks" var="endpoint"/>
    <ul id="feedbacks" style="padding-left: 15%; padding-right: 25%; padding-top: 5%;">

    </ul>

</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.4/moment.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.4/moment-with-locales.min.js"></script>
<script src="<c:url value="/js/trip.js" />"></script>
<script>
                       window.onload = function () {
                           loadFeedback('${endpoint}');
                       };
</script>
