package com.example.demo.controller;

import com.example.demo.entity.Cart;
import com.example.demo.reposiroty.CartRepository;
import com.example.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin_cart")
public class CartController {

    @Autowired
    private CartService service;

    @GetMapping(value = "/carts")
    public ResponseEntity<List<Cart>> findAllCart()
    {
        return new ResponseEntity<>(service.findAllCart(), HttpStatus.OK);
    }

}
