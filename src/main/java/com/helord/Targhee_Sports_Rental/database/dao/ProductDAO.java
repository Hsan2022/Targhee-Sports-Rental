package com.helord.Targhee_Sports_Rental.database.dao;

import com.helord.Targhee_Sports_Rental.database.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDAO extends JpaRepository<Product, Long> {

    // access spring data JPA to use the method name to generate the query
    Product findById(Integer id);

    // native sql query
    @Query("select p from Product p where p.productName like concat('%', :name, '%') or p.productCode like concat('%', :name, '%')")
    List<Product> findByNameOrCode(String name);


}
