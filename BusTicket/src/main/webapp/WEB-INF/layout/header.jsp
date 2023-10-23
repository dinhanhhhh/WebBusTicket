<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<nav class="navbar navbar-expand-sm">
    <div class="container-fluid">
        <a class="navbar-brand fw-bold text-lg-center text-gradient" href="/BusTicket/">
            <img style="width: 25%" src="<c:url value="/images/icons_bus.png"/>" /> Bus Ticket Online
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-end" id="collapsibleNavbar">
            <ul class="navbar-nav" style="margin: 10px">
                <li class="nav-item nav-masthead">
                    <a class="nav-link fw-bold text-lg-center " style="margin-right: 15px" href="/BusTicket/">Trang Chủ</a>
                </li>
                <sec:authorize access="!hasRole('ROLE_ADMIN') and isAuthenticated()">
                    <li class="nav-item nav-masthead">
                        <a class="nav-link fw-bold text-lg-center " style="margin-right: 15px" href="<c:url value="/tickets"/>">Quản Lý Vé Xe</a>
                    </li>                    
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <li class="nav-item nav-masthead">
                        <a class="nav-link fw-bold text-lg-center " href="<c:url value="/admin/trips"/>">Quản Lý Chuyến Xe</a>
                    </li>
                    <li class="nav-item nav-masthead">
                        <a class="nav-link fw-bold text-lg-center " href="<c:url value="/admin/routes"/>">Quản Lý Tuyến Xe</a>
                    </li>
                    <li class="nav-item nav-masthead">
                        <a class="nav-link fw-bold text-lg-center " href="<c:url value="/admin/coaches"/>">Quản Lý Xe</a>
                    </li>
                    <li class="nav-item nav-masthead">
                        <a class="nav-link fw-bold text-lg-center " href="<c:url value="/admin/users"/>">Quản Lý Nhân Viên</a>
                    </li>
                    <li class="nav-item nav-masthead">
                        <a class="nav-link fw-bold text-lg-center " style="margin-right: 40px" href="<c:url value="/admin/route-stats"/>">Thống Kê & Báo Cáo</a>
                    </li>
                </sec:authorize>

                <sec:authorize access="!isAuthenticated()">
                    <li class="nav-item active" style="padding-right: 10px">
                        <button class="button-30">
                            <a class="nav-link text-info fw-bold text-lg-center " href="<c:url value="/login"/>">
                                <i class="far fa-user"></i> Đăng nhập
                            </a>
                        </button>
                    </li>
                    <li class="nav-item active">
                        <button class="button-30">
                            <a class="nav-link text-info fw-bold text-lg-center " href="<c:url value="/register"/>">Đăng ký</a>
                        </button>
                    </li>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <li class="nav-item active" style="margin-right: 15px">
                        <a class="nav-link text-danger fw-bold text-lg-center " href="<c:url value="/"/>" data-bs-toggle="modal" data-bs-target="#myModal" data-toggle="tooltip" data-placement="right" title="Bấm để xem thông tin cá nhân">
                            <c:if test="${currentUser.avatar != null}">
                                <img style="width: 25px" src="${pageContext.session.getAttribute("currentUser").avatar}" class="rounded" />
                            </c:if>
                            <c:if test="${currentUser.avatar == null}">
                                <i class="far fa-user"></i>
                            </c:if>

                            <sec:authentication property="principal.username"/>
                        </a>
                    </li>

                    <li class="nav-item active">
                        <button class="button-30">
                            <a class="nav-link text-danger fw-bold text-lg-center " href="<c:url value="/logout"/>">Đăng xuất</a>
                        </button>
                    </li>
                </sec:authorize>
            </ul>
        </div>
    </div>

    <!-- The Modal -->
    <div class="modal fade" id="myModal">
        <div class="modal-dialog">
            <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">Xin chào ${pageContext.session.getAttribute("currentUser").fullname} </h4>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>

                <!-- Modal body -->
                <div class="modal-body">
                    Bạn đang đăng nhập với vai trò ${currentUser.userRole} 
                    <div class="col-auto" style="margin: 15px auto">
                        <button type="button" class="btn btn-primary">
                            <a class="nav-link text-white fw-bold text-lg-center " href="<c:url value="/userdetails"/>">
                                Thông Tin Cá Nhân
                            </a>
                        </button>
                    </div> 
                </div>

                <!-- Modal footer -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
                </div>

            </div>
        </div>
    </div>

</nav>