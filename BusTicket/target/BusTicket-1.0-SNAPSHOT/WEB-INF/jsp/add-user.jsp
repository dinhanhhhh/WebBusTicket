<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="container">
    <h1 class="text-center text-info" style="margin-top: 15px">Thêm Nhân Viên</h1>

    <c:url value="/admin/users/add_user" var="action" />
    <form:form method="post" action="${action}" enctype="multipart/form-data" modelAttribute="user">
        <div class="row g-3">
            <div class="col-md-6 mb-3">
                <label for="fullname" class="form-label fs-5">Full Name</label>
                <form:input type="text" class="form-control" id="fullname" path="fullname" placeholder="Full Name"/>
            </div>
            <div class="col-md-2 mb-3">
                <label for="gender" class="form-label fs-5">Gender</label>
                <div>
                    <div class="form-check form-check-inline">
                        <form:radiobutton class="form-check-input" id="genderM" path="gender" value="Nam"/>
                        <label class="form-check-label fs-5" for="inlineRadio1">Male</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <form:radiobutton class="form-check-input" id="genderF" path="gender" value="Nu"/>
                        <label class="form-check-label fs-5" for="inlineRadio2">Female</label>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <label for="role" class="form-label fs-5">Role</label>
                <form:select class="form-select" aria-label=".form-select-lg example" id="userRole" path="userRole">
                    <option selected value="ROLE_EMPLOYEE">Employee</option>
                    <option value="ROLE_DRIVER">Driver</option>
                    <option value="ROLE_CUSTOMER">Customer</option>
                    <option value="ROLE_ADMIN">Admin</option>
                </form:select>
            </div>
            <div class="col-md-6 mb-3">
                <label for="birthday" class="form-label fs-5">Birthday</label>
                <form:input type="date" class="form-control" id="birthday" path="birthday" placeholder="Birthday"/>
            </div>
            <div class="col-md-6 mb-3">
                <label class="form-label fs-5">Address</label>
                <form:input type="text" class="form-control" id="address" path="address" placeholder="Address"/>

            </div>
            <div class="col-md-6 mb-3">
                <label for="phone" class="form-label fs-5">Phone</label>
                <form:input type="text" class="form-control" id="phone" path="phone" placeholder="Phone"/>

            </div>
            <div class="col-md-6 mb-3">
                <label for="email" class="form-label fs-5">Email</label>
                <form:input type="email" class="form-control" id="email" path="email" placeholder="name@example.com"/>

            </div>
            <div class="col-md-6 mb-3">
                <label for="username" class="form-label fs-5">Username</label>
                <form:input type="text" class="form-control" id="username" path="username" placeholder="Username"/>

            </div>    
            <div class="col-md-6 mb-3">
                <label for="password" class="form-label fs-5">Password</label>
                <form:input type="password" class="form-control" id="password" path="password" placeholder="Password"/>

            </div>
            <div class="col-md-6 mb-3">
                <label for="password" class="form-label fs-5">Confirm Password</label>
                <form:input type="password" class="form-control" id="confirmPassword" path="confirmPassword" placeholder="Confirm Password"/>

            </div>
            <div class="col-md-6 mb-3">
                <label for="avatar" class="form-label fs-5">Avatar</label>
                <form:input type="file" class="form-control" id="avatar" path="file"/>
                <img style="width: 50%; margin: 10px" id="image" alt="Image"/>
            </div>
            <div class="col-auto">
                <button type="submit" class="btn btn-primary">Submit</button>
            </div>   
        </div>
    </form:form> 
</div>

<script type="text/javascript">
    $(document).ready(function () {
        $('#avatar').change(function () {
            showImageChooseFile(this);
        });
    });

    function showImageChooseFile(fileInput) {
        var file = fileInput.files[0];
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#image').attr('src', e.target.result);
        };

        reader.readAsDataURL(file);
    }
</script>