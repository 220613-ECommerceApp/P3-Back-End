package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.revature.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	@Query(value="SELECT name FROM products WHERE DIFFERENCE(name, ?1) >2"
			+ "ORDER BY DIFFERENCE(name, ?1)", nativeQuery=true)
	public List<Product> findBySimilarName(String name);
	
	@Query("SELECT p FROM products p WHERE CONCAT(p.name) LIKE %?1%")
	public List<Product> search(String keyword);
	
	@Query("SELECT p FROM products p WHERE p.price BETWEEN startPrice AND endPrice")
	public List<Product> priceRangeSearch(double startPrice, double endPrice);
	
	@Query("SELECT p FROM products p WHERE p.id = (SELECT product_id FROM products_tags WHERE tag_name = tagName")
	public List<Product> tagSearch(String tagName); 
	
}



