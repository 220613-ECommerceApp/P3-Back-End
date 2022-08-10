package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.revature.models.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, String> {

  // @Query(value = "SELECT * FROM products WHERE id IN "
  // + "(SELECT product_id FROM tag_junction WHERE tag_name = :tagName)",
  // nativeQuery = true)
  // public List<Product> tagSearch(@Param("tagName") String tagName);
}
