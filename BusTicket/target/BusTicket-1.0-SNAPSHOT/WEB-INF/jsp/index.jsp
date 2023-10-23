<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<img class="banner" src="<c:url value="/images/bus.jpg"/>" />

<div class="container">
    <div style="margin: 15px">
        <c:url value="/" var="action"/>
        <form action="${action}"class="d-flex" style="margin-top: 15px">
            <input class="form-control me-2" type="text"  name="kw" placeholder="Nhập từ khóa....">
            <button type="submit" class="btn btn-primary" type="button">Tìm</button>
        </form>
    </div>

    <c:if test="${trips.size() == 0}">
        <p>
            <em>
                Không có chuyến đi nào được tìm thấy !!!
            </em>
        </p>
    </c:if>

    <ul class="pagination" style="margin: 15px">        
        <li class="page-item"><a class="page-link" href="<c:url value="/"/>?page=1">1</a></li> 
        <li class="page-item"><a class="page-link" href="<c:url value="/"/>?page=2">2</a></li> 
        <li class="page-item"><a class="page-link" href="<c:url value="/"/>?page=3">3</a></li>
    </ul>

    <div class="container">
        <div class="row" style="margin-top: 10px">
            <c:forEach items="${trips}" var="t">
                <c:url value="/" var="cURL">
                    <c:param name="tripId" value="${t.id}"/>
                </c:url>
                <div class="card col-md-3" style="padding: 3px; margin: 5px 10px;" >
                    <a href="/BusTicket/trips/${t.id}">
                        <img class="card-img-top" class="img-fluid" src="<c:url value="${t.image}"/>" alt="Card image">
                    </a>
                    <div class="card-body">
                        <h4 class="card-title">${t.name}</h4>
                        <p class="card-text"> Điểm xuất phát: ${t.idRoute.start}</p>
                        <p class="card-text"> Điểm đến: ${t.idRoute.end}</p>
                        <p class="card-text"> Khởi hành vào lúc <fmt:formatDate value="${t.startTime}" pattern="HH:mm aa dd-MM-yyyy"/></p>
                        <a href="/BusTicket/trips/${t.id}" class="btn btn-info">Thông tin vé</a>
                        <a href="/BusTicket/trips/book_ticket/${t.id}" class="btn btn-primary">Đặt Vé Ngay</a>
                    </div>
                </div>                                 
            </c:forEach>
        </div>
    </div>
</div>
