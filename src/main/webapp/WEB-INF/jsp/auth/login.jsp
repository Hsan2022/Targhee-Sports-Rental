<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp"/>

<section class="container">
    <div class="loginForm-image-container">

    <div class="loginForm-container">
        <!-- Check for non-empty values -->
        <c:if test="${param.error != null}">
            <div class="alert alert-danger" role="alert">Invalid Username or Password</div>
        </c:if>

        <div style="background-color: rgba(255, 255, 255, 0.4); padding: 20px; border-radius: 8px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); text-align: left;">
            <p style="font-size: 36px; color: #000000; margin: 0; font-weight: bold">Welcome back!</p>
        </div>

        <form action="/account/loginProcessingURL" method="post">
            <!-- Email input -->
            <div class="form-group mb-3">
                <label for="usernameId" class="form-label">Email</label>
                <input type="text" id="usernameId" name="username" class="form-control
                    <c:choose>
                        <c:when test="${not empty bindingResult && bindingResult.hasFieldErrors('username')}">
                            is-invalid
                        </c:when>
                        <c:otherwise>
                            is-valid
                        </c:otherwise>
                    </c:choose>"
                       value="${form.username}" aria-describedby="usernameHelp">
                <c:if test="${bindingResult.hasFieldErrors('username')}">
                    <small id="usernameHelp" class="text-danger">
                        <c:forEach items="${bindingResult.getFieldErrors('username')}" var="error">
                            ${error.defaultMessage}<br>
                        </c:forEach>
                    </small>
                </c:if>
            </div>

            <!-- Password input field -->
            <div class="form-group mb-4">
                <label for="passwordId" class="form-label">Password</label>
                <input type="password" id="passwordId" name="password" class="form-control
                    <c:choose>
                        <c:when test="${not empty bindingResult && bindingResult.hasFieldErrors('password')}">
                            is-invalid
                        </c:when>
                        <c:otherwise>
                            is-valid
                        </c:otherwise>
                    </c:choose>"
                       value="${form.password}" aria-describedby="passwordHelp">
                <c:if test="${bindingResult.hasFieldErrors('password')}">
                    <small id="passwordHelp" class="text-danger">
                        <c:forEach items="${bindingResult.getFieldErrors('password')}" var="error">
                            ${error.defaultMessage}<br>
                        </c:forEach>
                    </small>
                </c:if>
            </div>

            <!-- Submit button -->
            <div class="mb-3 text-center">
                <button type="submit" class="btn btn-primary">Login</button>
            </div>

            <!-- Social Media Links -->
            <div class="d-flex justify-content-center text-center mt-4 pt-1 ">
                <p>or sign up with:</p>
            </div>
            <div class="d-flex justify-content-center text-center mt-4 pt-1">
                <a href="#!" style="color: #003366; margin-right: 1.5rem; font-size: 2rem;"><i class="bi bi-facebook"></i></a>
                <a href="#!" style="color: #003366; margin-right: 1.5rem; font-size: 2rem;"><i class="bi bi-twitter"></i></a>
                <a href="#!" style="color: #003366; font-size: 2rem;"><i class="bi bi-google"></i></a>
            </div>

            <!-- Sign Up Prompt -->
            <div class="d-flex justify-content-center text-center mt-4 pt-1">
                <p class="mb-0">Don't have an account? <a href="/account/create-account" class="btn btn-primary">Create an Account</a></p>
            </div>
        </form>
    </div>

    <div class="image-container">
        <img src="../../../pub/images/chairlift.jpeg" alt="Chairlift Image">
    </div>
    </div>
</section>

<jsp:include page="../include/footer.jsp"/>
