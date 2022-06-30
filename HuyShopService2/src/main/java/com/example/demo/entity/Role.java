package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id ;
    @Column(name = "name")
    private String name;
    @ColumnDefault("true")
    @Column(name = "status")
    private boolean status;

    @JsonIgnore
    @OneToMany(mappedBy = "roleId" , cascade = CascadeType.ALL)
    private List<UserTb> user;

    @PrePersist
    void onPrePersist() {
        if (status==false) {
            status=true;
        }
    }
}
