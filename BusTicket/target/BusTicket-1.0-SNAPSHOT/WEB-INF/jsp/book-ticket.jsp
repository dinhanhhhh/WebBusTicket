<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="container">
    <h1 class="text-center text-info" style="margin-top: 15px">Đặt Vé ${tripT.idRoute.start} - ${tripT.idRoute.end}</h1>
    <div>
        <h4>Thông Tin Chuyến Xe</h4>
        <div class="row g-3">
            <div class="form-floating col-md-6 mb-3">
                <input type="text" class="form-control" placeholder="Tài xế" value="${tripT.idDriver.fullname}" disabled/>
                <label>Tài xế</label>
            </div>
            <div class="form-floating col-md-6 mb-3">
                <input type="text" class="form-control" placeholder="Biển số xe" value="${tripT.idCoach.licensePlates}" disabled/>
                <label>Biển số xe</label>
            </div>
            <div class="form-floating col-md-3 mb-3">
                <input type="text" class="form-control" placeholder="Điểm xuất phát" value="${tripT.idRoute.start}" disabled/>
                <label>Điểm xuất phát</label>
            </div>
            <div class="form-floating col-md-3 mb-3">
                <input type="text" class="form-control" placeholder="Điểm đến" value="${tripT.idRoute.end}" disabled/>
                <label>Điểm đến</label>
            </div>
            <div class="form-floating col-md-3 mb-3">
                <input type="text" class="form-control" placeholder="Ngày xuất phát" value="<fmt:formatDate value="${tripT.startTime}" pattern="dd-MM-yyyy"/>" disabled/>
                <label>Ngày xuất phát</label>
            </div>
            <div class="form-floating col-md-3 mb-3">
                <input type="text" class="form-control" placeholder="Ngày xuất phát" value="<fmt:formatDate value="${tripT.startTime}" pattern="HH:mm aa"/>" disabled/>
                <label>Thời gian xuất phát</label>
            </div>
        </div>
    </div>
    <div>
        <form method="post" action="">
            <div class="row g-3">
                <sec:authorize access="hasRole('ROLE_EMPLOYEE')">
                    <h4>Thông Tin Nhân Viên</h4>
                    <div class="form-floating col-md-4 mb-3">
                        <input type="text" class="form-control" id="fullnameE" path="fullname" placeholder="Full name" disabled/>
                        <label for="fullname">Full name</label>
                    </div>
                    <div class="form-floating col-md-4 mb-3">
                        <select class="form-select" aria-label=".form-select-lg example" id="genderE" name="gender" path="gender" disabled>
                            <option selected value="Nam">Nam</option>
                            <option value="Nu">Nu</option>
                        </select>
                        <label for="roll" class="form-label">Gender:</label>
                    </div>
                    <div class="form-floating col-md-4 mb-3">
                        <input type="text" class="form-control" id="addressE" path="address" placeholder="Address" disabled/>
                        <label for="address">Address</label>
                    </div>
                    <div class="form-floating col-md-6 mb-3">
                        <input type="text" class="form-control" id="phoneE" path="phone" placeholder="Phone" disabled/>
                        <label for="phone">Phone</label>
                    </div>
                    <div class="form-floating col-md-6 mb-3">
                        <input type="email" class="form-control" id="emailE" path="email" placeholder="name@example.com" disabled/>
                        <label for="email">Email</label>
                    </div>
                </sec:authorize>
                <h4>Thông Tin Khách Hàng </h4>    
                <div class="form-floating col-md-4 mb-3">
                    <input type="text" class="form-control" id="fullnameCustomer" path="fullname" placeholder="Full name"/>
                    <label for="fullname">Full name</label>
                </div>
                <div class="form-floating col-md-4 mb-3">
                    <select class="form-select" aria-label=".form-select-lg example" id="genderCustomer" name="genderCustomer" path="gender">
                        <option selected value="Nam">Nam</option>
                        <option value="Nu">Nu</option>
                    </select>
                    <label for="roll" class="form-label">Gender:</label>
                </div>
                <div class="form-floating col-md-4 mb-3">
                    <input type="text" class="form-control" id="addressCustomer" path="address" placeholder="Address"/>
                    <label for="address">Address</label>
                </div>
                <div class="form-floating col-md-6 mb-3">
                    <input type="text" class="form-control" id="phoneCustomer" path="phone" placeholder="Phone"/>
                    <label for="phone">Phone</label>
                </div>
                <div class="form-floating col-md-6 mb-3">
                    <input type="email" class="form-control" id="emailCustomer" path="email" placeholder="name@example.com"/>
                    <label for="email">Email</label>
                </div>


                <div class="col-md-6 mb-3">
                    <h4>Chọn lựa ghế ngồi (<fmt:formatNumber type="number" value="${tripT.price}" maxFractionDigits="3"/> VNÐ/ghế)</h4>
                    <div id="loadSeats">
                        <div id="loadSeats">
                            <c:forEach items="${seatsList}" var="seat">
                                <c:set var="seatIsReserved" value="false" />
                                <c:forEach items="${ticketDetail}" var="r">
                                    <c:choose>
                                        <c:when test="${r.statusSeat and r.idSeat.name eq seat.name}">
                                            <c:set var="seatIsReserved" value="true" />
                                        </c:when>
                                    </c:choose>
                                </c:forEach>
                                <c:choose>
                                    <c:when test="${seatIsReserved eq 'true'}">
                                        <!-- Hiển thị các ghế đã đặt trước và vô hiệu hóa chúng -->
                                        <input type="checkbox" name="seats" value="${seat.id}" disabled> ${seat.name}
                                    </c:when>
                                    <c:otherwise>
                                        <!-- Hiển thị các ghế chưa đặt trước -->
                                        <input type="checkbox" name="seats" value="${seat.id}"> ${seat.name}
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="col-md-6 mb-3">
                        <h4>Các ghế đã được đặt trước</h4>
                        <div class="row g-3">
                            <c:forEach items="${ticketDetail}" var="r">
                                <c:choose>
                                    <c:when test="${r.statusSeat}">
                                        <div class="col-sm-3">
                                            <input type="checkbox" name="seats" checked disabled> ${r.idSeat.name} by ${r.idTicket.idCustomerNew.fullname}
                                        </div>
                                    </c:when>
                                </c:choose>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="form-floating col-md-12 mb-3">
                        <select class="form-select" aria-label=".form-select-lg example" id="paymentMethod" name="paymentMethod" path="paymentMethod">
                            <option selected value="Trực tiếp">Trực tiếp</option>
                            <c:if test="${currentUser.id == null}">
                                <option value="MoMo" data-toggle="tooltip" data-placement="right" title="Bạn cần phải đăng nhập tài khoản!" disabled>MoMo</option>
                                <option value="ZaloPay" data-toggle="tooltip" data-placement="right" title="Bạn cần phải đăng nhập tài khoản!" disabled>ZaloPay</option>
                            </c:if>
                            <c:if test="${currentUser.id != null}">
                                <option value="MoMo">MoMo</option>
                                <option value="ZaloPay">ZaloPay</option>
                            </c:if>
                        </select>
                        <label for="roll" class="form-label">Phương thức thanh toán</label>
                    </div>
                    <sec:authorize access="!isAuthenticated()">
                        <div class="col-auto">
                            <button type="button" class="btn btn-primary" onclick="addTicket(${tripT.id})">Submit</button>
                        </div> 
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                        <div class="col-auto">
                            <button type="button" class="btn btn-primary" onclick="addTicket(${tripT.id}, ${currentUser.id})">Submit</button>
                        </div> 
                    </sec:authorize>
                </div>
        </form>
    </div>
</div>
<script src="<c:url value="/js/ticket.js" />"></script>
<script>
                            window.onload = function () {
                                getSeats('${idCoach.id}');
                                loadUserEmployee('${currentUser.id}');
                                loadUser('${currentUser.id}');
                            };
</script>