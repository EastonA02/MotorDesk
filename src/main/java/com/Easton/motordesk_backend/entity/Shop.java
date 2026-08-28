package com.Easton.motordesk_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
lamdoc notations - remove need to write these methods
& constructor
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

//create shop table in postgres database
@Table(name = "shop")

//declare class as an entity (Table)
@Entity
public class Shop {

    //Primary Key: ID
    @Id
    //auto assign int value & increment
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_id")
    private Long id;

    //All other attributes (columns)

    @Column(name = "name", length = 150)
    private String name;

    @Column(name = "address", length = 150)
    private String address;

    @Column(name = "phone", length = 20)
    private String phone;

}
