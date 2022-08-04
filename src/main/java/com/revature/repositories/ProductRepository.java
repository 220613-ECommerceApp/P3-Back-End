package com.revature.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.revature.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

      @Query(value = "select * from products where lower(name) ~* :pattern", nativeQuery = true)
      public List<Product> findBySimilarName(@Param("pattern") String pattern);

      @Query(value = "select * from products where lower(description) ~* :description", nativeQuery = true)
      public List<Product> findByDescriptionContainingIgnoreCase(
                  @Param("description") String description);

      @Query(value = "SELECT * FROM products WHERE price BETWEEN :startPrice AND :endPrice", nativeQuery = true)
      public List<Product> priceRangeSearch(@Param("startPrice") double startPrice, @Param("endPrice") double endPrice);

      @Query(value = "SELECT * FROM products WHERE id IN "
                  + "(SELECT product_id FROM tag_junction WHERE tag_name = :tagName)", nativeQuery = true)
      public List<Product> tagSearch(@Param("tagName") String tagName);
}