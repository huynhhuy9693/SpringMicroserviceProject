package com.example.demo.controller;


import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping( value = "/admin_product")
public class CategotyController {

    @Autowired
    private CategoryService service;


    @GetMapping("/categories")
    public String findCategories(Model model)
    {
        List<Category> categoryList = service.findAllCategories();
        model.addAttribute("categories",categoryList);
        return "admin/category_list";

    }

    @GetMapping("/create_category")
    public String showAddCategory(Model model)
    {
        Category category = new Category();
        model.addAttribute("category", category);
        return "admin/create_category";
    }

    @GetMapping("/category/{id}")
    public ResponseEntity findCategoryById(@PathVariable("id") Long id)
    {
        System.out.println("Fetching Category with id " + id);
        Category category = service.findCategoryById(id);

        if (category==null) {
            System.out.println("Category with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>( service.findCategoryById(id), HttpStatus.OK);

    }

    @PostMapping("category/create")
    public String  createCategory(@ModelAttribute("category") Category category)
    {

        System.out.println("Creating Category " +category.getName());
        service.saveCategory(category);
        return "redirect:http://localhost:8080/admin_product/categories";

    }

    @GetMapping("category/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id )
    {

        System.out.println("Fetching & Deleting Category with id " + id);
        Category category = service.findCategoryById(id);
        service.deleteCategory(id);
        return "redirect:http://localhost:8080/admin_product/categories";
    }

    @GetMapping("category/details/{id}")
    public String detailCategory(@PathVariable Long id, Model model)
    {
        System.out.println("category_update " + id);

        model.addAttribute("category",service.findCategoryById(id));
        return "admin/update_category";
    }

    @PostMapping("category/update/{id}")
    public String  updateCategory(@RequestParam("id") Long id, @ModelAttribute("category") Category category) {

        System.out.println("Updating Category "  + id);
        service.saveCategory(category);
        return "redirect:http://localhost:8080/admin_product/categories";

    }
}
