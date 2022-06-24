package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping( value = "/admin_product")


public class ProductController {
    @Autowired
    private ProductService service;
    @Autowired
    private CategoryService categoryService;



    @GetMapping("/products")
    public String findProducts(Model model)
    {
        model.addAttribute("products", service.findAllProducts());
        return "admin/product_list";
//        return service.findAllProducts();
    }

    @GetMapping("/create_product")
    public String showAddProduct(Model model)
    {
        Product product = new Product();
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.findAllCategories());
        return "admin/create_product";
    }

    @GetMapping("/product/{id}")
    public ResponseEntity findProductById(@PathVariable("id") long id)
    {
        System.out.println("Fetching Product with id " + id);
        Product product = service.findProductById(id);
        if (product==null) {
            System.out.println("Product with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>( service.findProductById(id), HttpStatus.OK);


    }

    @PostMapping("product/create")
    public String  createProduct( Model model,@ModelAttribute("product") Product product )
    {
        System.out.println("Creating Product " +product.getName());
        service.saveProduct(product);
        return "redirect:http://localhost:8080/admin_product/products";
    }

    @GetMapping ("product/delete/{id}")
    public String deleteProduct(@PathVariable("id") long id )
    {
        System.out.println("Fetching & Deleting Products with id " + id);
        Product product = service.findProductById(id);
        service.deleteProduct(id);
        return "redirect:http://localhost:8080/admin_product/products";
    }

    @GetMapping("product/details/{id}")
    public String detailProduct(@PathVariable Long id, Model model)
    {
        System.out.println("product id " + id);
        model.addAttribute("product",service.findProductById(id));
        model.addAttribute("categories",categoryService.findAllCategories());
        return "admin/update_product.html";
    }

    @PostMapping("product/update/{id}")
    public String updateProduct(@RequestParam("id") long id, @ModelAttribute("product") Product product) {

        System.out.println("Updating Product " + id);
        service.saveProduct(product);
        return "redirect:http://localhost:8080/admin_product/products";
    }
}
