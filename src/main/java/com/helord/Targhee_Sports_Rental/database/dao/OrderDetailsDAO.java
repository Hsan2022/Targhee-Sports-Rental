package com.helord.Targhee_Sports_Rental.database.dao;

import com.helord.Targhee_Sports_Rental.database.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


// class to query and save objs in db, access to methods library
public interface OrderDetailsDAO extends JpaRepository<OrderDetail, Long> {

    // custom SQL query, getting table data from db
    @Query(value = "SELECT * FROM orderdetails WHERE order_id = :orderId AND product_id = :productId", nativeQuery = true)
    // fetch an OrderDetail based on the specified orderId and productId - () called in OrderController
    OrderDetail isProductInCart(Integer orderId, Integer productId);

    @Query("SELECT COUNT(od) FROM OrderDetail od WHERE od.order.id = :orderId")
    long countByOrder(@Param("orderId") Integer orderId);

}
