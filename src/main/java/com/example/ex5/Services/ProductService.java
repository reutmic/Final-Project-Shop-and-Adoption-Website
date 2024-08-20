/**
 * This is a Product Service
 */
package com.example.ex5.Services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.ex5.repo.Product;
import com.example.ex5.repo.ProductRepository;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository products;

    /**
     * This function is for searching for products in the repository by the filters of 'suitedFor'
     *  and 'category'.
     * @param suitedFor - a list of chosen 'suitedFor' options.
     * @param categories - a list of chosen 'category' options.
     * @return - returns the products that fits the search
     */
    public List<Product> getFilteredProducts(List<String> suitedFor, List<String> categories) {
        if (suitedFor != null && !suitedFor.isEmpty() && categories != null && !categories.isEmpty()) {
            return products.findBySuitedForInAndCategoryIn(suitedFor, categories);
        }
        else if (suitedFor != null && !suitedFor.isEmpty()) {
            return products.findBySuitedForIn(suitedFor);
        }
        else if (categories != null && !categories.isEmpty()) {
            return products.findByCategoryIn(categories);
        }
        else {
            return products.findAll();
        }
    }
}
