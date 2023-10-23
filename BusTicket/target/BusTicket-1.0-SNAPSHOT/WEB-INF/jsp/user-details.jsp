<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="container">
    <h1 class="text-center text-info" style="margin-top: 15px">Thông Tin Cá Nhân</h1>
    <div class="row g-3">
        <div class="form-floating col-md-4 mb-3">
            <input type="text" class="form-control" id="fullname" path="fullname" placeholder="Full name" disabled/>
            <label for="fullname">Full name</label>
        </div>
        <div class="form-floating col-md-4 mb-3">
            <select class="form-select" aria-label=".form-select-lg example" id="gender" name="gender" path="gender" disabled>
                <option selected value="Nam">Nam</option>
                <option value="Nu">Nu</option>
            </select>
            <label for="roll" class="form-label">Gender</label>
        </div>
        <div class="form-floating col-md-4 mb-3">
            <select class="form-select" aria-label=".form-select-lg example" id="userRole" name="userRole" path="userRole" disabled>
                <option selected value="ROLE_EMPLOYEE">Employee</option>
                <option value="ROLE_DRIVER">Driver</option>
                <option value="ROLE_CUSTOMER">Customer</option>
                <option value="ROLE_ADMIN">Admin</option>
            </select>
            <label for="roll" class="form-label">Role</label>
        </div>
        <div class="form-floating col-md-4 mb-3">
            <input type="date" class="form-control" id="birthday" path="birthday" placeholder="Birthday" disabled/>
            <label for="birthday">Birthday</label>
        </div>
        <div class="form-floating col-md-4 mb-3">
            <input type="text" class="form-control" id="address" path="address" placeholder="Address" disabled/>
            <label for="address">Address</label>
        </div>
        <div class="form-floating col-md-4 mb-3">
            <input type="text" class="form-control" id="phone" path="phone" placeholder="Phone" disabled/>
            <label for="phone">Phone</label>
        </div>
        <div class="form-floating col-md-6 mb-3">
            <input type="email" class="form-control" id="email" path="email" placeholder="name@example.com" disabled/>
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
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.4/moment.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.4/moment-with-locales.min.js"></script>
<script src="<c:url value="/js/user.js" />"></script>
<script>
    <c:url value="/api/users" var="u"/>
        window.onload = function () {
            loadMyUserCurrent(${currentUser.id});
        };
</script>
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
