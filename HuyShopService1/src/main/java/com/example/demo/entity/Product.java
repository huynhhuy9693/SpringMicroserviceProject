package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
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

//    @JsonIgnore
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "category_id" )
    private Category categoryId;

    @PrePersist
    void onPrePersist() {
        if (status == false) {
            status=true;
        }
    }



}
