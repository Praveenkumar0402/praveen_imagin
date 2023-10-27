package com.example.RefreshToken.controller;

import com.example.RefreshToken.config.UserInfoUserDetailsService;
import com.example.RefreshToken.dto.AuthRequest;
import com.example.RefreshToken.dto.JwtResponse;
import com.example.RefreshToken.dto.Product;
import com.example.RefreshToken.dto.RefreshTokenRequest;
import com.example.RefreshToken.entity.RefreshToken;
import com.example.RefreshToken.entity.UserInfo;
import com.example.RefreshToken.exceptions.NotFoundException;
import com.example.RefreshToken.service.JwtService;
import com.example.RefreshToken.service.ProductService;
import com.example.RefreshToken.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    JwtService jwtService;
    @Autowired
    RefreshTokenService refreshTokenService;
    @Autowired
    AuthenticationManager authenticationManager;


    @PostMapping("/create")
    public String addNewUser(@RequestBody UserInfo userInfo) {
        return productService.addUser(userInfo);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Product> getAllTheProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public Product getProductById(@PathVariable int id) throws NotFoundException {
        return productService.getProduct(id);
    }


    @PostMapping("/login")
    public JwtResponse authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(authRequest.getUsername());
            return JwtResponse.builder()
                    .accessToken(jwtService.generateToken(authRequest.getUsername()))
                    .token(refreshToken.getToken()).build();
        } else {
            throw new RuntimeException("invalid user request !");
        }
    }

    @PostMapping("/refreshToken")
    public JwtResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return refreshTokenService.findByToken(refreshTokenRequest.getToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUserInfo)
                .map(userInfo -> {
                    String accessToken = jwtService.generateToken(userInfo.getName());
                    return JwtResponse.builder()
                            .accessToken(accessToken)
                            .token(refreshTokenRequest.getToken())
                            .build();
                }).orElseThrow(() -> new RuntimeException(
                        "Refresh token is not in database!"));
    }
}