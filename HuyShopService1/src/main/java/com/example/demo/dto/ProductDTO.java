package com.example.demo.dto;

import com.example.demo.entity.Category;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDTO implements Serializable {

    private long id;


    private String name;


    private BigDecimal price;


    private String img_url;


    private int quantity;


    private boolean status;



    private CategoryDTO categoryDTO;
}
