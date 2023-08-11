package com.example.Admin.controller;

import com.example.Library.dto.OrderDto;
import com.example.Library.model.*;
import com.example.Library.repository.*;
import com.example.Library.service.Impl.ProductServiceImpl;
import com.example.Library.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

@Component
public class SampleData implements CommandLineRunner {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private MarketRepository marketRepository;
    @Autowired
    private ScreenRepository screenRepository;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductServiceImpl productService;
    @Override
    public void run(String... args) throws Exception {
        addUser();
        addRole();
        addProduct();
        addCategory();
        addOrder();
        addMarket();
        addScreen();
    }

    private void addUser() {
        if(!userRepository.existsByfullName("Le Tan Hung")) {
            User user = new User();
            user.setFullName("Le Tan Hung");
            user.setBirthdate("07/11/2002");
            user.setPhoneNumber("1234567890");
            user.setEmail("letanhung@gmail.com");
            user.setUsername("hungle");
            user.setPassword(passwordEncoder.encode("123456"));
            user.setCreate_time("Mon Aug 07 10:14:36 GMT+07:00 2023");
            userRepository.save(user);
        }
    }

    private void addRole() {
        if(!roleRepository.existsByName("admin")) {
            Role role1 = new Role();
            role1.setName("admin");
            roleRepository.save(role1);
        }
        if(!roleRepository.existsByName("user")) {
            Role role2 = new Role();
            role2.setName("user");
            roleRepository.save(role2);
        }
    }

    private void addProduct() {
        if(!productRepository.existsByName("nước dừa")) {
            Product product = new Product();
            product.setName("nước dừa");
            product.setPrice(25000.0);
            product.setQuantity("10");
            product.setNote("");
            product.setCreate_time(productService.convertToDate("07/08/2023"));
            product.setUpdate_time(productService.convertToDate("07/08/2023"));
            productRepository.save(product);
        }
    }

    private void addCategory() {
        if(!categoryRepository.existsByName("laptop")) {
            Category category = new Category();
            category.setName("laptop");
            categoryRepository.save(category);
        }
    }

    private void addOrder() {
        if(!orderRepository.existsByName("Le Tan Hung")) {
            OrderDto order = new OrderDto();
            Date date = new Date();
            java.sql.Date dateNow = new java.sql.Date(date.getTime());

            order.setNgaydat(dateNow);
            order.setName("Le Tan Hung");
            order.setAddress("TG");
            order.setPhonenumber("1234567890");
            order.setNote("");
            order.setTotalproducts("nước dừa");
            order.setQuantity("3");
            order.setPrice("25000");
            order.setTotalprice("75000");
            orderService.newOrder(order);
        }
    }

    private void addMarket() {
        if(!marketRepository.existsByName("laptop")){
            Market market = new Market();
            market.setName("laptop");
            market.setPrice_increase(25000.0);
            marketRepository.save(market);
        }
    }

    private void addScreen() {
        if(!screenRepository.existsByName("dasboard")) {
            Screen screen = new Screen();
            screen.setName("dasboard");
            screen.setDescription("Giao diện màn hình");
            screenRepository.save(screen);
        }
    }
}
