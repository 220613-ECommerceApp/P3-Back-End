package com.revature.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.revature.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

      @Query("FROM Product WHERE lower(name) LIKE lower(concat('%', ?1, '%'))")
      public List<Product> findBySimilarName(String pattern);

      @Query("FROM Product WHERE lower(description) LIKE lower(concat('%', ?1, '%'))")
      public List<Product> findByDescriptionContainingIgnoreCase(
                  String description);

      @Query(value = "SELECT * FROM products WHERE price BETWEEN :startPrice AND :endPrice", nativeQuery = true)
      public List<Product> priceRangeSearch(@Param("startPrice") double startPrice, @Param("endPrice") double endPrice);

      @Query(value = "SELECT FROM products WHERE id = "
                  + "(SELECT id FROM products_tags WHERE tag_name = :tagName)", nativeQuery = true)
      public List<Product> tagSearch(@Param("tagName") String tagName);
}