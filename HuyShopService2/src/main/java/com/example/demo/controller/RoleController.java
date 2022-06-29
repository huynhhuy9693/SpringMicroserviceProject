package com.example.demo.controller;


import com.example.demo.entity.Role;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
@RequestMapping("/admin_user")
public class RoleController {
    @Autowired
    private RoleService service;

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> findRoles(HttpServletResponse response)
    {
        response.addHeader("Access-Control-Allow-Origin", "*");
        List<Role> roleList = service.findAllRoles();
        return new ResponseEntity<>(roleList,HttpStatus.OK);
    }

//    @GetMapping("/create_role")
//    public String showAddRole(Model model)
//    {
//        Role role = new Role();
//        model.addAttribute("role", role);
//        return "admin/create_role";
//    }
//
    @GetMapping("/role/{id}")
    public ResponseEntity getRoleById(@PathVariable("id") int id,HttpServletResponse response) {
        System.out.println("Fetching Role with id " + id);
        response.addHeader("Access-Control-Allow-Origin", "*");
        Role role = service.findRoleById(id);
        if (role == null) {
            System.out.println("Role with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(service.findRoleById(id), HttpStatus.OK);
    }
//
//
//    }
//
    @PostMapping("role")
    public ResponseEntity  createRole(@RequestBody Role role)
    {
        System.out.println("Creating Role " +role.getName());
        return  new ResponseEntity<>(service.saveRole(role),HttpStatus.OK);

    }
//
//    @GetMapping("role/{id}")
//    public String deleteRole(@PathVariable("id") int id )
//    {
//        System.out.println("Fetching & Deleting Role with id " + id);
//        Role role = service.findRoleById(id);
////        if (role==null) {
////            System.out.println("Unable to delete. Role with id " + id + " not found");
////            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
////        }
//        service.deleteRole(id);
//        return "redirect:http://localhost:8080/admin_user/roles";
//    }
//
//    @GetMapping("role/details/{id}")
//    public String detailsRole(@PathVariable int id, Model model)
//    {
//        System.out.println("role_update " + id);
//        model.addAttribute("role",service.findRoleById(id));
//        return "admin/update_role";
//    }
//
    @PutMapping("role/{id}")
    public ResponseEntity  updateRole(@PathVariable("id") int id, @RequestBody Role role) {

        System.out.println("Updating Role " + id);
        service.saveRole(role);
        return new ResponseEntity<>(service.saveRole(role),HttpStatus.OK);

    }
}
