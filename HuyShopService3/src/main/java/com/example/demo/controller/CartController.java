package com.example.demo.controller;

import com.example.demo.entity.Cart;
import com.example.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin_cart")
public class CartController {

    @Autowired
    private CartService service;


    @GetMapping(value = "/carts")
    public String findAllCart(Model model)
    {
        System.out.println("carts");
        List<Cart> carts = service.findAllCart();
        model.addAttribute("cart", carts);
        return "admin/cart_list";
    }

    public String showAddCart(@RequestParam("cart") Cart cart)
    {
        return null;
    }

}
