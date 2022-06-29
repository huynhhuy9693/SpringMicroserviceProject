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

import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/admin_user")
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private RoleService roleService;



    @GetMapping("/users")
    public ResponseEntity<List<UserTb>> findUsers(HttpServletResponse response)
    {
        response.addHeader("Access-Control-Allow-Origin", "*");
        List<UserTb> userTbList = service.findAllUsers();
        return new ResponseEntity<>(userTbList,HttpStatus.OK);

    }
//    @GetMapping("/create_user")
//    public String showAddUser(Model model)
//    {
//        UserTb user= new UserTb();
//        model.addAttribute("user", user);
//        model.addAttribute("roles", roleService.findAllRoles());
//        return "admin/create_user";
//    }
//
    @GetMapping("/user/{id}")
    public ResponseEntity findUserById(@PathVariable("id") long id,HttpServletResponse response)
    {
        System.out.println("Fetching User with id " + id);
        response.addHeader("Access-Control-Allow-Origin", "*");
        UserTb users = service.findUserById(id);
        if (users==null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>( service.findUserById(id), HttpStatus.OK);


    }
//
    @PostMapping("user")
    public ResponseEntity  createUser(@RequestBody UserTb userTb )
    {
        System.out.println("Creating User " +userTb.getName());
        service.saveUser(userTb);
        return new ResponseEntity<>(service.saveUser(userTb),HttpStatus.OK);
    }
//
//    @GetMapping("user/{id}")
//    public String deleteUser(@PathVariable("id") long id )
//    {
//
//        System.out.println("Fetching & Deleting User with id " + id);
//        UserTb user = service.findUserById(id);
////        if (user==null) {
////            System.out.println("Unable to delete. User with id " + id + " not found");
////            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
////        }
//        service.deleteUser(id);
//        return "redirect:http://localhost:8080/admin_user/users";
//    }
//
//    @GetMapping("user/details/{id}")
//    public String detailProduct(@PathVariable Long id, Model model)
//    {
//        System.out.println("user id " + id);
//        model.addAttribute("user",service.findUserById(id));
//        model.addAttribute("roles",roleService.findAllRoles());
//        return "admin/update_user.html";
//    }
//
    @PutMapping("user/{id}")
    public ResponseEntity  updateUser(@PathVariable long id, @RequestBody UserTb userTb) {

        System.out.println("Updating User " + id);
        return new ResponseEntity<>(service.saveUser(userTb),HttpStatus.OK);

    }
}
