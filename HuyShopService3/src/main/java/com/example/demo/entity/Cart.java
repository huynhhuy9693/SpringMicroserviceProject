package com.example.demo.entity;



import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "status")
    private boolean status;
    @Column(name = "total_price")
    private long totalPrice;
    @Column(name = "order_number")
    private String orderNumber;

}
