<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
    <h1 class="text-center text-info" style="margin-top: 15px">Thêm Chuyến Xe</h1>

    <c:url value="/admin/trips/add_trip" var="action" />
    <form:form method="post" action="${action}" modelAttribute="trip">
        <div class="form-floating mb-3 mt-3">
            <form:input class="form-control" path="name" id="name"/>
            <form:errors path="name" element="div" cssClass="text-danger" />
            <label for="name">Tên chuyến đi:</label>
        </div>
        <div class="form-floating mb-3 mt-3">
            <form:input type="datetime-local" class="form-control" path="startTime" id="startTime"/>
            <form:errors path="startTime" element="div" cssClass="text-danger" />
            <label for="startTime">Thời gian xuất phát:</label>
        </div>
        <div class="form-floating mt-3 mb-3">
            <form:input class="form-control" path="price" id="price"/>
            <form:errors path="price" element="div" cssClass="text-danger" />
            <label for="price">Giá</label>
        </div>
        <div class="form-floating mt-3 mb-3">
            <form:select path="idRoute" class="form-select" id="route" name="routelist">
                <c:forEach items="${routes}" var="r">
                    <option value="${r.id}">${r.start} - ${r.end}</option>
                </c:forEach>
            </form:select>
            <label for="sel1" class="form-label">Tuyến xe:</label>
        </div>
        <div class="form-floating mt-3 mb-3">
            <form:select path="idDriver" class="form-select" id="driver" name="driverlist">
                <c:forEach items="${userDriver}" var="d">
                    <option value="${d.id}">${d.fullname}</option>
                </c:forEach>
            </form:select>
            <label for="driver" class="form-label">Tài xế:</label>
        </div>
        <div class="form-floating mt-3 mb-3">
            <form:select path="idCoach" class="form-select" id="coach" name="coachlist">
                <c:forEach items="${coaches}" var="c">
                    <option value="${c.id}">${c.name} - ${c.totalseat} chỗ - ${c.licensePlates}</option>
                </c:forEach>
            </form:select>
            <label for="coach" class="form-label">Xe:</label>
        </div>

        <div class="col-auto">
            <button type="submit" class="btn btn-primary">Submit</button>
        </div>   
    </form:form>
</div>
