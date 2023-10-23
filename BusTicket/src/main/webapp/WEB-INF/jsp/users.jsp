<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="container">
    <h1 class="text-center text-info" style="margin-top: 15px">QUẢN LÝ NHÂN VIÊN</h1>

    <button class="cssbuttons-io-button" type="button" onclick="location.href = '<c:url value="/admin/users/add_user"/>'">
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24"><path fill="none" d="M0 0h24v24H0z"></path><path fill="currentColor" d="M11 11V5h2v6h6v2h-6v6h-2v-6H5v-2z"></path></svg>
        <span> Add </span>
    </button>

    <div class="d-flex align-items-center" >
        <strong id="mySpinner" >Loading...</strong>
        <div class="spinner-border ms-5" id="mySpinner2"></div>
    </div>

    <table class="table table-hover caption-top">
        <caption>Danh sach nhan vien</caption>
        <thead>
            <tr>
                <th>STT</th>
                <th>Avatar</th>
                <th>Username</th>
                <th>Full Name</th>
                <th>Birthday</th>
                <th>Phone</th>
                <th>Email</th>
                <th>Role</th>
                <th></th>
            </tr>
        </thead>
        <tbody id="myUser">

        </tbody>
    </table>

    <!-- The Modal -->
    <div class="modal" id="myModalEditUser">
        <div class="modal-dialog modal-xl">
            <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">Edit User</h4>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>

                <!-- Modal body -->
                <div class="modal-body">
                    <c:url value="/api/users/editUser" var="action" />
                    <form:form method="post" action="${action}" enctype="multipart/form-data">
                        <div class="row g-3">
                            <div class="form-floating col-md-4 mb-3">
                                <input type="text" class="form-control" id="fullname" path="fullname" placeholder="Full name"/>
                                <label for="fullname">Full name</label>
                            </div>
                            <!--                        <div class="form-floating col-md-4 mb-3">
                                                        <div class="form-check form-check-inline">
                                                            <input type="radio" class="form-check-input" id="gender" name="gender" path="gender" value="Nam">Nam
                                                                <label class="form-check-label" for="inlineRadio1"></label>
                                                        </div>
                                                        <div class="form-check form-check-inline">
                                                            <input type="radio" class="form-check-input" id="genderNu" name="gender" path="gender" value="Nu">Nu
                                                                <label class="form-check-label" for="inlineRadio2"></label>
                                                        </div>
                                                    </div>-->
                            <div class="form-floating col-md-4 mb-3">
                                <select class="form-select" aria-label=".form-select-lg example" id="gender" name="gender" path="gender">
                                    <option selected value="Nam">Nam</option>
                                    <option value="Nu">Nu</option>
                                </select>
                                <label for="roll" class="form-label">Gender</label>
                            </div>
                            <div class="form-floating col-md-4 mb-3">
                                <select class="form-select" aria-label=".form-select-lg example" id="userRole" name="userRole" path="userRole">
                                    <option selected value="ROLE_EMPLOYEE">Employee</option>
                                    <option value="ROLE_DRIVER">Driver</option>
                                    <option value="ROLE_CUSTOMER">Customer</option>
                                    <option value="ROLE_ADMIN">Admin</option>
                                </select>
                                <label for="roll" class="form-label">Role</label>
                            </div>
                            <div class="form-floating col-md-4 mb-3">
                                <input type="date" class="form-control" id="birthday" path="birthday" placeholder="Birthday"/>
                                <label for="birthday">Birthday</label>
                            </div>
                            <div class="form-floating col-md-4 mb-3">
                                <input type="text" class="form-control" id="address" path="address" placeholder="Address"/>
                                <label for="address">Address</label>
                            </div>
                            <div class="form-floating col-md-4 mb-3">
                                <input type="text" class="form-control" id="phone" path="phone" placeholder="Phone"/>
                                <label for="phone">Phone</label>
                            </div>
                            <div class="form-floating col-md-6 mb-3">
                                <input type="email" class="form-control" id="email" path="email" placeholder="name@example.com"/>
                                <label for="email">Email</label>
                            </div>
                            <div class="form-floating col-md-6 mb-3">
                                <input type="text" class="form-control" id="username" path="username" placeholder="User name" disabled/>
                                <label for="username">User name</label>
                            </div>    
                            <div class="form-floating col-md-6 mb-3">
                                <input type="password" class="form-control" id="password" path="password" placeholder="Password" disabled/>
                                <label for="password">Password</label>
                            </div>
                            <div class="form-floating col-md-6 mb-3">
                                <input type="password" class="form-control" id="confirmPassword" path="confirmPassword" placeholder="Confirm Password" disabled/>
                                <label for="password">Confirm Password</label>
                            </div>
                            <div class="form-floating col-md-6 mb-3">
                                <input type="file" class="form-control" id="fileUploadAvatar" name="fileUploadAvatar" path="file" disabled/>
                            </div>
                            <div class="form-floating col-md-6 mb-3">
                                <img id="avatar" style="width: 45%"/>
                            </div>
                        </div>
                    </form:form>
                </div>

                <!-- Modal footer -->
                <div class="modal-footer">
                    <button id="editU" class="btn btn-primary">
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
<script src="<c:url value="/js/user.js" />"></script>
<script>
    <c:url value="/api/users" var="u"/>
        window.onload = function () {
            getUsers('${u}');
        };
</script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script type="text/javascript">
        $(document).ready(function () {
            $('#fileUploadAvatar').change(function () {
                showImageChooseFile(this);
            });
        });

        function showImageChooseFile(fileInput) {
            var file = fileInput.files[0];
            var reader = new FileReader();

            reader.onload = function (e) {
                $('#avatar').attr('src', e.target.result);
            };

            reader.readAsDataURL(file);
        }
</script>