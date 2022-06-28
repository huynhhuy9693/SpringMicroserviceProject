package com.example.demo.repository;

import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.stereotype.Repository;

import java.util.List;

@RepositoryRestResource
public interface ProductRepository  extends JpaRepository<Product,Long> {
    //http://localhost:8081/api/products/search/findByName?name={$name}
    List<Product> findByName(@Param("name") String name);
}
