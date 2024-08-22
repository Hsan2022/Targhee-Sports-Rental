package com.helord.Targhee_Sports_Rental.database.dao;

import com.helord.Targhee_Sports_Rental.database.entity.OrderDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderDetailsDAOTest {

    @Autowired
    private OrderDetailsDAO orderDetailDao; // Assumed DAO for OrderDetail, adjust as needed

    @PersistenceContext
    private EntityManager entityManager;

    private List<OrderDetail> orderDetails;

    @BeforeEach
    @Transactional // roll back after each test
    void setUp() {
        // Retrieve existing order detail data from the database
        TypedQuery<OrderDetail> query = entityManager.createQuery("SELECT od FROM OrderDetail od", OrderDetail.class);
        orderDetails = query.getResultList();
    }

    @Test
    void testUsingExistingOrderDetails() {
        for (OrderDetail orderDetail : orderDetails) {
            Integer orderId = orderDetail.getOrderID();
            Integer productId = orderDetail.getProductID();
            Integer quantityOrdered = orderDetail.getQuantityOrdered();
            Double priceEach = orderDetail.getPriceEach();
            Integer orderLineNumber = orderDetail.getOrderLineNumber();

            // Assertions
            assertNotNull(orderId, "Order ID should not be null");
            assertTrue(orderId > 0, "Order ID should be greater than 0");

            assertNotNull(productId, "Product ID should not be null");
            assertTrue(productId > 0, "Product ID should be greater than 0");

            assertNotNull(quantityOrdered, "Quantity ordered should not be null");
            assertTrue(quantityOrdered > 0, "Quantity ordered should be greater than 0");

            assertNotNull(priceEach, "Price each should not be null");
            assertTrue(priceEach >= 0, "Price each should be non-negative");

            assertNotNull(orderLineNumber, "Order line number should not be null");
            assertTrue(orderLineNumber >= 0, "Order line number should be non-negative");

        }
    }
}
