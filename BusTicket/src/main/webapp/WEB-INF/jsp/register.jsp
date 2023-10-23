<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="container">
    <div class="card-register container" style="padding: 10px 35px; background-color: lightblue;">
        <h1 class="text-center text-info">ĐĂNG KÝ</h1>

        <c:if test="${errMsg != null}">
            <div class="alert alert-danger alert-dismissible">
                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                ${errMsg}
            </div>
        </c:if>

        <c:url value="/register" var="action" />

        <form:form method="post" action="${action}" enctype="multipart/form-data" modelAttribute="user">
            <div class="row g-3">
                <div class="col-md-4">
                    <div class="col-md-10 mb-3">
                        <label for="avatar" class="form-label fs-5">Avatar</label>
                        <form:input type="file" class="form-control" id="avatar" path="file"/>
                    </div>
                    <div class="col-md-12 mb-3">
                        <img src="https://res.cloudinary.com/doe6rzwse/image/upload/v1665356827/busApp/no_image_s6haop.jpg" style="width: 45%; margin: 10px" id="image" alt="Image"/>
                    </div>
                </div>
                <div class="col-md-8">
                    <div class="row g-3">
                        <div class="col-md-6 mb-3">
                            <label for="fullname" class="form-label fs-5">Full Name</label>
                            <form:input type="text" class="form-control" id="fullname" path="fullname" placeholder="Full Name"/>
                        </div>
                        <div class="col-md-3 mb-3">
                            <label for="gender" class="form-label fs-5">Gender</label>
                            <div>
                                <div class="form-check form-check-inline">
                                    <form:radiobutton class="form-check-input" id="gender" path="gender" value="Nam"/>
                                    <label class="form-check-label fs-5" for="inlineRadio1">Male</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <form:radiobutton class="form-check-input" id="gender" path="gender" value="Nu"/>
                                    <label class="form-check-label fs-5" for="inlineRadio2">Female</label>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3 mb-3">
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
                            <form:errors path="username" element="div" cssClass="text-danger" />

                        </div>    
                        <div class="col-md-6 mb-3">
                            <label for="password" class="form-label fs-5">Password</label>
                            <form:input type="password" class="form-control" id="password" path="password" placeholder="Password"/>
                            <form:errors path="password" element="div" cssClass="text-danger" />

                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="password" class="form-label fs-5">Confirm Password</label>
                            <form:input type="password" class="form-control" id="confirmPassword" path="confirmPassword" placeholder="Confirm Password"/>
                            <form:errors path="confirmPassword" element="div" cssClass="text-danger" />

                        </div>
                        <div class="col-auto mb-3">
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </div>   
                    </div>
                </div>
            </div>
        </form:form> 
    </div>
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