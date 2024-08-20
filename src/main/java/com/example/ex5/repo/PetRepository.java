/**
 * This is a pet repository class
 */

package com.example.ex5.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findByType(String type);
}
