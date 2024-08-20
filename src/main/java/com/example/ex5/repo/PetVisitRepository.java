/**
 * This is a pet visits repository class
 */
package com.example.ex5.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PetVisitRepository extends JpaRepository<PetVisit, Long>{
        List<PetVisit> findByPotentialAdopterEmailAndStatusNotIn(String email, List<String> statuses);
}
