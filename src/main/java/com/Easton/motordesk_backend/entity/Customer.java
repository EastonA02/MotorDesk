package com.Easton.motordesk_backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

//create customer table in database
@Table(name = "customer")

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "address", length = 200)
    private String address;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    //Foreign Key
    //many customers to one shop
    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop; //join to Shop entity/table
}
