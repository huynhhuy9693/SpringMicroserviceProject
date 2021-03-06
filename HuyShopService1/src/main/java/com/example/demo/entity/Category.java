package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter

public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "status")
    private boolean status;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoryId")
    private List<Product> product ;
    @PrePersist
    void onPrePersist() {
        if (status == false) {
            status=true;
        }
    }
}
