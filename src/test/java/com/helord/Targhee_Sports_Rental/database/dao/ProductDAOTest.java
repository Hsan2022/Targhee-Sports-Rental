package com.helord.Targhee_Sports_Rental.database.dao;

import com.helord.Targhee_Sports_Rental.database.entity.Product;
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
public class ProductDAOTest {

    @Autowired
    private ProductDAO productDao; // Assumed DAO for Product, adjust as needed

    @PersistenceContext
    private EntityManager entityManager;

    private List<Product> products;

    @BeforeEach
    @Transactional // roll back after each test
    void setUp() {
        // Retrieve existing product data from the db
        TypedQuery<Product> query = entityManager.createQuery("SELECT p FROM Product p", Product.class);
        products = query.getResultList();
    }

    @Test
    void testUsingExistingProducts() {
        for (Product product : products) {
            String productCode = product.getProductCode();
            String productName = product.getProductName();
            String productDescription = product.getProductDescription();
            Integer quantityInStock = product.getQuantityInStock();
            Double buyPrice = product.getBuyPrice();

            // Assertions
            assertNotNull(productCode, "Product code should not be null");
            assertNotNull(productName, "Product name should not be null");
            assertFalse(productName.isEmpty(), "Product name should not be empty");
            assertTrue(quantityInStock >= 0, "Quantity in stock should be non-negative");
            assertTrue(buyPrice >= 0, "Buy price should be non-negative");

            assertNotNull(productDescription, "Product description should not be null");
            assertFalse(productDescription.isEmpty(), "Product description should not be empty");


        }
    }
}
