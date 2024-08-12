<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="include/header.jsp" />

<!-- Search form for searching products -->
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

<!-- Search results settings -->
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
                            <img src="${product.imageUrl}" class="card-img-top" alt="${product.productName}">
                            <div class="card-body">
                                <h5 class="card-title">${product.productName}</h5>
                                <p class="card-text">${product.productDescription}</p>
                            </div>
                            <div class="card-footer">
                                <p class="card-text font-weight-bold text-muted">${product.buyPrice}</p>
                                <div class="row">
                                    <div class="col-auto">
                                        <a href="/?id=${product.id}">Edit</a>
                                    </div>
                                    <div class="col-auto">
                                        <a href="/order/addToCart?productId=${product.id}">Add To Cart</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </section>
</c:if>

<jsp:include page="include/footer.jsp" />
