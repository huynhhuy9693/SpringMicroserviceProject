package com.example.demo.service;


import com.example.demo.entity.UserTb;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    RoleRepository roleRepository;

    public List<UserTb> findAllUsers()
    {
        roleRepository.findAll();
        return repository.findAll();

    }

    public UserTb findUserById(long id)
    {

        for(UserTb user : repository.findAll())
        {
            if(user.getId()==id)
            {
                return user;
            }
        }
        return null;
    }
    public boolean isIdExits(UserTb user)
    {
        return findUserById(user.getId())==null;
    }


    public UserTb saveUser(UserTb user)
    {
        System.out.println("----");
        return repository.save(user) ;
    }

    public void deleteUser(Long id)
    {
        repository.deleteById(id);
    }

    public UserTb findByName(String name) {
        for(UserTb user : repository.findAll()){
            if(user.getName().equalsIgnoreCase(name)){
                return user;
            }
        }
        return null;
    }
    public boolean isUserExist(User user) {
        return findByName(user.getName())!=null;
    }
}
