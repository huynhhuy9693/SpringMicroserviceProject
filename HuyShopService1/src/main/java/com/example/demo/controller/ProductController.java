package com.example.demo.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.demo.entity.Product;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Part;
import java.io.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping( value = "/admin_product")


public class ProductController {
    @Autowired
    private ProductService service;
    @Autowired
    private CategoryService categoryService;




    @GetMapping("/products")
    public ResponseEntity<List<Product>> findProducts()
    {
        return new ResponseEntity<List<Product>>(service.findAllProducts(),HttpStatus.OK);
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
        return new ResponseEntity<>( product, HttpStatus.OK);


    }

    @PostMapping(value = "product/create")
    public ResponseEntity  createProduct(@RequestBody  Product product)
    {
        System.out.println("Creating Product " +product.getName());
        return new ResponseEntity<>(service.saveProduct(product),HttpStatus.OK);
    }

    @DeleteMapping ("product/delete/{id}")
    public void deleteProduct(@PathVariable("id") long id )
    {
        System.out.println("Fetching & Deleting Products with id " + id);

        Product product = service.findProductById(id);
        service.deleteProduct(id);
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
    public ResponseEntity updateProduct(@PathVariable("id") long id, @RequestBody Product product) {

        System.out.println("Updating Product " + id);

        service.saveProduct(product);
        return new ResponseEntity<>(service.saveProduct(product),HttpStatus.OK);
    }
}
