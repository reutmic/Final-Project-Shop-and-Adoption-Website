/**
 * This is the products repository class
 */

package com.example.ex5.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findBySuitedForIn(List<String> suitedFor);
    List<Product> findByCategoryIn(List<String> categories);
    List<Product> findBySuitedForInAndCategoryIn(List<String> suitedFor, List<String> categories);
}
