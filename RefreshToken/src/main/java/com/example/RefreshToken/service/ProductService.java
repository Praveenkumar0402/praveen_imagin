package com.example.RefreshToken.service;

import com.example.RefreshToken.dto.Product;
import com.example.RefreshToken.entity.UserInfo;
import com.example.RefreshToken.exceptions.NotFoundException;
import com.example.RefreshToken.repository.UserInfoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ProductService {

    List<Product> productList = null;

    @Autowired
    UserInfoRepository userInfoRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @PostConstruct
    public void loadProductsFromDB() {
        productList = IntStream.rangeClosed(1, 100)
                .mapToObj(i -> Product.builder()
                        .productId(i)
                        .name("product " + i)
                        .qty(new Random().nextInt(10))
                        .price(new Random().nextInt(5000)).build()
                ).collect(Collectors.toList());
    }

    public List<Product> getProducts() {
        return productList;
    }

    public Product getProduct(int id) throws NotFoundException {
        return productList.stream()
                .filter(product -> product.getProductId() == id)
                .findAny()
                .orElseThrow(() -> new NotFoundException("product " + id + " not found"));
    }

    public String addUser(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfoRepository.save(userInfo);
        return "User added to system ";
    }
}