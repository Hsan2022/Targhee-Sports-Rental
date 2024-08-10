<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="include/header.jsp" />


<%--search form for searching products--%>
<section>
    <div class="container">
        <div class="row justify-content-center pt-5 pb-3">
            <div class="col-md-8 text-center">
                <form action="/search" method="GET">
                    <div class="mb-3">
                        <label for="search" class="form-label"><h4>Product Name Search</h4></label>
                        <input type="text" value="${search}" class="form-control" id="search" name="search" placeholder="Enter search term"/>
                    </div>
                    <button type="submit" class="btn btn-info">Search</button>
                </form>
            </div>
        </div>
    </div>
</section>


<%--search results settings--%>
<%--these tags allow content to be rendered if prod list is not empty--%>
<%--for now all images are the same--%>
<%--user has deliver/size/special offers option--%>
<c:if test="${not empty products}">
    <section id="searchResults">
        <div class="container">
            <div class="row pt-5">
                <div class="col-12">
                    <h2 class="text-center">There are (${products.size()}) search results for ${search}</h2>
                </div>
            </div>
            <div class="row pt-3">
                <c:forEach items="${products}" var="product">
                    <div class="col-lg-4 col-md-6 mb-4">
                        <div class="card h-100 shadow-sm">
                            <img src="../../pub/images/jacket.jpeg" class="card-img-top" alt="Product Image">
                            <div class="card-body">
                                <h5 class="card-title">${product.productName}</h5>
                                <p class="card-text">${product.productDescription}</p>
                            </div>
                            <form action="/cart/add" method="post">
                                <input type="hidden" name="productId" value="${product.id}"/>
                                <div class="form-group mt-3">
                                    <label for="size-${product.id}">Size:</label><br>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="size" id="small-${product.id}" value="small" required>
                                        <label class="form-check-label" for="small-${product.id}">Small</label>
                                    </div>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="size" id="medium-${product.id}" value="medium" required>
                                        <label class="form-check-label" for="medium-${product.id}">Medium</label>
                                    </div>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="size" id="large-${product.id}" value="large" required>
                                        <label class="form-check-label" for="large-${product.id}">Large</label>
                                    </div>
                                </div>
                                <div class="form-group mt-3">
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="pickup-delivery" id="pickup-${product.id}" value="pickup" required>
                                        <label class="form-check-label" for="pickup-${product.id}">Pick up in store</label>
                                    </div>
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="pickup-delivery" id="delivery-${product.id}" value="delivery" required>
                                        <label class="form-check-label" for="delivery-${product.id}">Delivery</label>
                                    </div>
                                </div>
                                <div class="form-check mt-3">
                                    <input class="form-check-input" type="checkbox" id="special-offers-${product.id}" name="specialOffers">
                                    <label class="form-check-label" for="special-offers-${product.id}">Receive special offers</label>
                                </div>
                                <div class="card-footer">
                                    <div class="row">
                                        <div class="col">
                                            <p class="card-text font-weight-bold text-muted">${product.buyPrice}</p>
                                        </div>
                                        <div class="col-auto">
                                            <td><a href="/?id=${product.id}">Edit</a></td>

                                        <%--                                     <button type="submit" class="btn btn-primary">Add to Cart</button>--%>
                                        <td><a href="/order/addToCart?id=${product.id}">Add To Cart</a></td>

                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </section>
</c:if>

<%--validation in js--%>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        const forms = document.querySelectorAll('form[action="/cart/add"]');

        forms.forEach(form => {
            form.addEventListener('submit', function (event) {
                const sizeSelected = form.querySelector('input[name="size"]:checked');
                const deliverySelected = form.querySelector('input[name="pickup-delivery"]:checked');

                if (!sizeSelected) {
                    alert('Please select a size.');
                    event.preventDefault(); // Prevents form submission
                    return;
                }

                if (!deliverySelected) {
                    alert('Please select a delivery option.');
                    event.preventDefault(); // Prevents form submission
                    return;
                }
            });
        });
    });
</script>

<jsp:include page="include/footer.jsp" />
