<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="include/header.jsp" />

<!-- Search form setting -->
<section>
    <div class="container">
        <div class="row justify-content-center pt-5 pb-3">
            <div class="col-md-8 text-center">
                <!-- Image link above search form -->
                <div id="promoImage" class="pt-4" style="margin-bottom: 10px;">
                    <img src="https://mountainsupplybend.com/uploads/3/4/4/6/34463170/ski-snow-brands_orig.png" alt="gear brands" class="img-fluid"/>
                </div>
                <form id="searchForm" action="/search" method="GET">
                    <div class="mb-3">
                        <input type="text" value="${search}" class="form-control" id="search" name="search" placeholder="Check out our best deals! Search here."
                               style="height: 70px; font-size: 22px; padding: 0 15px; box-sizing: border-box;"/>
                    </div>
                    <button type="submit" class="btn btn-info" style="height: 50px; font-size: 22px; padding: 0 40px; margin-bottom: 10px;">Search</button>
                </form>
            </div>
        </div>
    </div>
</section>

<!-- Search form results settings -->
<c:if test="${not empty products}">
    <section id="searchResults">
        <div class="container">
            <div class="row pt-5">
                <div class="col-12">
                    <h2 class="text-center">Scroll down to see (${products.size()}) results for ${search}</h2>
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
<%--                                    <div class="col-auto">--%>
<%--                                        <a href="/?id=${product.id}">Edit</a>--%>
<%--                                    </div>--%>
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


<!-- JavaScript to hide image when search is submitted -->

<script>
    document.addEventListener('DOMContentLoaded', function() {
        document.getElementById('searchForm').addEventListener('submit', function() {
            console.log('Form submitted');
            document.getElementById('promoImage').style.display = 'none';
        });
    });
</script>

