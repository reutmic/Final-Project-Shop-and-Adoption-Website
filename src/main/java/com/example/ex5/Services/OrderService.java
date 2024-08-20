/**
 * This is an Order Service
 */

package com.example.ex5.Services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.ex5.repo.ShopOrderRepository;
import com.example.ex5.repo.ShopOrder;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;


@Service
public class OrderService {

    @Autowired
    private ShopOrderRepository orders;


    // functions:

    /**
     * This function is for generating a unique number for a new order
     * @return - returns the new order number
     */
    public int generateUniqueOrderNum() {
        Random random = new Random();
        int orderNum;
        Set<Integer> existingOrderNums = new HashSet<>();

        for (ShopOrder order : orders.findAll()) {
            existingOrderNums.add(order.getOrderNum());
        }

        do {
            orderNum = 10000000 + random.nextInt(90000000);
        } while (existingOrderNums.contains(orderNum));

        return orderNum;
    }

}
