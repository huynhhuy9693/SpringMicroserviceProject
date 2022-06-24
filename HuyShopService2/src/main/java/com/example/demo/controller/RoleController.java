package com.example.demo.controller;


import com.example.demo.entity.Role;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/admin_user")
public class RoleController {
    @Autowired
    private RoleService service;

    @GetMapping("/roles")
    public String findRoles(Model model)
    {
        model.addAttribute("roles", service.findAllRoles());
        return "/admin/role_list";
    }

    @GetMapping("/create_role")
    public String showAddRole(Model model)
    {
        Role role = new Role();
        model.addAttribute("role", role);
        return "admin/create_role";
    }

    @GetMapping("/role/{id}")
    public ResponseEntity getRoleById(@PathVariable("id") int id)
    {
        System.out.println("Fetching Role with id " + id);

        Role role = service.findRoleById(id);
        if (role==null) {
            System.out.println("Role with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>( service.findRoleById(id), HttpStatus.OK);


    }

    @PostMapping("role/create")
    public String  createRole(@ModelAttribute("role") Role role)
    {
        System.out.println("Creating Role " +role.getName());
        service.saveRole(role);
        return "redirect:http://localhost:8080/admin_user/roles";
    }

    @GetMapping("role/delete/{id}")
    public String deleteRole(@PathVariable("id") int id )
    {
        System.out.println("Fetching & Deleting Role with id " + id);
        Role role = service.findRoleById(id);
//        if (role==null) {
//            System.out.println("Unable to delete. Role with id " + id + " not found");
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
        service.deleteRole(id);
        return "redirect:http://localhost:8080/admin_user/roles";
    }

    @GetMapping("role/details/{id}")
    public String detailsRole(@PathVariable int id, Model model)
    {
        System.out.println("role_update " + id);
        model.addAttribute("role",service.findRoleById(id));
        return "admin/update_role";
    }

    @PostMapping("role/update/{id}")
    public String  updateRole(@RequestParam("id") int id, @ModelAttribute("role") Role role) {

        System.out.println("Updating Role " + id);
        service.saveRole(role);
        return "redirect:http://localhost:8080/admin_user/roles";

    }
}
