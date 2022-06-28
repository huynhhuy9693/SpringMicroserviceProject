package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "status")
    private boolean status;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoryId")
    private List<Product> product ;
    @PrePersist
    void onPrePersist() {
        if (status == false) {
            status=true;
        }
    }
}
