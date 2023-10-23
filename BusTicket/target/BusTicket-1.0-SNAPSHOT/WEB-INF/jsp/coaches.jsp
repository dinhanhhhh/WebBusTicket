<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="container">
    <h1 class="text-center text-info" style="margin-top: 15px">QUẢN LÝ XE</h1>
    <div class="d-flex align-items-center" >
        <strong id="mySpinner" >Loading...</strong>
        <div class="spinner-border ms-5" id="mySpinner2"></div>
    </div>


    <c:url value="/admin/coaches" var="action" />
    <form:form method="post" action="${action}" modelAttribute="coach">
        <form:errors path="*" element="div" cssClass="alert alert-danger"/>
        <div class="row g-3">
            <div class="form-floating col-md-4 mb-3">
                <form:input type="text" class="form-control" id="name" path="name" placeholder="Tên xe"/>
                <form:errors path="name" element="div" cssClass="text-danger" />
                <label for="floatingInput">Tên xe</label>
            </div>
            <div class="form-floating col-md-4 mb-3">
                <form:input type="number" class="form-control" id="totalseat" path="totalseat" placeholder="Tống số ghế"/>
                <form:errors path="totalseat" element="div" cssClass="text-danger" />
                <label for="floatingTotalSeat">Tổng số ghế</label>
            </div>
            <div class="form-floating col-md-4 mb-3">
                <form:input type="text" class="form-control" id="licensePlates" path="licensePlates" placeholder="Biển số xe"/>
                <form:errors path="licensePlates" element="div" cssClass="text-danger" />
                <label for="floatingLicensePlates">Biển số xe</label>
            </div>
        </div>
        <div class="row">
            <div class="col-auto">
                <button type="submit" class="btn btn-primary">Add</button>
            </div>
        </div>
    </form:form> 

    <table class="table table-hover caption-top">
        <caption>Danh sách xe</caption>
        <thead>
            <tr>
                <th>STT</th>
                <th>Tên xe</th>
                <th>Số ghế</th>
                <th>Biển số xe</th>
                <th></th>
            </tr>
        </thead>
        <tbody id="myCoaches">

        </tbody>
    </table>

    <!-- The Modal -->
    <div class="modal" id="myModalEditCoach">
        <div class="modal-dialog">
            <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">Edit Coach</h4>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>

                <!-- Modal body -->
                <div class="modal-body">
                    <div class="row g-3">
                        <div class="form-floating col-md-4 mb-3">
                            <input type="text" class="form-control" id="name1" path="name" placeholder="Ten xe"/>
                            <label for="floatingInput">Tên xe</label>
                        </div>
                        <div class="form-floating col-md-4 mb-3">
                            <input type="number" class="form-control" id="totalseat1" path="totalseat" placeholder="Tong so ghe" disabled/>
                            <label for="floatingTotalSeat">Tổng số ghế</label>
                        </div>
                        <div class="form-floating col-md-4 mb-3">
                            <input type="text" class="form-control" id="licensePlates1" path="licensePlates" placeholder="Bien so xe" disabled/>
                            <label for="floatingLicensePlates">Biển số xe</label>
                        </div>
                    </div>
                </div>

                <!-- Modal footer -->
                <div class="modal-footer">
                    <button id="editC" class="btn btn-primary">
                        Edit
                    </button>
                    <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
                </div>

            </div>
        </div>
    </div>
</div>

<script src="<c:url value="/js/coach.js" />"></script>
<script>
    <c:url value="/api/coaches" var="c"/>
    window.onload = function () {
        getCoaches('${c}');
    }
</script>