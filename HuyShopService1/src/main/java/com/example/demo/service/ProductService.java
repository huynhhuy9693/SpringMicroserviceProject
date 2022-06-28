package com.example.demo.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.demo.entity.Product;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;
    @Autowired
    CategoryRepository categoryRepository;

    public List<Product> findAllProducts()
    {
        return repository.findAll();
    }


    public Product findProductById(long id)
    {

        for(Product product : repository.findAll())
        {
            if(product.getId()==id)
            {
                return product;
            }
        }
        return null;
    }
    public boolean isIdExits(Product product)
    {
        return findProductById(product.getId())==null;
    }


    public Product saveProduct(Product product)
    {
        return repository.save(product) ;
    }

    public void deleteProduct(Long id)
    {
        repository.deleteById(id);
    }


    public Product findByName(String name) {
        for(Product product : repository.findAll()){
            if(product.getName().equalsIgnoreCase(name)){
                return product;
            }
        }
        return null;
    }
    public boolean isProductExist(Product product) {
        return findByName(product.getName())!=null;
    }



}
