<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="container">

    <h1 class="text-center text-info" style="margin-top: 15px">QUẢN LÝ CHUYẾN XE</h1>

    <button class="cssbuttons-io-button" type="button" onclick="location.href = '<c:url value="/admin/trips/add_trip"/>'">
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24"><path fill="none" d="M0 0h24v24H0z"></path><path fill="currentColor" d="M11 11V5h2v6h6v2h-6v6h-2v-6H5v-2z"></path></svg>
        <span> Add </span>
    </button>

    <div class="spinner-border text-info" id="myLoading" role="status">
        <span class="visually-hidden">Loading...</span>
    </div>

    <table class="table table-hover caption-top">
        <caption>Danh sách chuyến xe</caption>
        <thead>
            <tr>
                <th>STT</th>
                <th>Image</th>
                <th>Tên</th>
                <th>Điểm xuất phát</th>
                <th>Điểm đến</th>
                <th>Thời gian đi</th>
                <th>Giá vé</th>
                <th>Tài xế</th>
                <th>Biển số xe</th>
                <th></th>
            </tr>
        </thead>
        <tbody id="adminTrip">

        </tbody>
    </table>

    <!-- The Modal -->
    <div class="modal" id="myModalEditTrip">
        <div class="modal-dialog">
            <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">Edit Trip</h4>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>

                <!-- Modal body -->
                <div class="modal-body">
                    <div class="row g-3">
                        <div class="form-floating mb-3 mt-3">
                            <input class="form-control" path="name" id="name"/>
                            <label for="name">Tên chuyến đi:</label>
                        </div>
                        <div class="form-floating mb-3 mt-3">
                            <input type="datetime-local" class="form-control" path="startTime" id="startTime"/>
                            <label for="startTime">Thời gian xuất phát:</label>
                        </div>
                        <div class="form-floating mt-3 mb-3">
                            <input class="form-control" path="price" id="price"/>
                            <label for="price">Giá</label>
                        </div>
                        <div class="form-floating mt-3 mb-3">
                            <select path="idRoute" class="form-select" id="route" name="routelist">
                                <c:forEach items="${routes}" var="r">
                                    <option value="${r.id}">${r.start} - ${r.end}</option>
                                </c:forEach>
                            </select>
                            <label for="sel1" class="form-label">Tuyến xe:</label>
                        </div>
                        <div class="form-floating mt-3 mb-3">
                            <select path="idDriver" class="form-select" id="driver" name="driverlist">
                                <c:forEach items="${userDriver}" var="d">
                                    <option value="${d.id}">${d.fullname}</option>
                                </c:forEach>
                            </select>
                            <label for="driver" class="form-label">Tài xế:</label>
                        </div>
                        <div class="form-floating mt-3 mb-3">
                            <select path="idCoach" class="form-select" id="coach" name="coachlist">
                                <c:forEach items="${coaches}" var="c">
                                    <option value="${c.id}">${c.name} - ${c.totalseat} chỗ - ${c.licensePlates}</option>
                                </c:forEach>
                            </select>
                            <label for="coach" class="form-label">Xe:</label>
                        </div> 
                    </div>
                </div>

                <!-- Modal footer -->
                <div class="modal-footer">
                    <button id="editT" class="btn btn-primary">
                        Edit
                    </button>
                    <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
                </div>

            </div>
        </div>
    </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.4/moment.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.4/moment-with-locales.min.js"></script>
<script src="<c:url value="/js/trip.js" />"></script>
<script>
    <c:url value="/api/trips" var="endpoint"/>

        window.onload = function () {
            loadAdminTrips('${endpoint}');
        };
</script>