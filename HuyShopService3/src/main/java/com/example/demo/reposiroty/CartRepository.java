package com.example.demo.reposiroty;


import com.example.demo.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
public interface CartRepository extends JpaRepository<Cart,Long> {
}
