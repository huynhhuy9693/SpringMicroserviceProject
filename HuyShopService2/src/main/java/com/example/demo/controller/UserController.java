package com.example.demo.controller;

import com.example.demo.entity.UserTb;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
@RequestMapping("/admin_user")
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private RoleService roleService;



    @GetMapping("/users")
    public String findUsers(Model model)
    {
        model.addAttribute("users", service.findAllUsers());
        return "/admin/user_list";

    }
    @GetMapping("/create_user")
    public String showAddUser(Model model)
    {
        UserTb user= new UserTb();
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.findAllRoles());
        return "admin/create_user";
    }

    @GetMapping("/user/{id}")
    public ResponseEntity findUserById(@PathVariable("id") long id)
    {
        System.out.println("Fetching User with id " + id);
        UserTb users = service.findUserById(id);
        if (users==null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>( service.findUserById(id), HttpStatus.OK);


    }

    @PostMapping("user/create")
    public String  createUser(@ModelAttribute("user") UserTb userTb )
    {
        System.out.println("Creating User " +userTb.getName());
        service.saveUser(userTb);
        return "redirect:http://localhost:8080/admin_user/users";
    }

    @GetMapping("user/delete/{id}")
    public String deleteUser(@PathVariable("id") long id )
    {

        System.out.println("Fetching & Deleting User with id " + id);
        UserTb user = service.findUserById(id);
//        if (user==null) {
//            System.out.println("Unable to delete. User with id " + id + " not found");
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
        service.deleteUser(id);
        return "redirect:http://localhost:8080/admin_user/users";
    }

    @GetMapping("user/details/{id}")
    public String detailProduct(@PathVariable Long id, Model model)
    {
        System.out.println("user id " + id);
        model.addAttribute("user",service.findUserById(id));
        model.addAttribute("roles",roleService.findAllRoles());
        return "admin/update_user.html";
    }

    @PostMapping("user/update/{id}")
    public String  updateUser(@RequestParam long id, @ModelAttribute("user") UserTb userTb) {

        System.out.println("Updating User " + id);
        service.saveUser(userTb);
        return "redirect:http://localhost:8080/admin_user/users";

    }
}
