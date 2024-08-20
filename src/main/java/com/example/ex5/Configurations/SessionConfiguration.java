/**
 * this is a configuration for session-scoped classes
 */


package com.example.ex5.Configurations;

import com.example.ex5.SessionClasses.UserDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Scope;
//import org.springframework.web.context.annotation.ApplicationScope;
//import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;
import com.example.ex5.SessionClasses.Cart;

@Configuration
public class SessionConfiguration {
    @Bean
    @SessionScope
    public Cart sessionCart() {
        Cart cart = new Cart();
        return cart;
    }


    @Bean
    @SessionScope
    public UserDetails sessionUserDetails() {
        UserDetails userDetails = new UserDetails();
        return userDetails;
    }

}
