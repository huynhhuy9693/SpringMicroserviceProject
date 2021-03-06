package com.example.demo.controller;


import com.example.demo.dto.CategoryDTO;
import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping( value = "/admin_product")
public class CategotyController {

    @Autowired
    private CategoryService service;


    @GetMapping("/categories")
    public ResponseEntity<List<Category>> findCategories(HttpServletResponse response)
    {
        response.addHeader("Access-Control-Allow-Origin", "*");
        List<Category> categoryList = service.findAllCategories();
        return new ResponseEntity<>(categoryList,HttpStatus.OK);

    }

    @GetMapping("/create_category")
    public String showAddCategory(Model model)
    {
        Category category = new Category();
        model.addAttribute("category", category);
        return "admin/create_category";
    }

    @GetMapping("/category/{id}")
    public ResponseEntity findCategoryById(@PathVariable("id") Long id,HttpServletResponse response)
    {
        System.out.println("Fetching Category with id " + id);
        response.addHeader("Access-Control-Allow-Origin", "*");
        Category category = service.findCategoryById(id);

        if (category==null) {
            System.out.println("Category with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>( service.findCategoryById(id), HttpStatus.OK);

    }

    @PostMapping(value = "category")
    public ResponseEntity  createCategory( @RequestBody Category category)
    {

        System.out.println("Creating Category " +category.getName());

        return new ResponseEntity<>(service.saveCategory(category),HttpStatus.OK);
    }

    @DeleteMapping("category/{id}")
    public void deleteCategory(@PathVariable("id") Long id )
    {
        System.out.println("Fetching & Deleting Category with id " + id);
        service.findCategoryById(id);
        service.deleteCategory(id);
    }

    @GetMapping("category/details/{id}")
    public String detailCategory(@PathVariable Long id, Model model)
    {
        System.out.println("category_update " + id);
        model.addAttribute("category",service.findCategoryById(id));
        return "admin/update_category";
    }

    @PutMapping("category/{id}")
    public ResponseEntity  updateCategory(@PathVariable("id") Long id, @RequestBody Category category)
    {
        System.out.println("Updating Category "  + id);
        service.findCategoryById(id);
        return new ResponseEntity<>(service.saveCategory(category),HttpStatus.OK);
    }
}
