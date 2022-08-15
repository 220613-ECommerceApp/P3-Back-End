package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revature.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

      @Query(value = "SELECT * FROM products WHERE lower(name) ~* :pattern ORDER BY LOWER(name)", nativeQuery = true)
      public List<Product> findBySimilarName(@Param("pattern") String pattern);

      @Query(value = "SELECT * FROM products WHERE lower(description) ~* :description ORDER BY LOWER(name)", nativeQuery = true)
      public List<Product> findByDescriptionContainingIgnoreCase(
                  @Param("description") String description);

      @Query(value = "SELECT * FROM products WHERE price BETWEEN :startPrice AND :endPrice ORDER BY LOWER(name)", nativeQuery = true)
      public List<Product> priceRangeSearch(@Param("startPrice") double startPrice, @Param("endPrice") double endPrice);

      @Query(value = "SELECT * FROM products WHERE id IN "
                  + "(SELECT product_id FROM tag_junction WHERE tag_name = :tagName ) ORDER BY Lower(name)", nativeQuery = true)
      public List<Product> tagSearch(@Param("tagName") String tagName);

      @Query(value = "SELECT * FROM products WHERE (price BETWEEN :startPrice AND :endPrice) AND ((lower(name) ~* :pattern) OR (lower(description) ~* :pattern)) ORDER BY lower(name)", nativeQuery = true)
      public List<Product> superSearchWithoutTag(@Param("startPrice") double startPrice,
                  @Param("endPrice") double endPrice,
                  @Param("pattern") String pattern);

      @Query(value = "SELECT * FROM products WHERE (price BETWEEN :startPrice AND :endPrice) AND (id IN (SELECT product_id FROM tag_junction WHERE tag_name = :tagName)) AND ((lower(name) ~* :pattern) OR (lower(description) ~* :pattern) ) ORDER BY LOWER(name)", nativeQuery = true)
      public List<Product> superSearchWithTag(@Param("startPrice") double startPrice,
                  @Param("endPrice") double endPrice,
                  @Param("tagName") String tagName, @Param("pattern") String pattern);
      
      @Query("FROM Product ORDER BY LOWER(name)")
      public List<Product> findAll();
}
