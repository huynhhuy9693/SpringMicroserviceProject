package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name="img_url")
    private String img_url;

    @Column(name = "quantity")
    private int quantity;

    @Column(name ="status")
    private boolean status;

    @PrePersist
    void onPrePersist() {
        if (status == false) {
            status=true;
        }
    }


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id" )
    private Category category;
}
