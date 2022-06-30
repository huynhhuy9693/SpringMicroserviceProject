package com.example.demo.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Product;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;


    public List<Product> findAllProducts()
    {
        List<Product> productList = repository.findAll();
        return productList;

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


    public Product saveProduct(ProductDTO productDTO)
    {
        Product productRequest = modelMapper.map(productDTO, Product.class);
        Product product = repository.save(productRequest);
        // convert entity to DTO
        return product;
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


//    public Product findByCategory(Long id,Product product)
//    {
//        return repository.findByCategoryId(product.getCategoryId());
//    }

}
