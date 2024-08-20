/**
 * This is the shop orders repository
 */

package com.example.ex5.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ShopOrderRepository extends JpaRepository<ShopOrder, Long> {
    List<ShopOrder> findByOrderNum(int num);
}
