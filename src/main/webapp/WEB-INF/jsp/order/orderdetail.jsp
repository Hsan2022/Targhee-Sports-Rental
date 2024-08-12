<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<jsp:include page="../include/header.jsp" />

<style>
    .table {
        width: 100%;
        margin: 20px 0;
        border-collapse: collapse;
        /*opacity: max(45);*/
        background-color: white;
    }

    .table th, .table td {
        padding: 10px;
        text-align: left;
        border-bottom: 1px solid #dee2e6;
    }

    .table th {
        background-color: #007bff; /* Blue background for headers */
        color: #fff; /* White text in headers */
    }

    /*.table tr:nth-child(even) {*/
    /*    background-color: #f2f2f2; !* Zebra striping for rows *!*/
    /*}*/
</style>

<h1>This is your cart page</h1>

<section>
    <div class="container">

        <%-- Display message to user of successful order --%>
        <c:if test="${not empty message}">
            <div class="alert alert-success">
                    ${message}
            </div>
        </c:if>

        <%--diaplay items in cart before checkout--%>
        <c:if test="${empty message}">
            <div class="row pt-5" style="background-color: #f2f2f2;">
                <div class="col-12">
                    <h2 class="text-center">You Have (${orderDetails.size()}) Items In Cart </h2>
                </div>
            </div>
            <div class="row pt-3">
                <div class="col-12">
                    <table class="table">

                        <%--columns and values displayed--%>
                        <tr>
                            <th>Order Id</th>
                            <th>Order Date</th>
                            <th>Product Name</th>
                            <th>Quantity Ordered</th>
                            <th>Price Each</th>
                            <th>Total Price</th>
                            <th>Actions</th>
                        </tr>
                        <c:forEach items="${orderDetails}" var="detail">
                            <tr>
                                <td>${detail.order_id}</td>
                                <td>${detail.order_date}</td>
                                <td>${detail.product_name}</td>
                                <td>${detail.quantity}</td>
                                <td>${detail.price}</td>
                                <td>${detail.total}</td>
                                <td>
                                    <!-- Button to delete product from order -->
                                    <form action="<c:url value='/order/deleteProduct' />" method="post" style="display: inline;">

                                        <input type="hidden" name="productId" value="${detail.product_id}" />
                                        <input type="hidden" name="orderId" value="${detail.order_id}" />
                                        <button type="submit" class="btn btn-danger">Remove</button>

                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="5" class="text-right">Total</td>
                            <td>${orderTotal}</td>
                        </tr>
                    </table>
                </div>
            </div>

            <%--checkout--%>
            <div class="row">
                <div class="col-12 text-center">
                     <%--checkout button--%>
                    <a href="/order/checkout" class="btn btn-primary">Checkout</a>
                    <%--redirect to search page--%>
                    <a href="<c:url value='http://localhost:8080/search?search=' />" class="btn btn-secondary">Keep Shopping</a>
                </div>
            </div>
        </c:if>
    </div>
</section>

<jsp:include page="../include/footer.jsp" />
