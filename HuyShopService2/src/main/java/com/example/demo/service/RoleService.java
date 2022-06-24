package com.example.demo.service;


import com.example.demo.entity.Role;
import com.example.demo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;


@Service
public class RoleService {
    @Autowired
    private RoleRepository repository;

    public List<Role> findAllRoles()
    {
        return repository.findAll();
    }
    public Role findRoleById(@PathVariable("id") int id)
    {
        for(Role role : repository.findAll())
        {
            if(role.getId()==id)
            {
                return role;
            }
        }
        return null;
    }
    public boolean isIdExits(Role role)
    {
        return findRoleById(role.getId())==null;
    }


    public Role saveRole(@RequestBody Role role)
    {

        return repository.save(role) ;

    }

    public void deleteRole(@PathVariable("id") int id )
    {
        repository.deleteById(id);
    }

    public Role findByName(String name) {
        for(Role role : repository.findAll()){
            if(role.getName().equalsIgnoreCase(name)){
                return role;
            }
        }
        return null;
    }
    public boolean isCategoryExist(Role role) {
        return findByName(role.getName())!=null;
    }

}
