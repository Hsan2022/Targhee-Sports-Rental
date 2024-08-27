<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp" />

<section class="container">
    <div class="createAccount-image-container">

    <div class="createAccount-container">
        <div style="background-color: rgba(255, 255, 255, 0.4); padding: 20px; border-radius: 8px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); text-align: center;">
            <p style="font-size: 36px; color: #000000; margin: 0; font-weight: bold">Create an account</p>
        </div>
        <div class="row pt-5 ">
            <div class="col-12">
                <form action="../account/create-account" method="post">

                    <!-- email input -->
                    <div class="row align-items-center justify-content-center">
                        <div class="col-2">
                            <label for="emailId" class="col-form-label">Email</label>
                        </div>
                        <div class="col-4">
                            <input type="text"
                                   id="emailId"
                                   name="email"
                                   class="form-control
                    <c:if test="${bindingResult.hasFieldErrors('email')}">is-invalid</c:if>" value="${form.email}">
                        </div>
                    </div>
                    <c:if test="${bindingResult.hasFieldErrors('email')}">
                        <div class="row align-items-center justify-content-center">
                            <div class="offset-2 col-4">
                                <div class="text-danger">
                                    <c:forEach items="${bindingResult.getFieldErrors('email')}" var="error">
                                        ${error.defaultMessage}<br>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </c:if>


                    <!-- password input field -->
                    <div class="row align-items-center justify-content-center pt-3">
                        <div class="col-2">
                            <label for="passwordId" class="col-form-label">Password</label>
                        </div>
                        <div class="col-4">
                            <input type="text"
                                   id="passwordId"
                                   name="password"
                                   class="form-control <c:if test="${bindingResult.hasFieldErrors('password')}">is-invalid</c:if>"
                                   value="${form.password}">
                        </div>
                    </div>
                    <c:if test="${bindingResult.hasFieldErrors('password')}">
                        <div class="row align-items-center justify-content-center">
                            <div class="offset-2 col-4">
                                <div style="color:red">
                                    <c:forEach items="${bindingResult.getFieldErrors('password')}" var="error">
                                        ${error.defaultMessage}<br>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </c:if>

                    <!-- firstname input field -->
                    <div class="row align-items-center justify-content-center pt-3">
                        <div class="col-2">
                            <label for="firstnameId" class="col-form-label">First Name</label>
                        </div>
                        <div class="col-4">
                            <input type="text"
                                   id="firstnameId"
                                   name="firstname"
                                   class="form-control
                    <c:if test="${bindingResult.hasFieldErrors('firstname')}">is-invalid</c:if>"
                                   value="${form.firstname}">
                        </div>
                    </div>
                    <c:if test="${bindingResult.hasFieldErrors('firstname')}">
                        <div class="row align-items-center justify-content-center">
                            <div class="offset-2 col-4">
                                <div style="color:red">
                                    <c:forEach items="${bindingResult.getFieldErrors('firstname')}" var="error">
                                        ${error.defaultMessage}<br>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </c:if>

                    <!-- lastname input field -->
                    <div class="row align-items-center justify-content-center pt-3">
                        <div class="col-2">
                            <label for="lastNameId" class="col-form-label">Last Name</label>
                        </div>
                        <div class="col-4">
                            <input
                                    type="text"
                                    id="lastNameId"
                                    name="lastname"
                                    class="form-control"
                                    value="${form.lastname}">
                        </div>
                    </div>
                    <c:if test="${bindingResult.hasFieldErrors('lastname')}">
                        <div class="row align-items-center justify-content-center">
                            <div class="offset-2 col-4">
                                <div style="color:red">
                                    <c:forEach items="${bindingResult.getFieldErrors('lastname')}" var="error">
                                        ${error.defaultMessage}<br>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </c:if>

                    <!-- address input field -->
                    <div class="row align-items-center justify-content-center pt-3">
                        <div class="col-2">
                            <label for="addressId" class="col-form-label">Address</label>
                        </div>
                        <div class="col-4">
                            <input
                                    type="text"
                                    id="addressId"
                                    name="address"
                                    class="form-control"
                                    value="${form.address}">
                        </div>
                    </div>
                    <c:if test="${bindingResult.hasFieldErrors('address')}">
                        <div class="row align-items-center justify-content-center">
                            <div class="offset-2 col-4">
                                <div style="color:red">
                                    <c:forEach items="${bindingResult.getFieldErrors('address')}" var="error">
                                        ${error.defaultMessage}<br>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </c:if>
                    <!-- Checkbox -->
                    <div class="form-check d-flex justify-content-center mb-8 pt-3">
                        <input class="form-check-input me-2" type="checkbox" value="" id="form2Example33" checked />
                        <label class="form-check-label" for="form2Example33">
                            Subscribe to our newsletter
                        </label>
                    </div>

                <%-- submit button--%>
                    <div class="row justify-content-center pt-3 ">
                        <div class="col-auto text-center">
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="image-container">
        <img src="../../../pub/images/skier2.jpg" alt="skier holding skis">
    </div>
    </div>
    </div>
</section>


<jsp:include page="../include/footer.jsp" />