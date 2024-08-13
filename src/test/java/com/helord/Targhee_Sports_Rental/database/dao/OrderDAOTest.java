package com.helord.Targhee_Sports_Rental.database.dao;

import com.helord.Targhee_Sports_Rental.database.entity.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderDAOTest {

    @Autowired
    private OrderDAO orderDao;

    @PersistenceContext
    private EntityManager entityManager;

    private List<Order> orders;

    @BeforeEach
    @Transactional // roll back after each test
    void setUp() {
        // Retrieve existing order data from the database
        TypedQuery<Order> query = entityManager.createQuery("SELECT o FROM Order o", Order.class);
        orders = query.getResultList();
    }

    @Test
    void testUsingExistingOrders() {
        for (Order order : orders) {
            int orderId = order.getId();
            int userId = order.getUserId();
            Date orderDate = order.getOrderDate();
            Date requiredDate = order.getRequiredDate();
            String status = order.getStatus();
            String comment = order.getComment();

            // Assertions
            assertTrue(orderId > 0, "Order ID should be greater than 0");
            assertTrue(userId > 0, "User ID should be greater than 0");
            assertNotNull(orderDate, "Order date should not be null");
            assertNotNull(requiredDate, "Required date should not be null");
            assertNotNull(status, "Order status should not be null");
            assertFalse(status.isEmpty(), "Order status should not be empty");

            // Optionally check comments
            assertTrue(comment == null || !comment.isEmpty(), "Order comments should be null or not empty");
        }
    }
}