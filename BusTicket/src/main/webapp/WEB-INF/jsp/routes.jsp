<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="container">
    <h1 class="text-center text-info" style="margin-top: 15px">QUẢN LÝ TUYẾN XE</h1>
    <div class="d-flex align-items-center" >
        <strong id="mySpinner" >Loading...</strong>
        <div class="spinner-border ms-5" id="mySpinner2"></div>
    </div>

    <c:url value="/admin/routes" var="action" />
    <form:form method="post" action="${action}" modelAttribute="route">
        <div class="row g-3">
            <div class="form-floating col-md-6 mb-3">
                <form:input type="text" class="form-control" id="start" path="start" placeholder="Điểm xuất phát"/>
                <form:errors path="start" element="div" cssClass="text-danger" />
                <label for="floatingInput">Điểm xuất phát</label>
            </div>
            <div class="form-floating col-md-6 mb-3">
                <form:input type="text" class="form-control" id="end" path="end" placeholder="Điểm đến"/>
                <form:errors path="end" element="div" cssClass="text-danger" />
                <label for="floatingPassword">Điểm đến</label>
            </div>
        </div>
        <div class="row">
            <div class="col-auto">
                <button id="addR" class="btn btn-primary">Add</button>
            </div>
        </div> 
    </form:form> 

    <table class="table table-hover caption-top">
        <caption>Danh sách tuyến xe</caption>
        <thead>
            <tr>
                <th>STT</th>
                <th>Điểm xuất phát</th>
                <th>Điểm đến</th>
                <th></th>
            </tr>
        </thead>
        <tbody id="myRoutes">

        </tbody>
    </table>

    <!-- The Modal -->
    <div class="modal" id="myModalEditRoute">
        <div class="modal-dialog">
            <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">Edit Route</h4>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>

                <!-- Modal body -->
                <div class="modal-body">
                    <div class="row g-3">
                        <div class="form-floating col-md-4 mb-3">
                            <input type="text" class="form-control" id="start1" path="start" placeholder="Điểm xuất phát"/>
                            <label for="floatingInput">Điểm xuất phát</label>
                        </div>
                        <div class="form-floating col-md-4 mb-3">
                            <input type="text" class="form-control" id="end1" path="end" placeholder="Điểm đến"/>
                            <label for="floatingTotalSeat">Điểm đến</label>
                        </div>
                    </div>
                </div>

                <!-- Modal footer -->
                <div class="modal-footer">
                    <button id="editR" class="btn btn-primary">
                        Edit
                    </button>
                    <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
                </div>

            </div>
        </div>
    </div>
</div>

<script src="<c:url value="/js/route.js" />"></script>
<script>
    <c:url value="/api/routes" var="r"/>
    window.onload = function () {
        getRoutes('${r}');
    }
</script>