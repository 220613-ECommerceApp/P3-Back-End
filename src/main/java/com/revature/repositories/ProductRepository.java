package com.revature.repositories;

import com.revature.models.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
   public List<Product> findByDescriptionContainingIgnoreCase(String description);
}
