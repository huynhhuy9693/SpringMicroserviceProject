package com.example.demo.service;

import com.example.demo.entity.Cart;
import com.example.demo.reposiroty.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository repository;

    public List<Cart> findAllCart()
    {
        return repository.findAll();
    }


    public Cart findCartById(@PathVariable("id") long id)
    {
        for(Cart  cart: repository.findAll())
        {
            if(cart.getId()==id)
            {
                return cart;
            }
        }
        return null;
    }
    public boolean isIdExits(Cart cart)
    {
        return findCartById(cart.getId())==null;
    }


    public Cart saveCart(@RequestBody Cart cart)
    {
        return repository.save(cart) ;
    }

    public void deleteCart(@PathVariable("id") long id )
    {
        repository.deleteById(id);
    }
}
