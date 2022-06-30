package com.example.demo.controller;


import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Product;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
@RequestMapping( value = "/admin_product")


public class ProductController {
    @Autowired
    private ProductService service;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ModelMapper modelMapper;



    @GetMapping("/products")
    public ResponseEntity findProducts(HttpServletResponse response)
    {
        response.addHeader("Access-Control-Allow-Origin", "*");
        List<Product> productList = service.findAllProducts();
        return new ResponseEntity<>(productList,HttpStatus.OK);
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
    public ResponseEntity findProductById(@PathVariable("id") long id,HttpServletResponse response)
    {
        System.out.println("Fetching Product with id " + id);
        response.addHeader("Access-Control-Allow-Origin", "*");
        Product product = service.findProductById(id);
        if (product==null) {
            System.out.println("Product with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>( product, HttpStatus.OK);


    }

    @PostMapping(value = "product")
    public ResponseEntity  createProduct(@RequestBody  ProductDTO productDTO)
    {
        System.out.println("Creating Product " +productDTO.getName());
        return new ResponseEntity<>(service.saveProduct(productDTO),HttpStatus.OK);
    }

    @DeleteMapping ("product/{id}")
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

    @PutMapping("product/{id}")
    public ResponseEntity updateProduct(@PathVariable("id") long id, @RequestBody ProductDTO productDTO) {

        System.out.println("Updating Product " + id);

        service.saveProduct(productDTO);
        return new ResponseEntity<>(service.saveProduct(productDTO),HttpStatus.OK);
    }

//    @GetMapping(value = "product/{id}/category")
//    public ResponseEntity findByCategory(@PathVariable("id") Long id, @RequestBody Product product)
//    {
//        System.out.println("Find category of id :" +id);
//        return new ResponseEntity<>(service.findByCategory(id,product), HttpStatus.OK);
//    }
}
