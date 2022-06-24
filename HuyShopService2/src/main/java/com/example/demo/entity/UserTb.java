package com.example.demo.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserTb {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    String name;
    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "dob")
    Date dob;
    @Column(name = "phone")
    String phone;
    @Column(name = "email")
    String email;
    @Column(name = "address")
    String address;
    @Column(name = "status")
    boolean status;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "pass_word")
    private String passWord;
//    @Column(name = "role")
//    private int role;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role roleId;

//    @OneToOne(mappedBy = "user")
//    private Cart cart;

    @PrePersist
    void onPrePersist() {
        if (status==false) {
            status=true;
        }
    }
}
