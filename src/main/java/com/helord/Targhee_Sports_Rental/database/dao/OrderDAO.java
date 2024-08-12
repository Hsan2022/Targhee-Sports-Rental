package com.helord.Targhee_Sports_Rental.database.dao;

import com.helord.Targhee_Sports_Rental.database.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface OrderDAO extends JpaRepository<Order, Long> {

    // Fetch order details including product information for a given order
    @Query(value = "SELECT o.id AS order_id, o.order_date, od.quantity_ordered AS quantity, p.product_name, p.id AS product_id, p.buy_price AS price, " +
            "(od.quantity_ordered * p.buy_price) AS total " +
            "FROM orderdetails od " +
            "JOIN products p ON od.product_id = p.id " +
            "JOIN orders o ON o.id = od.order_id " +
            "WHERE o.id = :orderId", nativeQuery = true)
    List<Map<String, Object>> getOrderDetails(Integer orderId);

    // calculate total order amount for a given order
    @Query(value = "SELECT SUM(od.quantity_ordered * p.buy_price) AS orderTotal " +
            "FROM orderdetails od " +
            "JOIN products p ON od.product_id = p.id " +
            "JOIN orders o ON o.id = od.order_id " +
            "WHERE o.id = :orderId", nativeQuery = true)
    Double getOrderTotal(Integer orderId);


    // find an order with 'CART' status for a given user
    @Query(value = " SELECT * FROM orders WHERE user_id = :userId AND status = 'CART'", nativeQuery = true)
    Order findOrderInCartStatus(Integer userId);

}
